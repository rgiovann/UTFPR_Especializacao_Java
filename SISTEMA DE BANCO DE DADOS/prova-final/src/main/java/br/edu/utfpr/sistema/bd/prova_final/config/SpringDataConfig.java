/**
 * Classe de configuração para configuração do Spring Data JPA e conectividade com banco de dados.
 *
 * Esta classe configura a conexão com o banco de dados, fábrica do entity manager e gerenciamento
 * de transações para um banco de dados MariaDB usando pool de conexões HikariCP. Ela habilita
 * os repositórios JPA e o gerenciamento de transações para a aplicação.
 *
 * Principais configurações incluem:
 * - Configuração do DataSource HikariCP para MariaDB
 * - Configuração do EntityManagerFactory com Hibernate como provedor JPA
 * - Configuração do gerenciamento de transações
 *
 * @author: Giovanni Leopoldo Rozza
 * @version 1.0
 * @date: 12/02/2025
 *
 * @see EnableJpaRepositories
 * @see EnableTransactionManagement
 * @see HikariDataSource
 * @see LocalContainerEntityManagerFactoryBean
 * @see PlatformTransactionManager
 */

package br.edu.utfpr.sistema.bd.prova_final.config;


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
@EnableJpaRepositories(basePackages = "br.edu.utfpr.sistema.bd.prova_final.repository")
@EnableTransactionManagement
public class SpringDataConfig {

    @Bean
    public DataSource dataSource() {
        HikariDataSource ds = null;
        try {
            ds = new HikariDataSource();
            // Alterando para MariaDB
            ds.setJdbcUrl("jdbc:mariadb://localhost:3307/prova_final");  //   porta/host/nome do  banco
            ds.setUsername("root");  // usuário do MariaDB
            ds.setPassword("admin");  // senha do seu usuário
            ds.setDriverClassName("org.mariadb.jdbc.Driver");  // Driver JDBC do MariaDB
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
        factory.setPackagesToScan("br.edu.utfpr.sistema.bd.prova_final.entity");
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

