# Análise de Vendas com Java e OpenCSV

## Descrição

Este projeto tem como objetivo processar e analisar dados de vendas a partir de um arquivo CSV. Utilizando Java e a biblioteca OpenCSV, os registros são carregados em memória e transformados em um fluxo de dados para responder a diversas questões analíticas sobre as vendas.

## Tecnologias Utilizadas

* **Java 17+**

* **OpenCSV** (para leitura do CSV)

* **Stream API** (para processamento eficiente dos dados)

## Estrutura do Código

### 1. `SalesReader.java`

Esta classe é responsável por:

* Ler o arquivo CSV e convertê-lo em uma lista de objetos `Sale`.

* Implementar métodos para análise de vendas, como:

  * **totalOfCompletedSales()**: Retorna o total de vendas concluídas.

  * **totalOfCancelledSales()**: Retorna o total de vendas canceladas.

  * **mostRecentCompletedSale()**: Obtém a venda concluída mais recente.

  * **daysBetweenFirstAndLastCancelledSale()**: Calcula o intervalo de dias entre a primeira e a última venda cancelada.

  * **totalCompletedSalesBySeller(String sellerName)**: Retorna o total de vendas concluídas por um vendedor.

  * **countAllSalesByManager(String managerName)**: Conta todas as vendas associadas a um gerente.

  * **totalSalesByStatusAndMonth(Sale.Status status, Month... months)**: Calcula o total de vendas por status e mês.

  * **countCompletedSalesByDepartment()**: Conta as vendas concluídas por departamento.

  * **countCompletedSalesByPaymentMethodAndGroupingByYear()**: Conta as vendas concluídas agrupadas por método de pagamento e ano.

  * **top3BestSellers()**: Retorna os três melhores vendedores com base no valor das vendas concluídas.

### 2. `LocalDateConverter.java`

Esta classe converte datas no formato `dd/MM/yyyy` para o tipo `LocalDate`, permitindo que a OpenCSV manipule corretamente os dados temporais do CSV.

## Como Executar

1. **Compilar o projeto**

   * Certifique-se de que o Java está instalado e configurado.

   * Compile as classes com:

     ```sh
     javac -cp "path_para_opencsv.jar" br/edu/utfpr/*.java
     ```

2. **Executar o programa**

   * Execute a classe principal e forneça o caminho do arquivo CSV:

     ```sh
     java -cp ".:path_para_opencsv.jar" br.edu.utfpr.SalesReader "arquivo.csv"
     ```

## Considerações

* O código utiliza **Streams e Collectors** para otimizar a análise de grandes volumes de dados.

* A manipulação de **BigDecimal** garante precisão nos cálculos financeiros.

* O uso de **Map e GroupingBy** permite agrupamentos eficientes para diversas análises.


**Autor:** \Giovanni Leopoldo Rozza\
**Disciplina:** \CETEJ32 - Linguagem De Programação Java II\
**Professor:** \Arthur P. Gregório\
**Universidade:** \UTFPR-Campus Cornélio Procópio\
