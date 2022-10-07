package com.example.stepsservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int stepNumber;
    private String figure;
    private boolean stepIsClear;

    public Step(){}

    public Step(int stepNumber, String figure, boolean stepIsClear){
        setStepNumber(stepNumber);
        setFigure(figure);
        setStepIsClear(stepIsClear);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStepNumber(){
        return stepNumber;
    }

    public void setStepNumber(int stepNumber){
        this.stepNumber = stepNumber;
    }

    public String getFigure(){
        return figure;
    }

    public void setFigure(String figure){
        this.figure = figure;
    }

    public boolean getStepIsClear(){
        return stepIsClear;
    }

    public void setStepIsClear(boolean stepIsClear){
        this.stepIsClear = stepIsClear;
    }
}
