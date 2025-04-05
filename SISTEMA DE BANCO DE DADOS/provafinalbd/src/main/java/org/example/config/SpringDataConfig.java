package org.example.config;


import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableJpaRepositories(basePackages = "org.example.repository")
@EnableTransactionManagement
public class SpringDataConfig {

    @Bean
    public DataSource dataSource() {
        HikariDataSource ds = null;
        try {
            ds = new HikariDataSource();
            // Alterando para MariaDB
            ds.setJdbcUrl("jdbc:mysql://localhost:3308/prova_final");  //   porta/host/nome do  banco
            ds.setUsername("root");  // usuário do MySQL
            ds.setPassword("ranger19");  // senha do seu usuário
            ds.setDriverClassName("com.mysql.cj.jdbc.Driver");  // Driver JDBC do MySQL
        } catch (Exception ex) {
            System.err.println("Erro ao configurar o DataSource: " + ex.getMessage());
            if (ds != null) {
                ds.close(); // Fecha explicitamente em caso de erro
            }
        }

        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();

        HibernateJpaVendorAdapter vendorAdapter =
                new HibernateJpaVendorAdapter();

        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(false);
        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setJpaPropertyMap(Map.of(Environment.HBM2DDL_AUTO, "create"));// Atualiza o esquema do banco de dados
        factory.setPackagesToScan("org.example.entity");
        return factory;

    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(emf);
        manager.setJpaDialect(new HibernateJpaDialect());
        return manager;
    }
}


