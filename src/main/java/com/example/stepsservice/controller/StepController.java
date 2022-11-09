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
            stepRepository.save(new Step(1, "Apple", true));
            stepRepository.save(new Step(2, "Apple", true));
            stepRepository.save(new Step(3, "Apple", false));
            stepRepository.save(new Step(1, "House", true));
            stepRepository.save(new Step(2, "House", false));
            stepRepository.save(new Step(3, "House", true));
            stepRepository.save(new Step(1, "Robot", true));
            stepRepository.save(new Step(1, "Robot", true));
            stepRepository.save(new Step(2, "Robot", true));
            stepRepository.save(new Step(3, "Robot", false));
            stepRepository.save(new Step(4, "Robot", true));
            stepRepository.save(new Step(5, "Robot", true));
            stepRepository.save(new Step(6, "Robot", true));
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
