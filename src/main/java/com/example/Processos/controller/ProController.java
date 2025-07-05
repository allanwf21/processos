package com.example.Processos.controller;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/importacao")
public class ProController {

    private JobLauncher jobLauncher;
    private Job job;


    public ProController(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @GetMapping
     public ResponseEntity<String> importar () {
        try {
            if (job == null) {
                throw new Exception("Erro interno banco de dados.");
            }
            JobParameters jobParameters = new JobParameters();
            jobLauncher.run(job, jobParameters);


        } catch (Exception e) {
            System.out.println("Job failed with the folling exception:" + e.getMessage());
            e.printStackTrace();

        }
        return ResponseEntity.status(HttpStatus.OK).body("Executado");

        }
    }

