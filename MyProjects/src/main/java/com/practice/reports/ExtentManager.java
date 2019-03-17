package com.practice.reports;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    
	// This is an inbuilt extent class
    private static ExtentReports extent;
    public static String screenshotFolderPath;
    
    // getInstance is a method inside ExtentReports class and we need to provide the path in which reports will be generated
    public static ExtentReports getInstance(String reportPath) {
    	if (extent == null){
    		// if entent object is null then, generate report folder with below name.html
    		String fileName="Report.html";
    		Date d = new Date();
    		String folderName=d.toString().replace(":", "_");
    		
    		// directory of the report folder
    		new File(reportPath+folderName+"//screenshots").mkdirs();
    		
    		reportPath=reportPath+folderName+"//";
    		screenshotFolderPath=reportPath+"screenshots//";
    		System.out.println(reportPath+fileName);
    		// This method has the settings of the report
    		createInstance(reportPath+fileName);
    	}
    	
        return extent;
    }
    
    public static ExtentReports createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Reports");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Reports - GFK Automation Testing");
        
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
        return extent;
    }
}