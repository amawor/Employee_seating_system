<template>
  <div id="app">
    <SeatingChart />
  </div>
</template>

<script>
import SeatingChart from './components/SeatingChart.vue';

export default {
  components: {
    SeatingChart,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
