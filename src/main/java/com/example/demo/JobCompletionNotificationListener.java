package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution){
        if(jobExecution.getStatus() == BatchStatus.COMPLETED){
            log.info("Job finished!");
            jdbcTemplate.query("SELECT user_id, user_name, dept, account FROM tbUsers", (rs, row)
                    -> new Users(rs. getLong(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getBigDecimal(4))).forEach(user -> log.info("Found <" + user + "> in the database"));
        }
    }
}
