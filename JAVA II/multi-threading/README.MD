# Corrida de Motos - Java II (UTFPR)

## Descrição da Atividade

Este projeto tem como objetivo exercitar os conceitos de **Race Condition** e **Critical Sections** utilizando sincronização de métodos e/ou objetos para simular uma corrida de motocicletas. O vencedor do campeonato é aquele que acumular mais pontos ao acessar a região crítica (linha de chegada).

## Requisitos da Implementação

A atividade deve atender aos seguintes requisitos:

1. Criar no mínimo **10 threads**, cada uma representando um competidor com o nome `Competidor #N`.

2. Realizar **no mínimo 10 corridas**.

3. Cada corrida só inicia após o término da anterior (quando todos os competidores cruzarem a linha de chegada).

4. O placar deve ser atualizado conforme os competidores cruzam a linha de chegada, distribuindo pontos da seguinte forma:

   * O primeiro a chegar recebe **10 pontos**.
   * O segundo recebe **9 pontos**.
   * O terceiro recebe **8 pontos**.
   * E assim sucessivamente, até o último competidor receber **1 ponto**.

5. O vencedor do campeonato será aquele que acumular **mais pontos ao final das 10 corridas**.

6. Ao final do campeonato, apresentar o **placar final** e o **pódio** dos melhores competidores.

## Estratégia Utilizada

A implementação utiliza **threads** para simular os competidores e um recurso compartilhado para gerenciar a competição. Cada competidor é representado por uma thread separada que aguarda o início da corrida, corre e cruza a linha de chegada de maneira concorrente. A sincronização é utilizada para garantir que os competidores cruzem a linha de chegada de forma ordenada e segura, evitando **race conditions**. Para isso:

* Um monitor ( `RecursoCompeticao`) é usado para coordenar o início e o fim de cada corrida.
* Um mecanismo de **wait/notify** permite que as threads aguardem o início da corrida e notifiquem a finalização.
* Os pontos são atribuídos de acordo com a ordem de chegada, garantindo que todos os competidores participem de um campeonato justo.
* O pódio e a tabela de pontos são exibidos ao final, levando em consideração eventuais empates.

## Estrutura do Código

O programa deve ser implementado em **apenas uma classe**, utilizando **classes aninhadas** para organização do código.

## Execução

Para rodar o programa, utilize um ambiente Java compatível (JDK 11 ou superior) e execute:

```sh
javac CorridaDeMotos.java
java CorridaDeMotos
```

## Autor

Atividade desenvolvida para a disciplina **Java II** da pós graduação em **Linguagem Java UTFPR - Campus Cornélio Procópio**.
