INSERT INTO departamento(nome, codigo_da_folha_de_pagamento, status) 
VALUES('departamento1', 1, 'ATIVO');

INSERT INTO departamento(nome, codigo_da_folha_de_pagamento, status) 
VALUES('departamento2', 2, 'ATIVO');

INSERT INTO departamento(nome, codigo_da_folha_de_pagamento, status) 
VALUES('departamento3', 3, 'ATIVO');



INSERT INTO funcionario(nome, numero_de_registro, cpf, data_de_admissao, salario, status, data_de_desligamento, genero, departamento_id, lider_id) 
VALUES('funcUm', 1, '123456789-11', '2000-01-01', 2000.00, 'ATIVO', null, 'MASCULINO', 1, null);

INSERT INTO funcionario(nome, numero_de_registro, cpf, data_de_admissao, salario, status, data_de_desligamento, genero, departamento_id, lider_id) 
VALUES('funcDois', 2, '111222333-44', '1999-01-01', 2000.00, 'ATIVO', null, 'FEMININO', 1, 1);

INSERT INTO funcionario(nome, numero_de_registro, cpf, data_de_admissao, salario, status, data_de_desligamento, genero, departamento_id, lider_id) 
VALUES('funcTres', 3, '111222333-55', '1998-01-01', 2000.00, 'ATIVO', null, 'MASCULINO', 2, 1);


INSERT INTO competencia(conceituacao, nome, tipo) 
VALUES('conceituacaoUm', 'competenciaUm', 'LIDERANCA');

INSERT INTO competencia(conceituacao, nome, tipo) 
VALUES('conceituacaoDois', 'competenciaDois', 'ORGANIZACIONAL');

INSERT INTO competencia(conceituacao, nome, tipo) 
VALUES('conceituacaoTres', 'competenciaTres', 'OPERACIONAL');


INSERT INTO feedback(data_de_ocorrencia, data_de_registro_do_feedback, evento, tipo, autor_id, competencia_id, subordinado_id) 
VALUES ('1999-01-01', '27-06-2022', 'evento ocorrido', 'ELOGIO', 1, 1, 2);

INSERT INTO feedback(data_de_ocorrencia, data_de_registro_do_feedback, evento, tipo, autor_id, competencia_id, subordinado_id) 
VALUES ('1999-01-01', '27-06-2020', 'evento ocorrido', 'ELOGIO', 1, 1, 2);

