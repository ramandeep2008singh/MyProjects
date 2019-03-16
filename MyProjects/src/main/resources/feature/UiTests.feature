Feature: This feature file contains the Test Scenarios for GFK Assignment, includes (Signup, Login and Checkout): Portal: "http://automationpractice.com/index.php" 

Background: 
	Given I have opened the firefox browser
	And I have loaded the url "http://automationpractice.com/index.php"
	
@SignUp @regression @EndToEnd 
Scenario: Sign up to the portal 
	Given The portal is opened
	When user clicks on Sign in button
	Then Authentication Page opens 
	#generate random email adresses
	When user enters email address 
	And user clicks on Create an account button
	Then CREATE AN ACCOUNT page opens
	When user fills in information in the field YOUR PERSONAL INFORMATION
	And user fills in information in the field YOUR ADDRESS
	And user clicks on Register button
	Then verify that the correct url with appender "?controller=my-account" is loaded
	And verify the username is formed by concatenating FirstName and LastName
	And verify the Sign out button is present
	And verify that MY ACCOUNT header is present
	And verify that welcome message is also present
	
@Login @regression @EndToEnd 
Scenario: Log in to the portal 
	Given The portal is opened 
	When user clicks on Sign in button on the top
	And Email address and Password are entered in the ALREADY REGISTERED? section
	And Sign in button below the password field is clicked
	Then verify that the correct url with appender "?controller=my-account" is loaded
	And verify the username is formed by concatenating FirstName and LastName
	And verify the "Sign out" button is present 
	
@Checkout @regression @EndToEnd 
Scenario: Check out the order 
	Given The portal is opened 
	And user clicks on Sign in button on the top
	When Email address and Password are entered in the ALREADY REGISTERED? section
	And Sign in button below the password field is clicked
	Then verify that the correct url with appender "?controller=my-account" is loaded
	When user clicks on Women option in the menu bar
	And user selects the product with title as Faded Short Sleeve T-shirts
	And user clicks on Add to cart button
	And user clicks on Proceed to checkout button on overlay
	And user clicks on Proceed to checkout button on SHOPPING-CART SUMMARY section
	And user clicks on Proceed to checkout button on ADDRESSES section
	And user agrees to the termns and conditions by clicking on the checkbox
	And user clicks on Proceed to checkout button on SHIPPING section
	And user clicks on the payment method of Pay by bank wire
	And user clicks on the button I confirm my order
	Then verify that the correct url with appender "?controller=order-confirmation" is loaded
	And verify that the order is complete
	And verify the last breadcrumb is the current selection