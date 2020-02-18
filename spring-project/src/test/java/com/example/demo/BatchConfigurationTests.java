package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBatchTest
public class BatchConfigurationTests {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

//    @Autowired
//    private Job job;

    @Test
    public void step1CompletedTest(){
        //jobLauncherTestUtils = new JobLauncherTestUtils();

        JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");
        Assertions.assertEquals(jobExecution.getStatus(), BatchStatus.COMPLETED);


    }

    @Test
    public void step2CompletedTest(){
        //jobLauncherTestUtils = new JobLauncherTestUtils();

        JobExecution jobExecution = jobLauncherTestUtils.launchStep("step2");
        Assertions.assertEquals(jobExecution.getStatus(), BatchStatus.COMPLETED);


    }
}
