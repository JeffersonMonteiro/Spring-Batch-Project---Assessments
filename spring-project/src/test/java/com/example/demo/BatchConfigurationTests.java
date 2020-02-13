package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.batch.runtime.BatchStatus;

@RunWith(SpringRunner.class)
@SpringBatchTest
public class BatchConfigurationTests {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    public void step1CompletedTest(){
        jobLauncherTestUtils = new JobLauncherTestUtils();

        JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");
        Assert.assertEquals(jobExecution.getStatus(), BatchStatus.COMPLETED);
    }
}
