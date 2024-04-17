package com.project.mowitnow.config;

import com.project.mowitnow.MowitnowProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.File;
import java.util.List;

@Configuration
public class BatchConfiguration {

    final String CHEMIN_FICHIER = "./src/main/resources/fichier_xebia.txt";

    public ItemProcessor<File, List<String>> processor() {
        return new MowitnowProcessor();
    }

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:org/springframework/batch/core/schema-drop-h2.sql")
                .addScript("classpath:org/springframework/batch/core/schema-h2.sql")
                .build();
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
    public FlatFileItemReader<File> itemReader() {
        return new FlatFileItemReaderBuilder<File>()
                .name("instructionItemReader")
                .resource(new FileSystemResource(new File(CHEMIN_FICHIER)))
                .lineMapper((line, lineNumber) -> new File(CHEMIN_FICHIER)) // Use a PassThroughLineTokenizer to directly map lines to File objects // Dummy line mapper since we're not actually using the lines
                .build();
    }

    @Bean
    public ItemWriter<List<String>> itemWriter() {
        return items -> {
            for (List<String> item : items) {
                System.out.println("result: " + item);
            }
        };
    }



    @Bean(name = "processInstructionsStep")
    public Step processInstructionsStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
        return new StepBuilder("processInstructionsStep", jobRepository)
                .<File, List<String>>chunk(1, transactionManager)
                .reader(itemReader())
                .processor(processor())
                .writer(itemWriter())
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