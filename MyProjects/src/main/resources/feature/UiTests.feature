Feature: This feature file contains the Test Scenarios for GFK Assignment, includes (Signup, Login and Checkout): Portal: "http://automationpractice.com/index.php" 

@SignUp @regression @EndToEnd 
Scenario: Sign up to the portal 
	Given I have opened the chrome browser 
	And I have loaded the url http://automationpractice.com/index.php  
	When user clicks on Sign in button
	#generate random email adresses
	And user enters email 
	And user clicks on Create an account button
	When user fills in information in the field YOUR PERSONAL INFORMATION
	And user fills in information in the field YOUR ADDRESS 
	And user clicks on Register button 
	Then verify that the correct url with appender "?controller=my-account" is loaded 
	And verify the username is formed by concatenating FirstName and LastName 
	And verify the Sign out button is present 
	And verify that MY ACCOUNT header is present 
	And verify that welcome message is also present