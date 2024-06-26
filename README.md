## versions:
- vue 3
- java 17
- springboot 3.3.1
- maven 4.0.0

## Database:
- MySQL
- databasename:employee-seating-system
- table: seating_chart、employee
- 請見DB資料夾
- 需更改employee_seating_system\backend\src\main\resources\application.properties 中的username/password

## 關於SQL Injection和XSS
- 使用Prepared Statements
- Springboot使用ORM框架(Hibernate) 可有效避免SQL Injection攻擊
- 使用選單來避免使用者輸入內容進行XSS攻擊
