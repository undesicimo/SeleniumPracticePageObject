
	@tag
	Feature: Purchase the order from Ecommerce Website
	

	Background:
	Given I landed on Ecommerce Page

	@tag2
	Scenario Outline: Positive test of submitting order
	
	Given Logged in with email <email> and password <password>
	When I add product <product> to Cart
	And Checkout product <product> and submit the order
	Then "Thankyou for the order." message is displayed on the ConformationPage

	Examples:
    	| email | password | product |
    	| a1ngel0@gmail.com  | A1234567 | IPHONE 13 PRO  |