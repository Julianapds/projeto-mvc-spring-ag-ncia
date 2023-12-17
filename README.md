# Plataforma Full Stack para Agência Rotas do Sol

Rotas do Sol é um site de viagens. Ele foi desenvolvido como atividade integrada do curso de Desenvolvimento Web Full Stack da Recode.

Para detalhes como modelos, acesse o repositório da primeira versão do projeto: https://github.com/Julianapds/backend-site-viagens

## Funcionalidades implementadas

- [x] Cadastro de usuário
- [x] Login de usuário
- [x] Gerenciamento de clientes
- [x] Gerenciamento de destinos
- [x] Gerenciamento de pacotes
- [x] Cadastro de reservas

## Tecnologias utilizadas

- HTML
- CSS
- JavaScript
- Bootstrap
- Java
- Thymeleaf
- Spring Boot
- Spring Data JPA


## Script de tabelas e dados

```
-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS db_rotasdosol;
USE db_rotasdosol;

-- Criação das tabelas
CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(11),
    email VARCHAR(255),
    endereco VARCHAR(255),
    nome VARCHAR(255),
    senha VARCHAR(255),
    telefone VARCHAR(20),
    tipo_usuario VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS destinos (
    id_destino INT AUTO_INCREMENT PRIMARY KEY,
    cidade VARCHAR(255),
    descricao TEXT,
    pais VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS hospedagens (
    id_hospedagem INT AUTO_INCREMENT PRIMARY KEY,
    data_checkin DATE,
    data_checkout DATE,
    url_imagem VARCHAR(255),
    nome_hotel VARCHAR(255),
    tipo_quarto VARCHAR(50),
    valor_pernoite DECIMAL(10, 2),
    id_destino INT,
    FOREIGN KEY (id_destino) REFERENCES destinos(id_destino)
);

CREATE TABLE IF NOT EXISTS voos (
    id_voo INT AUTO_INCREMENT PRIMARY KEY,
    companhia_aerea VARCHAR(255),
    data_chegada DATE,
    data_partida DATE,
    url_imagem VARCHAR(255),
    valor_preco DECIMAL(10, 2),
    id_destino INT,
    FOREIGN KEY (id_destino) REFERENCES destinos(id_destino)
);

CREATE TABLE IF NOT EXISTS pacotes (
    id_pacote INT AUTO_INCREMENT PRIMARY KEY,
    url_imagem VARCHAR(255),
    valor_preco DECIMAL(10, 2),
    id_hospedagem INT,
    id_voo INT,
    FOREIGN KEY (id_hospedagem) REFERENCES hospedagens(id_hospedagem),
    FOREIGN KEY (id_voo) REFERENCES voos(id_voo)
);

CREATE TABLE IF NOT EXISTS status_reserva (
    id_status INT AUTO_INCREMENT PRIMARY KEY,
    data_criacao DATE,
    nome_status VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS reservas (
    id_reserva INT AUTO_INCREMENT PRIMARY KEY,
    data_reserva DATE,
    id_cliente INT,
    id_hospedagem INT,
    id_pacote INT,
    id_status INT,
    id_voo INT,
    FOREIGN KEY (id_cliente) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_hospedagem) REFERENCES hospedagens(id_hospedagem),
    FOREIGN KEY (id_pacote) REFERENCES pacotes(id_pacote),
    FOREIGN KEY (id_status) REFERENCES status_reserva(id_status),
    FOREIGN KEY (id_voo) REFERENCES voos(id_voo)
);

-- Inserção de dados
INSERT INTO usuarios (cpf, email, endereco, nome, senha, telefone, tipo_usuario)
VALUES ('12345678910', 'cliente@example.com', 'Rua dos Clientes, 123', 'Cliente Exemplo', '123', '11 99999-9999', 'CLIENTE'),
       ('98765432100', 'admin@example.com', 'Avenida dos Administradores, 321', 'Admin Exemplo', '123', '11 88888-8888', 'ADMIN');

-- Inserção de destinos
INSERT INTO `db_rotasdosol`.`destinos`
(`cidade`, `descricao`, `pais`)
VALUES
('Rio de Janeiro', 'Cidade maravilhosa com praias e montanhas', 'Brasil'),
('Paris', 'Cidade da luz e da arte', 'França'),
('Tokyo', 'Metropole vibrante e cheia de tecnologia', 'Japão');

-- Inserção de hospedagens
INSERT INTO `db_rotasdosol`.`hospedagens`
(`data_checkin`, `data_checkout`, `url_imagem`, `nome_hotel`, `tipo_quarto`, `valor_pernoite`, `id_destino`)
VALUES
('2023-01-15', '2023-01-20', 'https://cf.bstatic.com/xdata/images/hotel/max1024x768/293155761.jpg?k=d63ea2f99cd1e82fd00c4dc204c49863f4f7da80225e31f78f66265dc4731df7&o=&hp=1', 'Hotel Maravilha', 'Luxo', 500.00, 1),
('2023-02-10', '2023-02-15', 'https://cf.bstatic.com/xdata/images/hotel/max1024x768/295686209.jpg?k=858fa8ccde6a4ef19c42adca0971dab45103567736cc1f0f74ba11c4ab4b31d0&o=&hp=1', 'Hotel Paris', 'Standard', 300.00, 2),
('2023-03-05', '2023-03-10', 'https://cdn.tatlerasia.com/asiatatler/i/hk/2020/01/09104644-ritz-carlton-tokyo-hotel_cover_2000x1126.jpeg', 'Hotel Tokyo', 'Executivo', 400.00, 3);

-- Inserção de voos
INSERT INTO `db_rotasdosol`.`voos`
(`companhia_aerea`, `data_chegada`, `data_partida`, `url_imagem`, `valor_preco`, `id_destino`)
VALUES
('Air Brasil', '2023-01-15', '2023-01-20', 'https://upload.wikimedia.org/wikipedia/commons/2/2f/PRAIB2.jpg', 1500.00, 1),
('Air France', '2023-02-10', '2023-02-15', 'https://revistapilotoribeirao.com.br/wp-content/uploads/2023/06/AF-A350-scaled.jpg', 2000.00, 2),
('Air Japan', '2023-03-05', '2023-03-10', 'https://s28477.pcdn.co/wp-content/uploads/2022/03/AirJapan.jpg', 2500.00, 3);

-- Inserção de pacotes
INSERT INTO `db_rotasdosol`.`pacotes`
(`url_imagem`, `valor_preco`, `id_hospedagem`, `id_voo`)
VALUES
('https://cf.bstatic.com/xdata/images/hotel/max1024x768/293155761.jpg?k=d63ea2f99cd1e82fd00c4dc204c49863f4f7da80225e31f78f66265dc4731df7&o=&hp=1', 3000.00, 1, 1),
('https://cf.bstatic.com/xdata/images/hotel/max1024x768/295686209.jpg?k=858fa8ccde6a4ef19c42adca0971dab45103567736cc1f0f74ba11c4ab4b31d0&o=&hp=1', 3500.00, 2, 2),
('https://cdn.tatlerasia.com/asiatatler/i/hk/2020/01/09104644-ritz-carlton-tokyo-hotel_cover_2000x1126.jpeg', 4000.00, 3, 3);

-- Inserção de status de reserva
INSERT INTO `db_rotasdosol`.`status_reserva`
(`data_criacao`, `nome_status`)
VALUES
('2023-01-01', 'Confirmada'),
('2023-01-02', 'Cancelada'),
('2023-01-03', 'Pendente');

-- Inserção de reservas
INSERT INTO `db_rotasdosol`.`reservas`
(`data_reserva`, `id_cliente`, `id_hospedagem`, `id_pacote`, `id_status`, `id_voo`)
VALUES
('2023-01-10', 1, 1, NULL, 1, NULL),
('2023-02-05', 2, NULL, 2, 3, NULL),
('2023-03-01', 3, NULL, NULL, 2, 1);
```