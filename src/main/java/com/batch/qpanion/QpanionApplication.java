package com.batch.qpanion;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class QpanionApplication {

    @Autowired
    public JobLauncher jobLauncher;

    @Autowired
    public Job job;

    public static void main(String[] args) {
        SpringApplication.run(QpanionApplication.class, args);
    }

    @Scheduled(cron = "${batch.cron}")
    public void excuteJob() throws Exception{

        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        jobLauncher.run(job, params);

    }

}
