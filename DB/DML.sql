
USE esun_employee_seating;

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
