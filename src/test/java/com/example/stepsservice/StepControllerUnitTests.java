package com.example.stepsservice;

import com.example.stepsservice.model.Step;
import com.example.stepsservice.repository.StepRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StepControllerUnitTests {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StepRepository stepRepository;

    private ObjectMapper mapper = new ObjectMapper();

    private Step stepDuck1 = new Step(1, "Duck", true);
    private Step stepDuck2 = new Step(2, "Duck", false);
    private Step stepChicken1 = new Step(1, "Chicken", false);
    private Step stepChicken2 = new Step(2, "Chicken", true);




    @Test
    public void givenSteps_whenGetStepsByFigure_thenReturnJson() throws Exception {

        List<Step> stepList = new ArrayList<>();
        stepList.add(stepChicken1);
        stepList.add(stepChicken2);

        given(stepRepository.findStepByFigure(stepChicken1.getFigure())).willReturn(stepList);

        mockMvc.perform(get("/steps/figure/{figure}", stepChicken1.getFigure()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].stepNumber",is(1)))
                .andExpect(jsonPath("$[1].stepNumber",is(2)))
                .andExpect(jsonPath("$[0].figure",is("Chicken")))
                .andExpect(jsonPath("$[1].figure",is("Chicken")))
                .andExpect(jsonPath("$[0].stepIsClear",is(false)))
                .andExpect(jsonPath("$[1].stepIsClear",is(true)));
    }


//    @GetMapping("/steps/stepIsClear/{stepIsClear}")
//    public List<Step> getStepsByStepIsClear(@PathVariable boolean stepIsClear){
//        return stepRepository.findStepBystepIsClear(stepIsClear);
//    }

    @Test
    public void givenSteps_whenGetStepsByStepIsClear_thenReturnJson() throws Exception {

        List<Step> stepList = new ArrayList<>();
        stepList.add(stepDuck1);
        stepList.add(stepChicken2);

        given(stepRepository.findStepBystepIsClear(true)).willReturn(stepList);

        mockMvc.perform(get("/steps/stepIsClear/{stepIsClear}", true))
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
