package com.project.mowitnow.ressource;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
        import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
        import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
        import org.springframework.batch.core.repository.JobRestartException;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobResource {

    /*@Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;*/

    @Autowired
    private ConfigurableApplicationContext ctx;

    @PostMapping("/mowitnow")
    public ResponseEntity<String> mowitnowResource() {

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
        Job job = ctx.getBean("mowerJob", Job.class);
        try {
            JobExecution jobExecution = jobLauncher.run(job, jobParameters);
            BatchStatus batchStatus = jobExecution.getStatus();
            return ResponseEntity.ok("Job started with status: " + batchStatus);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to start job: " + e.getMessage());
        }
    }

    /*@PostMapping("/mowitnow")
    public void mowitnowResource() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }*/

}
