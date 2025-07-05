CREATE TABLE EXTRACAO_PRO (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    int_pro VARCHAR(2) NOT NULL UNIQUE
);

INSERT INTO EXTRACAO_PRO (name, cpf, email, int_pro) VALUES
('Lucas Silva', '12345678901', 'lucas.silva1@email.com', 'N'),
('Mariana Souza', '98765432100', 'mariana.souza2@email.com', 'N'),
('Carlos Lima', '45678912300', 'carlos.lima3@email.com', 'N'),
('Ana Pereira', '32165498701', 'ana.pereira4@email.com', 'N'),
('João Mendes', '01234567890', 'joao.mendes5@email.com', 'N'),
('Fernanda Rocha', '09876543210', 'fernanda.rocha6@email.com', 'N'),
('Bruno Torres', '10928374651', 'bruno.torres7@email.com', 'N'),
('Juliana Alves', '65432198700', 'juliana.alves8@email.com', 'N'),
('Rafael Costa', '78945612300', 'rafael.costa9@email.com', 'N'),
('Paula Nunes', '74185296300', 'paula.nunes10@email.com', 'N'),
('Claudia Barreto', '10394857639', 'claudia.barreto100@email.com', 'N');
