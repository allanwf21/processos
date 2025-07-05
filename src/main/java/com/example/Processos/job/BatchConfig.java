package com.example.Processos.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.Processos.data_source.DataSourceQualifiers;
import com.example.Processos.model.Client;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {

          private final JobRepository jobRepository;
          private final PlatformTransactionManager  transactionManager;

          public BatchConfig(JobRepository jobRepository, PlatformTransactionManager  transactionManager ){
              this.jobRepository = jobRepository;
              this.transactionManager = transactionManager;
          }

    @Bean
    public JdbcCursorItemReader<Client> reader(@Qualifier (DataSourceQualifiers.ORACLE_JDBC_CLIENT)
                                               DataSource datasource){
              return new JdbcCursorItemReaderBuilder<Client>()
                      .dataSource(datasource)
                      .name("clientReader")
                      .sql("SELECT * FROM EXTRACAO_PRO WHERE INT_PRO = 'N'")
                      .rowMapper(new BeanPropertyRowMapper<>(Client.class))
                      .build();
    }

    @Bean
    public JdbcBatchItemWriter<Client> writer (
            @Qualifier(DataSourceQualifiers.SQL_JDBC_CLIENT) DataSource datasource) {
              return new JdbcBatchItemWriterBuilder<Client>()
                      .dataSource(datasource)
                      .sql("""
                              INSERT INTO EXTRACAO_PRO (
                              id, nome, cpf, email, int_pro
                              ) VALUES (
                              :id, :nome, :cpf, :email, :int_pro
                              );
                              """)
                      .beanMapped()
                      .build();
    }

    @Bean
    public Step stepCarga(ItemReader<Client> reader,
                          ItemWriter<Client> writer){
        return new StepBuilder("Step", jobRepository)
                .<Client, Client>chunk(10000, transactionManager)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public Job job (Step stepCarga){
        return new JobBuilder("job",jobRepository)
                .start(stepCarga)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
