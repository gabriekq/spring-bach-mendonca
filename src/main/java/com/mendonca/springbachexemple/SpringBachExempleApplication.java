package com.mendonca.springbachexemple;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.mendonca.batch.DBWriter;
import com.mendonca.batch.Processor;
import com.mendonca.config.SpringBatchConfig;
import com.mendonca.controller.LoadController;
import com.mendonca.models.User;
import com.mendonca.repository.UserRepository;


//LoadController.class
//@SpringBootApplication
@SpringBootApplication

@EnableJpaRepositories(basePackageClasses = {UserRepository.class})
@EntityScan(basePackageClasses =  {User.class})
@ComponentScan(basePackageClasses = {Processor.class,DBWriter.class,SpringBatchConfig.class})
@EnableAutoConfiguration
@EnableScheduling
public class SpringBachExempleApplication {

	     @Autowired
	     private JobLauncher jobLauncher;
	
	    @Autowired
	    private Job job;
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBachExempleApplication.class, args);
	}

	
	 @Scheduled(cron = "*/10 * * * * *")
	    public void perform() throws Exception{
		 
		 Map<String, JobParameter> maps = new HashMap<>();
	        maps.put("time", new JobParameter(System.currentTimeMillis()));
	        JobParameters parameters = new JobParameters(maps);
	        JobExecution jobExecution = jobLauncher.run(job, parameters);

	        System.out.println("JobExecution: " + jobExecution.getStatus());

	        System.out.println("Batch is Running...");
	        while (jobExecution.isRunning()) {
	            System.out.println("...");
	        }
		 
		 
	    }
	
	
	
	
	
	
}
