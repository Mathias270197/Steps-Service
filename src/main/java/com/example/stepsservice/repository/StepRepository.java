package com.example.stepsservice.repository;


import com.example.stepsservice.model.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepRepository extends JpaRepository<Step, Integer> {
    List<Step> findStepByFigure(String Figure);
    List<Step> findStepBystepIsClear(boolean stepIsClear);
}
