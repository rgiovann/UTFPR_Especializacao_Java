use dml;

# RESPOSTAS

# QUESTÃO 01

SELECT * 
FROM (SELECT 
    cantor.nome_cantor, 
    COUNT(gravacao.cod_cantor) AS num_gravacoes
FROM 
    gravacao
INNER JOIN 
    cantor USING(cod_cantor)
GROUP BY 
    cantor.nome_cantor) AS lista_gravacoes
WHERE num_gravacoes = (
    SELECT MIN(total) 
    FROM (
        SELECT COUNT(cod_cantor) AS total 
        FROM gravacao 
        GROUP BY cod_cantor
    ) AS menor_nr_gravacoes
)
ORDER BY nome_cantor ASC;




	
# QUESTÃO 02 (--- ERRADA---- ) 
# No script, nr_gravadoras_por_cantor não é uma tabela real, mas sim um subquery # # nomeado (alias) dentro da consulta.
#no entanto, não é possível referenciar diretamente um alias dentro do próprio nível# de subconsulta.

SELECT * 
FROM (SELECT nome_cantor, COUNT(DISTINCT cod_gravadora) AS num_gravadoras
FROM ( SELECT 
    cantor.nome_cantor, 
    gravacao.cod_gravadora
FROM 
    gravacao
INNER JOIN 
    cantor USING(cod_cantor)
GROUP BY cantor.nome_cantor,gravacao.cod_gravadora) AS lista_gravadoras
GROUP BY nome_cantor) AS nr_gravadoras_por_cantor
WHERE num_gravadoras = (
    SELECT MAX(num_gravadoras) 
FROM nr_gravadoras_por_cantor );  


# SOLUCAO UESTÃO 03  (--- CORRETA ---- ) REPETIR A SUBQUERY 
# nr_gravadoras_por_cantor


SELECT nome_cantor, num_gravadoras 
FROM (
    SELECT nome_cantor, COUNT(cod_gravadora) AS num_gravadoras
    FROM (
        SELECT 
            cantor.nome_cantor, 
            gravacao.cod_gravadora
        FROM gravacao
        INNER JOIN cantor USING(cod_cantor)
        GROUP BY cantor.nome_cantor, gravacao.cod_gravadora
    ) AS lista_gravadoras
    GROUP BY nome_cantor
) AS nr_gravadoras_por_cantor
WHERE num_gravadoras = (
    SELECT MAX(num_gravadoras)
    FROM (
        SELECT COUNT(cod_gravadora) AS num_gravadoras
        FROM (
            SELECT 
                cantor.nome_cantor, 
                gravacao.cod_gravadora
            FROM gravacao
            INNER JOIN cantor USING(cod_cantor)
            GROUP BY cantor.nome_cantor, gravacao.cod_gravadora
        ) AS lista_gravadoras
        GROUP BY nome_cantor
    ) AS maximo_gravadoras
);

# QUESTÃO 03 (--- ERRADA---- ) 
# No script, media_duracao_por_cantor não é uma tabela real, mas sim um subquery # # nomeado (alias) dentro da consulta.
#no entanto, não é possível referenciar diretamente um alias dentro do próprio nível# de subconsulta.

SELECT nome_cantor, media FROM media_duracao_por_cantor
WHERE media = (SELECT MAX(media) 
    FROM (SELECT cantor.nome_cantor, AVG(musica.duracao) AS media
FROM 
    gravacao
INNER JOIN cantor ON cantor.cod_cantor = gravacao.cod_cantor
INNER JOIN musica ON musica.cod_musica = gravacao.cod_musica
GROUP BY nome_cantor) AS media_duracao_por_cantor); 

# SOLUCAO UESTÃO 03  (--- CORRETA ---- ) 
# REPETIR A SUBQUERY media_duracao_por_cantor

SELECT nome_cantor, media FROM (SELECT cantor.nome_cantor, AVG(musica.duracao) AS media
FROM 
    gravacao
INNER JOIN cantor ON cantor.cod_cantor = gravacao.cod_cantor
INNER JOIN musica ON musica.cod_musica = gravacao.cod_musica
GROUP BY nome_cantor) AS media_duracao_por_cantor1
WHERE media = (SELECT MAX(media) 
    FROM (SELECT cantor.nome_cantor, AVG(musica.duracao) AS media
FROM 
    gravacao
INNER JOIN cantor ON cantor.cod_cantor = gravacao.cod_cantor
INNER JOIN musica ON musica.cod_musica = gravacao.cod_musica
GROUP BY nome_cantor) AS media_duracao_por_cantor2); 

	
	
# QUESTAO 04

SELECT DISTINCT cantor.nome_cantor
FROM cantor
WHERE cantor.cod_cantor NOT IN (
    SELECT gravacao.cod_cantor
    FROM gravacao
    INNER JOIN gravadora ON gravacao.cod_gravadora = gravadora.cod_gravadora
    WHERE gravadora.nome_gravadora = 'Sony'
);

# QUESTAO 05

SELECT cantor.nome_cantor,musica.titulo AS musica, gravacao.data_gravacao
FROM  gravacao
INNER JOIN cantor ON cantor.cod_cantor = gravacao.cod_cantor
INNER JOIN musica ON musica.cod_musica = gravacao.cod_musica
WHERE YEAR(gravacao.data_gravacao) = 2004;


#QUESTAO O6

SELECT cantor.nome_cantor, IFNULL(MAX(gravacao.data_gravacao), '') AS data_ultima_gravacao
FROM  gravacao
RIGHT JOIN cantor ON cantor.cod_cantor = gravacao.cod_cantor
GROUP BY cantor.nome_cantor
ORDER BY gravacao.data_gravacao DESC;


#QUESTAO 07

SELECT 
    pessoa.nome_pessoa AS nome,
    MAX(CASE 
        WHEN fone.tipo = 'R' THEN fone.numero
        END) AS fone_residencial,
    MAX(CASE 
        WHEN fone.tipo = 'C' THEN fone.numero
        END) AS fone_comercial,
    MAX(CASE 
        WHEN fone.tipo = 'L' THEN fone.numero
        END) AS celular
FROM 
    pessoa
INNER JOIN 
    fone  ON pessoa.cod_pessoa = fone.cod_pessoa
GROUP BY 
    pessoa.cod_pessoa
ORDER BY 
    pessoa.cod_pessoa;
 