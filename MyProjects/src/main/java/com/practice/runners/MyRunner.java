package com.practice.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		dryRun=false,
		strict=false,
		monochrome=false,
		features= {"src/main/resources/"},
		glue = {"com.practice.StepDefinitions"},
		format = {"pretty"},
		tags = {"@SignUp"}
		)

public class MyRunner {

}