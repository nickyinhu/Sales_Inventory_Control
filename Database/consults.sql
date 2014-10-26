SELECT co.client, cl.nome, sum(value) AS total
 FROM shopping AS co 
 JOIN client AS cl on(co.client = cl.id)
 GROUP BY co.client,cl.nome
 ORDER BY co.client 
;


SELECT t2.nome, t1.value, t1.data_shopping, sum(t1.value) AS soma
	FROM shopping  AS t1
	JOIN client AS t2 ON (t1.client = t2.id)
	WHERE t1.data_shopping = CURRENT_DATE
	GROUP BY t2.nome, t1.value, t1.data_shopping
;

SELECT sum(value)
	FROM shopping
	WHERE data_shopping = CURRENT_DATE
;

MaskFormatter cpf = new javax.swing.text.MaskFormatter("###.###.###-##");
textoCPF = new javax.swing.JFormattedTextField(cpf);

