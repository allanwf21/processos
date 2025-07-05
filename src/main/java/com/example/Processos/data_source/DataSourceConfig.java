package com.example.Processos.data_source;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

// ###### BANCO BATCH (H2) #######
   @Bean
   @Primary
   @ConfigurationProperties(prefix = "spring.datasource")
    DataSource datasource() {
       return DataSourceBuilder.create().build();
   }


    // ###### BANCO SQL #######

    @Bean(name = DataSourceQualifiers.SQL_JDBC_CLIENT)
    @ConfigurationProperties(prefix = "sql.datasource")
    DataSource sqldatasource() {
        return DataSourceBuilder.create().build();
    }


    // ###### BANCO ORACLE #######

    @Bean(name = DataSourceQualifiers.ORACLE_JDBC_CLIENT)
    @ConfigurationProperties(prefix = "oracle.datasource")
    DataSource oracleDataSource() {
        return DataSourceBuilder.create().build();
    }


}
