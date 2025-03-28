package br.edu.utfpr;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class SalesReader {

    private final List<Sale> sales;

    public SalesReader(String salesFile) {

        final var dataStream = ClassLoader.getSystemResourceAsStream(salesFile);

        if (dataStream == null) {
            throw new IllegalStateException("File not found or is empty");
        }

        final var builder = new CsvToBeanBuilder<Sale>(new InputStreamReader(dataStream, StandardCharsets.UTF_8));

        sales = builder
                .withType(Sale.class)
                .withSeparator(';')
                .build()
                .parse();
    }

    public BigDecimal totalOfCompletedSales() {
        return  sales.stream()
                .filter(sale->sale.getStatus().equals(Sale.Status.COMPLETED))
                .map(Sale::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal totalOfCancelledSales() {
        return  sales.stream()
                .filter(sale->sale.getStatus().equals(Sale.Status.CANCELLED))
                .map(Sale::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_EVEN);
    }

     public Optional<Sale> mostRecentCompletedSale() {

        return sales.stream()
                .filter(x->x.getStatus().equals(Sale.Status.COMPLETED))
                .max(Comparator.comparing(Sale::getSaleDate));
    }

    public long daysBetweenFirstAndLastCancelledSale() {
         List<LocalDate> dates = sales.stream()
                .filter(s -> s.getStatus().equals(Sale.Status.CANCELLED))
                .map(Sale::getSaleDate)
                .sorted()
                .toList(); // Java 16+

        return dates.isEmpty() ? 0 : dates.get(0).until(dates.get(dates.size() - 1), ChronoUnit.DAYS);
    }

    public BigDecimal totalCompletedSalesBySeller(String sellerName) {
        return  sales.stream()
                .filter(sale->sale.getSeller().equals(sellerName) && sale.getStatus().equals(Sale.Status.COMPLETED))
                .map(Sale::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    public long countAllSalesByManager(String managerName) {
        return  sales.stream()
                .filter(sale->sale.getManager().equals(managerName))
                .count();
    }

    public BigDecimal totalSalesByStatusAndMonth(Sale.Status status, Month... months) {
        return sales.stream()
                .filter(sale -> sale.getStatus().equals(status) && Arrays.asList(months).contains(sale.getSaleDate().getMonth()))
                .map(Sale::getValue)
                .reduce(BigDecimal.ZERO,BigDecimal::add)
                .setScale(2, RoundingMode.HALF_EVEN);

    }

    public Map<String, Long> countCompletedSalesByDepartment() {
        return sales.stream()
                .filter(sale -> sale.getStatus().equals(Sale.Status.COMPLETED))
                .collect(Collectors.groupingBy(Sale::getDepartment, Collectors.counting()));
    }

    public Map<Integer, Map<String, Long>> countCompletedSalesByPaymentMethodAndGroupingByYear() {
        return sales.stream()
                .filter(sale -> sale.getStatus().equals(Sale.Status.COMPLETED))
                .collect(Collectors.groupingBy(sale -> sale.getSaleDate().getYear(),
                        Collectors.groupingBy(Sale::getPaymentMethod, Collectors.counting())));
    }


    public Map<String, BigDecimal> top3BestSellers() {


        return sales.stream()
                .filter(sale -> sale.getStatus().equals(Sale.Status.COMPLETED))
                .collect(Collectors.groupingBy(Sale::getSeller,
                        Collectors.mapping(Sale::getValue, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))))
                .entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(   // Collectors.toMap() default não preserva a ordem...
                        Map.Entry::getKey,   // função para extrair a chave
                        Map.Entry::getValue, // função para extrair o valor
                        (e1, e2) -> e1,  // ... então precisamos de um merge function
                        LinkedHashMap::new // preserva a ordem
                ));


    }
}
