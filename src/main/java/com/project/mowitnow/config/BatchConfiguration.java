package com.project.mowitnow.config;

import com.project.mowitnow.MowitnowProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.io.FileSystemResource;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.File;

@Configuration
public class BatchConfiguration {

    final String CHEMIN_FICHIER = "./src/main/resources/fichier_xebia.txt";

    @Bean
    public MowitnowProcessor processor() {
        return new MowitnowProcessor();
    }


    @Bean
    @Conditional(ExecuteBatchCondition.class) // Conditional on property value
    public Job mowerJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new JobBuilder("mowerJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(processInstructionsStep(jobRepository, transactionManager))
                .end().build();
    }

    @Bean
    public FlatFileItemReader<String> itemReader() {
        return new FlatFileItemReaderBuilder<String>()
                .name("instructionItemReader")
                .resource(new FileSystemResource(new File("input.txt")))
                .lineMapper((line, lineNumber) -> line) // Simple line mapper
                .build();
    }

    /*@Bean
    public ItemWriter<? super String> itemWriter() {
        // Define your item writer logic here
        return items -> {
            for (String item : items) {
                // Process each item, e.g., write to a file or database
                System.out.println("Writing item: " + item);
            }
        };
    }*/



    @Bean
    public Step processInstructionsStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new StepBuilder("processInstructionsStep", jobRepository)
                .<String, String>chunk(10, transactionManager)
                .processor((ItemProcessor<? super String, ? extends String>) processor().process(new File(CHEMIN_FICHIER)))
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }
}