package com.batch.qpanion.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job demoJob(){
        return jobBuilderFactory.get("demoJob")
                .start(stepOne())
                .build();
    }

    @Bean
    public Step stepOne(){
        return stepBuilderFactory.get("stepOne")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("MyTaskOne start..");

                    System.out.println("MyTaskOne done..");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }




}
