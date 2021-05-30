package com.mendonca.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashMap;
import java.util.Map;

//@RestController
//@RequestMapping("/load")
public class LoadController {

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job;
	
	
	
	//@RequestMapping(value = "/",method = RequestMethod.GET)
	//@GetMapping
	public String load() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		

		   Map<String, JobParameter> maps = new HashMap<>();
	        maps.put("time", new JobParameter(System.currentTimeMillis()));
	        JobParameters parameters = new JobParameters(maps);
	        JobExecution jobExecution = jobLauncher.run(job, parameters);

	        System.out.println("JobExecution: " + jobExecution.getStatus());

	        System.out.println("Batch is Running...");
	        while (jobExecution.isRunning()) {
	            System.out.println("...");
	        }

	        return jobExecution.getStatus().toString();
	}
	
	
	
	
	
}
