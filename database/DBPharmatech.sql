CREATE DATABASE DBPharmatech;

USE DBPharmatech;

CREATE TABLE tbUsuarios (
    cpf VARCHAR(14) NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    tipoDeUsuario VARCHAR(20) NOT NULL
);

CREATE TABLE tbClientes (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cpf VARCHAR(14) NOT NULL,
    FOREIGN KEY(cpf) REFERENCES tbUsuarios(cpf)  
);

CREATE TABLE tbFarmaceuticos (
    id INT NOT NULL NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cpf VARCHAR(14) NOT NULL,
    FOREIGN KEY(cpf) REFERENCES tbUsuarios(cpf)  
);

CREATE TABLE tbAlmoxerifes (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cpf VARCHAR(14) NOT NULL,
    FOREIGN KEY(cpf) REFERENCES tbUsuarios(cpf)  
);

CREATE TABLE tbGerentes (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cpf VARCHAR(14) NOT NULL,
    FOREIGN KEY(cpf) REFERENCES tbUsuarios(cpf)
);

CREATE TABLE tbMedicamentos (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    dosagem VARCHAR(50) NOT NULL,
    descricao VARCHAR(200) NOT NULL,
    imagemDoMedicamento VARCHAR(250) NOT NULL,
    preco DOUBLE(5, 2) NOT NULL
);

INSERT INTO tbUsuarios VALUES 
    ('123.456.789-10', 'Retro', '123', '+5511943123154', 'Rua Paulista 10', 'Cliente'),
    ('987.654.321-01', 'Default', '456', '+5511242154212', 'Rua Arnival 31', 'Farmaceutico'),
    ('321.212.341-12', 'Reginaldo', '789', '+551192491941', 'Rua Condor 21', 'Almoxerife'),
    ('941.321.512-04', 'Lineuzinho', '012', '+551124124293', 'Rua Eldorado 54', 'Gerente');

INSERT INTO tbClientes (CPF) VALUES ('123.456.789-10');

INSERT INTO tbFarmaceuticos (CPF) VALUES ('987.654.321-01');

INSERT INTO tbAlmoxerifes (CPF) VALUES ('321.212.341-12');

INSERT INTO tbGerentes (CPF) VALUES ('941.321.512-04');

INSERT INTO tbMedicamentos (nome, dosagem, descricao, imagemDoMedicamento, preco) VALUES
	('Dipirona', '500Mg', 'Analgesico e antitérmico', 'https://io.convertiez.com.br/m/drogaonet/shop/products/images/12098/large/dipirona-monoidratada-medley-500mg-caixa-com-30-comprimidos_7849.jpg', 13.99),
	('Paracetamol', '750Mg', ' Analgésico e antipirético', 'https://th.bing.com/th/id/R.e3f4e94925feebd5461b0d590d76eb91?rik=S8cXlrFrFJgFtA&pid=ImgRaw&r=0', 12.50),
	('Ibuprofeno', '600Mg', 'Anti-inflamatório não esteroide', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/163671/7899547528619.png?v=637638139981370000', 25.00),
	('Amoxicilina', '500Mg', 'Antibiótico', 'https://th.bing.com/th/id/R.4a0e86c039fd16e9f9a170be3f47620d?rik=NMHjP7MyTZRB8Q&pid=ImgRaw&r=0', 45.50),
	('Azitromicina', '500Mg', 'Antibiótico', 'https://th.bing.com/th/id/OIP.d_ael963LAoEz4QCPS63hAHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 20.50),	
	(' Dicloridrato Cetirizina', '10Mg', 'antialérgico', 'https://th.bing.com/th/id/OIP.s-OnZWAuLLWaEjaKGBL4YwHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 90.50),
	('Loratadina', '10Mg', 'antialérgico', 'https://th.bing.com/th/id/OIP.UKAVa4ZII_ZXR2R9R00ozQHaHa?o=7&cb=iwp2rm=3&rs=1&pid=ImgDetMain', 20.50),
	('Omeprazol', '40Mg', 'Gastrico', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/156451/7896422504614.png?v=637637962607600000', 20.00),
	('Omeprazol', '20Mg', 'Gastrico', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/156789-800-auto?v=637637973070900000&width=800&height=auto&aspect=true', 19.00),
	('Simeticona', '120Mg', 'Antigases', 'https://th.bing.com/th/id/OIP.R6ih_7rKcbIIjcJ8H2PC-gHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 10.50),
	('Loperamida', '2Mg', 'Antidiarreico', 'https://pasteurio.vtexassets.com/arquivos/ids/175095/Salud-y-Medicamentos-Medicamentos-formulados_Genfar_Pasteur_169456_caja_01.jpg?v=638031148778270000', 15.50),
	('Clonazepam', '2Mg', 'Ansiolítico', 'https://th.bing.com/th/id/OIP._cp7V0Wy52d1vCmuHhfdeAHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 30.50),
	(' Cloridrato de Fluoxetina', '20Mg', 'Antidepressivo', 'https://th.bing.com/th/id/R.5c8eca5dfeac4a77ea327d94dfe09473?rik=RbiJpUUcXqflWQ&pid=ImgRaw&r=0', 40.50),
	(' Cloridrato de Sertralina', '50Mg', 'Antidepressivo', 'https://th.bing.com/th/id/OIP.Qua9hlnu07K1yGQo8C3LIQHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 35.00),
	('Atenolol', '50Mg', 'Antihipertensivo', 'https://th.bing.com/th/id/OIP.lhpGmpzHUW3rdss0qsInMAHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 15.00),
	('Losartana', '100Mg', 'Antihipertensivo', 'https://cdn-cosmos.bluesoft.com.br/products/7896422516853', 25.00),
	(' Cloridrato de Metformina', '850Mg', 'Antidiabético', 'https://images.tcdn.com.br/img/img_prod/740081/cloridrato_de_metformina_850mg_30_comprimidos_revestidos_prati_2113_1_a9ce61cce5fa846609dc62642750740b.jpg', 10.00),
	('Insulina', '100UI/ml', 'Antidiabético', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/172187/7897705200087.png?v=637703262258230000', 200.00),
	('Atorvastatina', '20Mg', 'Hipolipemiante', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/161235-800-auto?v=637638081078300000&width=800&height=auto&aspect=true', 35.00),
	('Atorvastatina', '40Mg', 'Hipolipemiante', 'https://th.bing.com/th/id/R.e4aabc4369a2cbf98b1e36891d699f3d?rik=lVsxtw4fu%2flrUg&pid=ImgRaw&r=0', 25.00),
	('Sinvastatina', '20Mg', 'Hipolipemiante', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/156663/7896422507851.png?v=637637970036400000', 12.90),
	('Ácido Acetilsalicílico', '150Mg', 'Antiinflamatório e antiplaquetário', 'https://drogariasp.vteximg.com.br/arquivos/ids/396828-1000-1000/acido-acetilsalicilico-100mg-cimed-10-comprimidos-Drogaria-SP-314420.jpg?v=637191139983630000', 19.90),
	('Ácido Valpróico', '250Mg', 'Anticonvulsivante', 'https://th.bing.com/th/id/OIP.j-JFSJZto1AAfwm6T3mMngHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 50.00),
	('Carbamazepina', '200Mg', 'Anticonvulsivante', 'https://th.bing.com/th/id/R.e68f0e1e406b869c7c2806ebeda1bf4f?rik=5FxMGX11LlJ6JQ&pid=ImgRaw&r=0', 120.00),
	(' Cloridrato de Sibultramina', '15Mg', 'tratamento para obesidade', 'https://anabolloja.com.br/wp-content/uploads/2023/02/Sibutramina-Eurofarma-660x641.jpg?v=1677268272', 90.00),
	('Brometo de Ipratrópio', '20Mcg', 'Broncodilatador', 'https://th.bing.com/th/id/OIP.WhZQRY1PQEbxW6sGUCGMpgHaJG?cb=iwp2&rs=1&pid=ImgDetMain', 30.00),
	('Salbutamol', '100Mcg', 'Broncodilatador', 'https://images.tcdn.com.br/img/img_prod/740081/aerolin_100mcg_spray_gsk_1907_1_4052d615c3da4032e19c29aec671a644.jpg', 25.00),
	('Budesonida', '200Mcg', 'Corticosteroide ', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/219988/7896181900740.png?v=637709529672400000', 20.00),
	('Montelucaste', '10Mg', 'Antileucotrieno', 'https://th.bing.com/th/id/OIP.o2hs6N-zmL-jcepmSBAD5gHaHa?o=7&cb=iwp2rm=3&rs=1&pid=ImgDetMain', 15.00),	
	('Dexametasona', '0,5Mg', 'Corticosteroide', 'https://th.bing.com/th/id/OIP.Y7neuPksulugV82JbQTOfAHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 20.00),
	('Prednisona', '20Mg', 'Corticosteroide', 'https://th.bing.com/th/id/R.4ee1b7fc03dece83da3fe512e5dea192?rik=hmGPonHWgtCUNA&pid=ImgRaw&r=0', 10.99),
	('Dipirona', '1G', 'Analgesico e antitérmico', 'https://product-data.raiadrogasil.io/images/3821266.webp?width=450&height=450&quality=85&type=resize', 35.50),
	(' Novacort', '10g', 'Antiflamatorio', 'https://th.bing.com/th/id/R.31e009f09df24f6eeb29c435683a3bba?rik=wV5V%2fX9Lt6nv8w&pid=ImgRaw&r=0', 50.00),
	('Cetoconazol', '200Mg', 'Antifúngico', 'https://th.bing.com/th/id/OIP.tS01HiQPKHd6-5I9nv0s7QHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 15.00),
	('Fluconazol', '150Mg', 'Antifúngico', 'https://th.bing.com/th/id/R.c895cf99411772b5987204a6178e355f?rik=ScqySnZQ7l1PAQ&pid=ImgRaw&r=0', 10.50),
	('Nistatina', '100000UI', 'Antifúngico', 'https://th.bing.com/th/id/OIP.m7MjsmFHOlC2ZOkIpH-_MgHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 17.20),
	('Clotrimazol', '10Mg', 'Antifúngico', 'https://drogariacoop.vtexassets.com/arquivos/ids/158528/7896004711423.jpg?v=638010888471800000', 15.90),
	('Metronidazol', '100Mg', 'Antibiótico e antiparasitário', 'https://th.bing.com/th/id/OIP.7nFkmhAWp7clsW3AEjjxRAHaJG?cb=iwp2&rs=1&pid=ImgDetMain', 22.50),
	('Albendazol', '400Mg', 'Antiparasitário', 'https://th.bing.com/th/id/OIP.XNPtk98rcLf2075RAsxaiAHaE8?o=7&cb=iwp2rm=3&rs=1&pid=ImgDetMain',15.57),	
	('Ivermectina', '6Mg', 'Antiparasitário', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/163658/7896714212678.png?v=637638139658600000', 20.00),
	('Atropina', '10mg', 'Dilatação da pupila', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/167643-800-auto?v=637638253713900000&width=800&height=auto&aspect=true', 12.45),
	('Dizepam', '10mg', 'Ansiolitico', 'https://th.bing.com/th/id/OIP.ugbZTccVQxRwiHGORSl2rAHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 16.99),
	('Cloridrato Paroxetina', '20mg', 'Antidepressivo', 'https://cdn-cosmos.bluesoft.com.br/products/7891317420338', 50.99),
	('Cloridrato de Venlafaxina', '75mg', 'Antidepressivo', 'https://th.bing.com/th/id/R.32828e20f77fa606bb6b1b8af64e7f10?rik=jW%2fmJVjAo%2fjy4Q&pid=ImgRaw&r=0', 40.99),
	('Cloridrato de Duloxetina', '30mg', 'Antidepressivo', 'https://th.bing.com/th/id/OIP.CeOQaTA3XFK2brRB0KmONwHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 90.99),
	('Cloridrato de Bupropiona', '150mg', 'Antidepressivo', 'https://media.sketchfab.com/models/aa8062ce7e964c9185e3d712e4cfdac1/thumbnails/d2ccb4c717854a068d28b85c0606ab40/c228083a9b60499389e38064fb17e6c5.jpeg', 114.25),
	('Cloridrato de Nortriptilina', '25mg', 'Antidepressivo', 'https://media.sketchfab.com/models/aa8062ce7e964c9185e3d712e4cfdac1/thumbnails/d2ccb4c717854a068d28b85c0606ab40/c228083a9b60499389e38064fb17e6c5.jpeg', 42.99),
	('Cloridrato de Amitriptilina', '25mg', 'Antidepressivo', 'https://th.bing.com/th/id/OIP.LqZNimCLicZR5neU2XXF_AHaJG?o=7&cb=iwp2rm=3&rs=1&pid=ImgDetMain', 10.99),
	('Cloridrato de Imipramina', '25mg', 'Antidepressivo', 'https://th.bing.com/th/id/OIP.zMuyuo_V9O_n8BqdiIlqzgHaDp?cb=iwp2&rs=1&pid=ImgDetMain', 31.99);
