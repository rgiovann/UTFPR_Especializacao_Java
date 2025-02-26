DELIMITER $

CREATE PROCEDURE aumentar_salario(IN percentual DECIMAL(5,2))
BEGIN
    UPDATE funcionario
    SET salario = ROUND(salario * (1 + (percentual / 100)), 2);
END $

DELIMITER ;