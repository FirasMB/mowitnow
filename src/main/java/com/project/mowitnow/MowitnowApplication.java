package com.project.mowitnow;


import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan(basePackages = "com.project.mowitnow")
public class MowitnowApplication {
    /*public static void main(String[] args) {

        // app.setEnvironmen(false);
        try {
            SpringApplication app = new SpringApplication(MowitnowApplication.class);

            ConfigurableApplicationContext ctx = app.run(args);

            JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);

            Job job = ctx.getBean("mowerJob", Job.class);
            JobParameters jobParameters = new JobParametersBuilder().toJobParameters();


            JobExecution jobExecution = jobLauncher.run(job, jobParameters);
            BatchStatus batchStatus = jobExecution.getStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/
    public static void main(String[] args) {
        SpringApplication.run(MowitnowApplication.class, args);
        //System.exit(SpringApplication.exit(SpringApplication.run(MowitnowApplication.class, args)));
    }
}
