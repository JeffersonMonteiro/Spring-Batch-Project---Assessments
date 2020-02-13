package com.example.demo;

import org.apache.catalina.User;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    // ESTA LENDO ARQUIVO PARA ENVIAR AO BANCO
    // INVERTER

    @Bean
    public FlatFileItemReader<Users>  reader(){
        return new FlatFileItemReaderBuilder<Users>().name("userItemReader")
                .resource(new ClassPathResource("user-data.csv")).delimited().names(new String[]{"name", "dept", "account"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Users>(){
                    public void setTargetType(Class<? extends Users> type) {
                        setTargetType(Users.class);
                    }}).build();
    }

    @Bean
    public UserItemProcessor processor() {
        return new UserItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Users> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Users>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO people (user_name, dept, account) VALUES (:name, :dept, :account)")
                .dataSource(dataSource)
                .build();
    }



}
