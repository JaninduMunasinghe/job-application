package com.embarkx.firstjobapp.job.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.job.JobService;

@Service
public class JobServiceImpl implements JobService{

    private List<Job> jobs = new ArrayList<>();

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        jobs.add(job);
    }
    
}
