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
    preco DOUBLE(5, 2) NOT NULL,
    quantidadeDisponivel INT NOT NULL,
    precisaDeReceita BOOLEAN NOT NULL
);

CREATE TABLE tbCarrinho (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idMedicamento INT NOT NULL,
    quantidade INT NOT NULL,
    FOREIGN KEY(idMedicamento) REFERENCES tbMedicamentos(id),
    idCliente INT NOT NULL,
    FOREIGN KEY(idCliente) REFERENCES tbClientes(id)
);

INSERT INTO tbUsuarios VALUES 
    ('153.453.820-85', 'Thiago Ribeiro', '123', '+55 (11) 94312-3154', 'Rua da Mooca, 2047',        'Cliente'),
    ('693.868.540-15', 'Robson Nunes',   '456', '+55 (11) 94215-4212', 'Rua Pedro de Toledo, 447',  'Farmaceutico'),
    ('903.014.000-32', 'Reginaldo Dias', '789', '+55 (11) 92449-1941', 'Rua André de Andrade, s/n', 'Almoxerife'),
    ('479.638.030-22', 'Lineu Silva',    '012', '+55 (11) 94102-4293', 'Av. Rebouças, 3970',        'Gerente');

INSERT INTO tbClientes (CPF) VALUES ('153.453.820-85');

INSERT INTO tbFarmaceuticos (CPF) VALUES ('693.868.540-15');

INSERT INTO tbAlmoxerifes (CPF) VALUES ('903.014.000-32');

INSERT INTO tbGerentes (CPF) VALUES ('479.638.030-22');

INSERT INTO tbMedicamentos (nome, dosagem, descricao, imagemDoMedicamento, preco, quantidadeDisponivel, precisaDeReceita) VALUES
	('Dipirona', '500Mg', 'Analgesico e Antitérmico', 'https://io.convertiez.com.br/m/drogaonet/shop/products/images/12098/large/dipirona-monoidratada-medley-500mg-caixa-com-30-comprimidos_7849.jpg', 13.99, 20, false),
	('Paracetamol', '750Mg', 'Analgésico e Antipirético', 'https://th.bing.com/th/id/R.e3f4e94925feebd5461b0d590d76eb91?rik=S8cXlrFrFJgFtA&pid=ImgRaw&r=0', 12.50, 50, false),
	('Ibuprofeno', '600Mg', 'Anti-inflamatório não esteroide', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/163671/7899547528619.png?v=637638139981370000', 25.00, 15, false),
	('Amoxicilina', '500Mg', 'Antibiótico', 'https://th.bing.com/th/id/R.4a0e86c039fd16e9f9a170be3f47620d?rik=NMHjP7MyTZRB8Q&pid=ImgRaw&r=0', 45.50, 30, true),
	('Azitromicina', '500Mg', 'Antibiótico', 'https://th.bing.com/th/id/OIP.d_ael963LAoEz4QCPS63hAHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 20.50, 40, true),
	('Dicloridrato Cetirizina', '10Mg', 'Antialérgico', 'https://th.bing.com/th/id/OIP.s-OnZWAuLLWaEjaKGBL4YwHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 90.50, 10, false),
	('Loratadina', '10Mg', 'Antialérgico', 'https://th.bing.com/th/id/OIP.UKAVa4ZII_ZXR2R9R00ozQHaHa?o=7&cb=iwp2rm=3&rs=1&pid=ImgDetMain', 20.50, 50, false),
	('Omeprazol', '40Mg', 'Gastrico', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/156451/7896422504614.png?v=637637962607600000', 20.00, 25, false),
	('Omeprazol', '20Mg', 'Gastrico', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/156789-800-auto?v=637637973070900000&width=800&height=auto&aspect=true', 19.00, 35, false),
	('Simeticona', '120Mg', 'Antigases', 'https://th.bing.com/th/id/OIP.R6ih_7rKcbIIjcJ8H2PC-gHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 10.50, 50, false),
	('Loperamida', '2Mg', 'Antidiarreico', 'https://pasteurio.vtexassets.com/arquivos/ids/175095/Salud-y-Medicamentos-Medicamentos-formulados_Genfar_Pasteur_169456_caja_01.jpg?v=638031148778270000', 15.50, 25, false),
	('Clonazepam', '2Mg', 'Ansiolítico', 'https://th.bing.com/th/id/OIP._cp7V0Wy52d1vCmuHhfdeAHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 30.50, 50, true),
	('Cloridrato de Fluoxetina', '20Mg', 'Antidepressivo', 'https://th.bing.com/th/id/R.5c8eca5dfeac4a77ea327d94dfe09473?rik=RbiJpUUcXqflWQ&pid=ImgRaw&r=0', 40.50, 10, true),
	('Cloridrato de Sertralina', '50Mg', 'Antidepressivo', 'https://th.bing.com/th/id/OIP.Qua9hlnu07K1yGQo8C3LIQHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 35.00, 50, true),
	('Atenolol', '50Mg', 'Antihipertensivo', 'https://th.bing.com/th/id/OIP.lhpGmpzHUW3rdss0qsInMAHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 15.00, 40, false),
	('Losartana', '50Mg', 'Antihipertensivo', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/156623/7896422507738.png?v=637637969031870000', 25.00, 15, false),
	('Cloridrato de Metformina', '850Mg', 'Antidiabético', 'https://images.tcdn.com.br/img/img_prod/740081/cloridrato_de_metformina_850mg_30_comprimidos_revestidos_prati_2113_1_a9ce61cce5fa846609dc62642750740b.jpg', 10.00, 50, false),
	('Insulina', '100UI/ml', 'Antidiabético', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/172187/7897705200087.png?v=637703262258230000', 200.00, 40, false),
	('Atorvastatina', '20Mg', 'Hipolipemiante', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/161235-800-auto?v=637638081078300000&width=800&height=auto&aspect=true', 35.00, 50, false),
	('Atorvastatina', '40Mg', 'Hipolipemiante', 'https://th.bing.com/th/id/R.e4aabc4369a2cbf98b1e36891d699f3d?rik=lVsxtw4fu%2flrUg&pid=ImgRaw&r=0', 25.00, 20, false),
	('Sinvastatina', '20Mg', 'Hipolipemiante', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/156663/7896422507851.png?v=637637970036400000', 12.90, 25, false),
	('Ácido Acetilsalicílico', '150Mg', 'Antiinflamatório e Antiplaquetário', 'https://drogariasp.vteximg.com.br/arquivos/ids/396828-1000-1000/acido-acetilsalicilico-100mg-cimed-10-comprimidos-Drogaria-SP-314420.jpg?v=637191139983630000', 19.90, 50, false),
	('Ácido Valpróico', '250Mg', 'Anticonvulsivante', 'https://th.bing.com/th/id/OIP.j-JFSJZto1AAfwm6T3mMngHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 50.00, 10, false),
	('Carbamazepina', '200Mg', 'Anticonvulsivante', 'https://th.bing.com/th/id/R.e68f0e1e406b869c7c2806ebeda1bf4f?rik=5FxMGX11LlJ6JQ&pid=ImgRaw&r=0', 120.00, 30, false),
	('Cloridrato de Sibultramina', '15Mg', 'Tratamento para obesidade', 'https://anabolloja.com.br/wp-content/uploads/2023/02/Sibutramina-Eurofarma-660x641.jpg?v=1677268272', 90.00, 50, false),
	('Brometo de Ipratrópio', '20Mcg', 'Broncodilatador', 'https://th.bing.com/th/id/OIP.WhZQRY1PQEbxW6sGUCGMpgHaJG?cb=iwp2&rs=1&pid=ImgDetMain', 30.00, 50, false),
	('Salbutamol', '100Mcg', 'Broncodilatador', 'https://images.tcdn.com.br/img/img_prod/740081/aerolin_100mcg_spray_gsk_1907_1_4052d615c3da4032e19c29aec671a644.jpg', 25.00, 50, false),
	('Budesonida', '200Mcg', 'Corticosteroide ', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/219988/7896181900740.png?v=637709529672400000', 20.00, 30, false),
	('Montelucaste', '10Mg', 'Antileucotrieno', 'https://th.bing.com/th/id/OIP.o2hs6N-zmL-jcepmSBAD5gHaHa?o=7&cb=iwp2rm=3&rs=1&pid=ImgDetMain', 15.00, 40, false),
	('Dexametasona', '0,5Mg', 'Corticosteroide', 'https://th.bing.com/th/id/OIP.Y7neuPksulugV82JbQTOfAHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 20.00, 20, false),
	('Prednisona', '20Mg', 'Corticosteroide', 'https://th.bing.com/th/id/R.4ee1b7fc03dece83da3fe512e5dea192?rik=hmGPonHWgtCUNA&pid=ImgRaw&r=0', 10.99, 10, false),
	('Dipirona', '1G', 'Analgesico e Antitérmico', 'https://drogariasp.vteximg.com.br/arquivos/ids/1201928-1000-1000/531626---dipirona-1g-generico-farmaco-10-comprimidos-1.jpg?v=638761921771970000', 35.50, 50, false),
	('Novacort', '10g', 'Antiflamatorio', 'https://th.bing.com/th/id/R.31e009f09df24f6eeb29c435683a3bba?rik=wV5V%2fX9Lt6nv8w&pid=ImgRaw&r=0', 50.00, 40, false),
	('Cetoconazol', '200Mg', 'Antifúngico', 'https://th.bing.com/th/id/OIP.tS01HiQPKHd6-5I9nv0s7QHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 15.00, 22, false),
	('Fluconazol', '150Mg', 'Antifúngico', 'https://th.bing.com/th/id/R.c895cf99411772b5987204a6178e355f?rik=ScqySnZQ7l1PAQ&pid=ImgRaw&r=0', 10.50, 55, false),
	('Nistatina', '100 000UI/Ml', 'Antifúngico', 'https://static.wixstatic.com/media/474d30_079bad3c959f42fb8e16e6fb65cc796c~mv2.jpg/v1/fill/w_220,h_230,al_c,q_80,usm_0.66_1.00_0.01,enc_avif,quality_auto/ALBISTIN-40ML-NOVO.jpg', 17.20, 50, false),
	('Clotrimazol', '10Mg', 'Antifúngico', 'https://drogariacoop.vtexassets.com/arquivos/ids/158528/7896004711423.jpg?v=638010888471800000', 15.90, 50, false),
	('Metronidazol', '100Mg', 'Antibiótico e Antiparasitário', 'https://th.bing.com/th/id/OIP.7nFkmhAWp7clsW3AEjjxRAHaJG?cb=iwp2&rs=1&pid=ImgDetMain', 22.50, 20, true),
	('Albendazol', '400Mg', 'Antiparasitário', 'https://th.bing.com/th/id/OIP.XNPtk98rcLf2075RAsxaiAHaE8?o=7&cb=iwp2rm=3&rs=1&pid=ImgDetMain',15.57, 45, false),
	('Ivermectina', '6Mg', 'Antiparasitário', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/163658/7896714212678.png?v=637638139658600000', 20.00, 10, false),
	('Atropina', '10mg', 'Dilatação da pupila', 'https://santaluciadrogaria.vtexassets.com/arquivos/ids/167643-800-auto?v=637638253713900000&width=800&height=auto&aspect=true', 12.45, 50, false),
	('Diazepam', '10mg', 'Ansiolitico', 'https://th.bing.com/th/id/OIP.ugbZTccVQxRwiHGORSl2rAHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 16.99, 10, true),
	('Cloridrato Paroxetina', '20mg', 'Antidepressivo', 'https://www.geolab.com.br/wp-content/uploads/2023/09/cloridrato-de-paroxetina-20mg_-30-comp-850x1000.png', 50.99, 5, true),
	('Cloridrato de Venlafaxina', '75mg', 'Antidepressivo', 'https://th.bing.com/th/id/R.32828e20f77fa606bb6b1b8af64e7f10?rik=jW%2fmJVjAo%2fjy4Q&pid=ImgRaw&r=0', 40.99, 24, true),
	('Cloridrato de Duloxetina', '30mg', 'Antidepressivo', 'https://th.bing.com/th/id/OIP.CeOQaTA3XFK2brRB0KmONwHaHa?cb=iwp2&rs=1&pid=ImgDetMain', 90.99, 12, true),
	('Cloridrato de Bupropiona', '150mg', 'Antidepressivo', 'https://images.tcdn.com.br/img/img_prod/1265393/bup_150mg_60_comp_rev_14711_1_832d71fd2f8af97f6c4cc347d5a933b4.jpg', 114.25, 50, true),
	('Cloridrato de Nortriptilina', '25mg', 'Antidepressivo', 'https://images.rappi.com.br/products/f7af6e6a-0648-4b1f-bc91-c3a85fa43b97.jpg', 42.99, 40, true),
	('Cloridrato de Amitriptilina', '25mg', 'Antidepressivo', 'https://th.bing.com/th/id/OIP.LqZNimCLicZR5neU2XXF_AHaJG?o=7&cb=iwp2rm=3&rs=1&pid=ImgDetMain', 10.99, 10, true),
	('Cloridrato de Imipramina', '25mg', 'Antidepressivo', 'https://5.imimg.com/data5/SELLER/Default/2023/8/337798733/FV/XM/DS/195464121/novartis-10mg-tofranil-500x500.png', 31.99, 50, true);
