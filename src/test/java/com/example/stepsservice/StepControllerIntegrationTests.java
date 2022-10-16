package com.example.stepsservice;


import com.example.stepsservice.model.Step;
import com.example.stepsservice.repository.StepRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class StepControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StepRepository stepRepository;

    private Step stepDuck1 = new Step(1, "Duck", true);
    private Step stepDuck2 = new Step(2, "Duck", false);
    private Step stepChicken1 = new Step(1, "Chicken", false);
    private Step stepChicken2 = new Step(2, "Chicken", true);

    @BeforeEach
    public void beforeAllTests() {
        stepRepository.deleteAll();
        stepRepository.save(stepDuck1);
        stepRepository.save(stepDuck2);
        stepRepository.save(stepChicken1);
        stepRepository.save(stepChicken2);
    }

    @AfterEach
    public void afterAllTests() {
        //Watch out with deleteAll() methods when you have other data in the test database!
        stepRepository.deleteAll();
    }



//    @GetMapping("/steps/stepIsClear/{stepIsClear}")
//    public List<Step> getStepsByStepIsClear(@PathVariable boolean stepIsClear){
//        return stepRepository.findStepBystepIsClear(stepIsClear);
//    }

    @Test
    public void givenSteps_whenGetStepsByFigure_thenReturnJson() throws Exception {

        mockMvc.perform(get("/steps/figure/{figure}","Duck"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].stepNumber",is(1)))
                .andExpect(jsonPath("$[1].stepNumber",is(2)))
                .andExpect(jsonPath("$[0].figure",is("Duck")))
                .andExpect(jsonPath("$[1].figure",is("Duck")))
                .andExpect(jsonPath("$[0].stepIsClear",is(true)))
                .andExpect(jsonPath("$[1].stepIsClear",is(false)));
    }


    //    @GetMapping("/steps/stepIsClear/{stepIsClear}")
//    public List<Step> getStepsByStepIsClear(@PathVariable boolean stepIsClear){
//        return stepRepository.findStepBystepIsClear(stepIsClear);
//    }


    @Test
    public void givenSteps_whenGetStepsByStepIsClear_thenReturnJson() throws Exception {

        mockMvc.perform(get("/steps/stepIsClear/{stepIsClear}","true"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].stepNumber",is(1)))
                .andExpect(jsonPath("$[1].stepNumber",is(2)))
                .andExpect(jsonPath("$[0].figure",is("Duck")))
                .andExpect(jsonPath("$[1].figure",is("Chicken")))
                .andExpect(jsonPath("$[0].stepIsClear",is(true)))
                .andExpect(jsonPath("$[1].stepIsClear",is(true)));
    }

}
