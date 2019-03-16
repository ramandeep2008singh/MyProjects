package com.practice.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "src/main/resources/feature/UiTests.feature",
				glue = {"com.practice.runner/MyRunner.java"})
       
        
public class MyRunner extends AbstractTestNGCucumberTests{
   
}