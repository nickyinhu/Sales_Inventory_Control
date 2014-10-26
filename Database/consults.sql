----                CONSULTAS           -----
----RETORNA TUDO O QUE A PESSOA GASTOU
SELECT co.clientes_id, cl.nome, sum(valor) AS total
 FROM compras AS co 
 JOIN clientes AS cl on(co.clientes_id = cl.id)
 GROUP BY co.clientes_id,cl.nome
 ORDER BY co.clientes_id 
;


-----JUNTAR ISSO 
---- PÁGINA DE RELATÓRIO DIÁRIO
SELECT t2.nome, t1.valor, t1.data_compra, sum(t1.valor) AS soma
	FROM compras  AS t1
	JOIN clientes AS t2 ON (t1.clientes_id = t2.id)
	WHERE t1.data_compra = CURRENT_DATE
	GROUP BY t2.nome, t1.valor, t1.data_compra
;

SELECT sum(valor)
	FROM compras
	WHERE data_compra = CURRENT_DATE
;

--- não é sql : COLOCAR MÁSCARA
MaskFormatter cpf = new javax.swing.text.MaskFormatter("###.###.###-##");
textoCPF = new javax.swing.JFormattedTextField(cpf);

