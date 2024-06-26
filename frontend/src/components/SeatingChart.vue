<template>
    <h1>EMPLOYEE SEATING SYSTEM</h1>
    <div>
        <div v-for="floor in floors" :key="floor.floor_no">
            <div class="seats">
            <div
                v-for="seat in floor.seats"
                :key="seat.seat_no"
                :class="{
                seat: true,
                'available': seat.status === 'available',
                'occupied': seat.status === 'occupied',
                'selected': selectedSeat && selectedSeat.floor_seat_seq === seat.floor_seat_seq
                }"
                @click="selectSeat(seat)"
            >
                <span v-if="seat.status === 'occupied'" class="occupied-text">{{ seat.floor_no }}樓: 座位{{ seat.seat_no }} [員編:{{ seat.employee_id }}]</span>
                <span v-else>{{ seat.floor_no }}樓: 座位{{ seat.seat_no }}</span>
            </div>
            </div>
        </div>

        <!-- 圖例 -->
        <div class="legend">
            <div class="legend-item">
                <div class="legend-color occupied"></div>
                <span>已占用</span>
            </div>
            <div class="legend-item">
                <div class="legend-color available"></div>
                <span>空位</span>
            </div>
            <div class="legend-item">
                <div class="legend-color selected"></div>
                <span>請選擇</span>
            </div>
        </div>
        
        <div v-if="selectedSeat && !selectedSeat.employee_id">
            <h3>選擇座位: 樓層{{ selectedSeat.floor_no }} - 座位{{ selectedSeat.seat_no }}</h3>
            <div class="select-container">
            <label for="employee">選擇員工:  </label>
            <select v-model="selectedEmployee">
                <option v-for="employee in availableEmployees" :key="employee.emp_id" :value="employee.emp_id">
                {{ employee.emp_id }} - {{ employee.name }}
                </option>
            </select>
            <button @click="assignSeat">分配座位</button>
            </div>
        </div>
        
        <div v-if="selectedSeat && selectedSeat.employee_id">
            <h3>選擇座位: 樓層{{ selectedSeat.floor_no }} - 座位{{ selectedSeat.seat_no }}</h3>
            <div class="select-container">
            <p>已佔用座位，員工編號: {{ selectedSeat.employee_id }}</p>
            <button @click="clearSeat">清除座位</button>
            </div>
        </div>
        
        <!-- 提示框 -->
        <div v-if="alertMessage">
            <div class="alert">
            <span>{{ alertMessage }}  </span>
            <button @click="closeAlert">關閉</button>
            </div>
        </div>


    </div>
</template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        floors: [],
        employees: [],
        availableEmployees: [],
        selectedSeat: null,
        selectedEmployee: null,
        alertMessage: '', // 提示框的内容
      };
    },
    methods: {
      async fetchEmployees() {
        try {
          const response = await axios.get('/employee');
          this.employees = response.data;
          this.availableEmployees = this.employees.filter(employee => !employee.seatingChart);
        } catch (error) {
          console.error('Error fetching employees:', error);
        }
      },
      async fetchSeatingChart() {
        try {
          const response = await axios.get('/seatingchart');
          const seatingChart = response.data;
          const floors = seatingChart.reduce((acc, seat) => {
            let floor = acc.find(f => f.floor_no === seat.floor_no);
            if (!floor) {
              floor = { floor_no: seat.floor_no, seats: [] };
              acc.push(floor);
            }
            const assignedEmployee = this.employees.find(employee => employee.seatingChart && employee.seatingChart.floor_seat_seq === seat.floor_seat_seq);
            floor.seats.push({ ...seat, status: assignedEmployee ? 'occupied' : 'available', employee_id: assignedEmployee ? assignedEmployee.emp_id : null });
            return acc;
          }, []);
          this.floors = floors;
        } catch (error) {
          console.error('Error fetching seating chart:', error);
        }
      },
      selectSeat(seat) {
        this.selectedSeat = seat;
        this.selectedEmployee = null; // Reset selected employee when seat is selected
      },
      async assignSeat() {
        if (this.selectedSeat && this.selectedEmployee) {
          try {
            const seatingChartId = this.selectedSeat.floor_seat_seq;
            const seatingChart = { floor_seat_seq: seatingChartId };
            await axios.put(`/${this.selectedEmployee}`, seatingChart);
            await this.fetchEmployees();
            await this.fetchSeatingChart();
            this.alertMessage = '座位成功分配!  ';
          } catch (error) {
            console.error('Error assigning seat:', error);
            this.alertMessage = '分配座位时出错!';
          }
        }
      },
      async clearSeat() {
        if (this.selectedSeat && this.selectedSeat.employee_id) {
          try {
            await axios.put(`/remove/${this.selectedSeat.employee_id}`);
            await this.fetchEmployees();
            await this.fetchSeatingChart();
            this.alertMessage = '座位已清除!  ';
          } catch (error) {
            console.error('Error clearing seat:', error);
            this.alertMessage = '清除座位时出错!';
          }
        }
      },
      closeAlert() {
        this.alertMessage = ''; // 关闭提示框
      },
    },
    async mounted() {
      await this.fetchEmployees();
      await this.fetchSeatingChart();
    },
  };
  </script>
  
  <style scoped>
  .seats {
    display: flex;
    flex-wrap: wrap;
  }
  .seats .seat {
    flex:1;
    height: 30px;
    margin: 3px;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    text-align: center;
    font-size: 12px;
    font-weight: bold;
    color: black;
  }
  .seats .available {
    background-color: #f2f2f2;
  }
  .seats .occupied {
    background-color: darkred;
  }
  .seats .selected {
    background-color: lightgreen;
  }
  .seats .occupied-text {
    font-weight: bold;
    color: white;
  }
  .select-container {
    margin-top: 10px;
  }
  .select-container button {
    margin-left: 10px;
  }
  .alert {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #f2f2f2;
    padding: 20px;
    border: 1px solid #ccc;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  .alert button {
    margin-top: 10px;
    padding: 5px 10px;
    background-color: #007bff;
    color: white;
    border: none;
    cursor: pointer;
  }
  .floor-header {
    font-size: 16px;
    font-weight: bold;
    margin-top: 10px;
  }
  .legend {
  margin-top: 20px;
  display: flex;
  gap: 10px;
}

.legend-item {
  display: flex;
  align-items: center;
  gap: 5px;
}

.legend-color {
  width: 20px;
  height: 20px;
  border: 1px solid #ccc;
}

.legend-item span {
  font-size: 14px;
}

.legend-item .occupied {
  background-color: darkred;
}

.legend-item .available {
  background-color: #f2f2f2;
}

.legend-item .selected {
  background-color: lightgreen;
}
  </style>
  