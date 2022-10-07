package com.example.stepsservice.controller;


import com.example.stepsservice.model.Step;
import com.example.stepsservice.repository.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class StepController {

    @Autowired
    private StepRepository stepRepository;

    @PostConstruct
    public void fillDB(){
        if(stepRepository.count() == 0){
            stepRepository.save(new Step(1, "duck", true));
            stepRepository.save(new Step(2, "duck", false));
            stepRepository.save(new Step(1, "chicken", false));
            stepRepository.save(new Step(2, "chicken", true));
        }

        System.out.println(stepRepository.findStepByFigure("duck"));
    }

    @GetMapping("/steps/figure/{figure}")
    public List<Step> getStepsByFigure(@PathVariable String figure){
        return stepRepository.findStepByFigure(figure);
    }

    @GetMapping("/steps/stepIsClear/{stepIsClear}")
    public List<Step> getStepsByStepIsClear(@PathVariable boolean stepIsClear){
        return stepRepository.findStepBystepIsClear(stepIsClear);
    }
}
