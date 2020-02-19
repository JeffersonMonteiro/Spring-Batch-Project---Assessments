package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    public class UsersRowMapper implements RowMapper<Users> {

        @Override
        public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
            Users user = new Users();
            user.setUserId(rs.getLong(1));
            user.setName(rs.getString(2));
            user.setDept(rs.getString(3));
            user.setAccount(rs.getBigDecimal(4));
            return user;
        }
    }

    @Bean
    public FlatFileItemReader<Users> reader() {
        return new FlatFileItemReaderBuilder<Users>().name("userItemReader")
                .resource(new ClassPathResource("user-data.csv")).delimited().names(new String[]{"userId", "name", "dept", "account"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Users>() {{
                    setTargetType(Users.class);
                }}).build();
    }

    @Bean
    public JdbcCursorItemReader<Users> dbReader() {
        JdbcCursorItemReader<Users> dbreader = new JdbcCursorItemReader<Users>();
        dbreader.setDataSource(dataSource);
        dbreader.setSql("SELECT user_id, user_name, dept, account FROM tbReadUsers");
        dbreader.setRowMapper(new UsersRowMapper());
        return dbreader;
    }

    @Bean
    public UserItemProcessor processor() {
        return new UserItemProcessor();
    }


    @Bean
    public JdbcBatchItemWriter<Users> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Users>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO tbUsers (user_id, user_name, dept, account) VALUES (:userId, :name, :dept, :account)")
                .dataSource(dataSource)
                .build();
        
    }

    @Bean
    public FlatFileItemWriter<Users> fWriter() {
        FlatFileItemWriter<Users> fWriter = new FlatFileItemWriter<Users>();

        fWriter.setName("fWriter");
        fWriter.setResource(new ClassPathResource("data-from-db.csv"));
        fWriter.setLineAggregator(new DelimitedLineAggregator<Users>() {{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<Users>() {{
                setNames(new String[]{"userId", "name", "dept", "account"});
            }});
        }});
        return fWriter;
    }

    @Bean
    public Job importExportUserJob(JobCompletionNotificationListener listener, Step step1, Step step2) {
        return jobBuilderFactory.get("importExportUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .next(step2)
                .end()
                .preventRestart()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Users> writer) {
        return stepBuilderFactory.get("step1")
                .<Users, Users>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

    @Bean
    public Step step2(JdbcCursorItemReader<Users> dbReader) {
        return stepBuilderFactory.get("step2")
                .<Users, Users>chunk(10)
                .reader(dbReader())
                .processor(processor())
                .writer(fWriter())
                .build();
    }

}