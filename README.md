## versions:
- vue 3
- java 17
- springboot 3.3.1

## Database:
- MySQL
- databasename:employee-seating-system
- table: seating_chart、employee
```SQL
-- 創建資料庫 esun_employee_seating，如果不存在的話
CREATE DATABASE IF NOT EXISTS esun_employee_seating;

-- 使用剛創建的資料庫
USE esun_employee_seating;

-- 樓層座位表 SeatingChart
CREATE TABLE IF NOT EXISTS SeatingChart (
    FLOOR_SEAT_SEQ INT PRIMARY KEY AUTO_INCREMENT,
    FLOOR_NO INT NOT NULL,
    SEAT_NO INT NOT NULL
);

-- 員工資料表 Employee
CREATE TABLE IF NOT EXISTS Employee (
    EMP_ID VARCHAR(10) PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL,
    EMAIL VARCHAR(100) UNIQUE,
    FLOOR_SEAT_SEQ INT,
    FOREIGN KEY (FLOOR_SEAT_SEQ) REFERENCES SeatingChart(FLOOR_SEAT_SEQ)
);


-- 插入範例資料到 SeatingChart 表
INSERT INTO SeatingChart (FLOOR_NO, SEAT_NO) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(3, 1),
(3, 2),
(3, 3),
(3, 4),
(4, 1),
(4, 2),
(4, 3),
(4, 4);

INSERT INTO Employee (EMP_ID, NAME, EMAIL, FLOOR_SEAT_SEQ) VALUES ('12006', '', '', NULL);
INSERT INTO Employee (EMP_ID, NAME, EMAIL, FLOOR_SEAT_SEQ) VALUES ('16142', '', '', NULL);
INSERT INTO Employee (EMP_ID, NAME, EMAIL, FLOOR_SEAT_SEQ) VALUES ('13040', '', '', NULL);
INSERT INTO Employee (EMP_ID, NAME, EMAIL, FLOOR_SEAT_SEQ) VALUES ('17081', '', '', NULL);
INSERT INTO Employee (EMP_ID, NAME, EMAIL, FLOOR_SEAT_SEQ) VALUES ('11221', '', '', NULL);

COMMIT;
```

