CREATE TABLE cargo (
    cod_cargo INT AUTO_INCREMENT PRIMARY KEY,
    cargo VARCHAR(50) NOT NULL
);

CREATE TABLE funcionario (
    cod_funcionario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(70) NOT NULL,
    sexo VARCHAR(10),
    telefone VARCHAR(20),
    cod_cargo INT NOT NULL,
    FOREIGN KEY (cod_cargo) REFERENCES cargo(cod_cargo)
);
