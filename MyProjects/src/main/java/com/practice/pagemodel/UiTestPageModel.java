package com.practice.pagemodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class contains locators for UI tests
 * 
 * @author ramandeep.singh.nagi
 *
 */
public class UiTestPageModel {

	/**
	 * Locator for link: "Sign in"
	 */
	@FindBy(css = ".login")
	private WebElement linkSignin;

	/**
	 * Locator for textbox: "Create An Account"
	 */
	@FindBy(css = "#email_create")
	private WebElement txtBoxCreateAcc;

	/**
	 * Locator for button: "Create an account"
	 */
	@FindBy(css = "#SubmitCreate")
	private WebElement btnCreateAnAcc;

	/**
	 * Locator for Radio button: "Mr."
	 */
	@FindBy(css = "#id_gender1")
	private WebElement rdBtnMr;

	/**
	 * Locator for Radio button: "Mrs."
	 */
	@FindBy(css = "#id_gender2")
	private WebElement rdBtnMrs;

	/**
	 * Locator for Text box: "First Name"
	 */
	@FindBy(css = "#customer_firstname")
	private WebElement txtBoxFirstName;

	/**
	 * Locator for Text box: "Last Name"
	 */
	@FindBy(css = "#customer_lastname")
	private WebElement txtBoxLastName;

	/**
	 * Locator for Text box: "Password"
	 */
	@FindBy(css = "#passwd")
	private WebElement txtBoxPassword;

	/**
	 * Locator for dropdown: "days"
	 */
	@FindBy(css = "#days")
	private WebElement drpDwnDays;

	/**
	 * Locator for dropdown: "months"
	 */
	@FindBy(css = "#months")
	private WebElement drpDwnMonths;

	/**
	 * Locator for dropdown: "years"
	 */
	@FindBy(css = "#years")
	private WebElement drpDwnYears;

	/**
	 * Locator for Text box: "Company"
	 */
	@FindBy(css = "#company")
	private WebElement txtBoxCompany;

	/**
	 * Locator for Text box: "Address1"
	 */
	@FindBy(css = "#address1")
	private WebElement txtBoxAddress1;

	/**
	 * Locator for Text box: "Address2"
	 */
	@FindBy(css = "#address2")
	private WebElement txtBoxAddress2;

	/**
	 * Locator for Text box: "City"
	 */
	@FindBy(css = "#city")
	private WebElement txtBoxCity;

	/**
	 * Locator for Dropdown: "State"
	 */
	@FindBy(css = "#id_state")
	private WebElement drpDwnState;

	/**
	 * Locator for Text box: "Zip/Postal Code"
	 */
	@FindBy(css = "#postcode")
	private WebElement txtBoxPostCode;

	/**
	 * Locator forDropdown: "Country"
	 */
	@FindBy(css = "id_country")
	private WebElement drpDwnCountry;

	/**
	 * Locator for Text box: "Additional information"
	 */
	@FindBy(css = "#other")
	private WebElement txtBoxAdditionalInfo;

	/**
	 * Locator for Text box: "Home phone"
	 */
	@FindBy(css = "#phone")
	private WebElement txtBoxHomePhone;

	/**
	 * Locator for Text box: "Mobile phone"
	 */
	@FindBy(css = "#phone_mobile")
	private WebElement txtBoxPhoneMobile;

	/**
	 * Locator for Text box: "Assign an address alias for future reference."
	 */
	@FindBy(css = "#alias")
	private WebElement txtBoxAlias;

	/**
	 * Locator for Button: "Register"
	 */
	@FindBy(css = "#submitAccount")
	private WebElement btnRegister;

	/**
	 * Locator for Label: "My Account"
	 */
	@FindBy(css = ".page-heading")
	private WebElement lblMyAccount;

	/**
	 * Locator for link: "firstname lastname"
	 */
	@FindBy(css = ".account")
	private WebElement linkUserName;

	/**
	 * Locator for Label: "Welcome to your account. Here you can manage all of
	 * your personal information and orders."
	 */
	@FindBy(css = ".info-account")
	private WebElement lblSubHeading;

	/**
	 * Locator for Link: "Sign out"
	 */
	@FindBy(css = ".logout")
	private WebElement linkSignOut;

	/**
	 * Locator for Text box: "Email address"
	 */
	@FindBy(css = "#email")
	private WebElement txtBoxEmail;

	/**
	 * Locator for Button: "Sign in"
	 */
	@FindBy(css = "#SubmitLogin")
	private WebElement btnSignIn;

	/**
	 * Locator for Menu Link: "Women"
	 */
	@FindBy(linkText = "Women")
	private WebElement linkWomen;

	/**
	 * Locator for Link block: "Faded Short Sleeve T-shirts"
	 */
	@FindBy(xpath = "//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")
	private WebElement LinkFadedShortTShirt;

	/**
	 * Locator for Button: "Add to Cart"
	 */
	@FindBy(css = "[name='Submit']")
	private WebElement BtnAddToCart;

	/**
	 * Locator for Button: "Proceed To Checkout"
	 */
	@FindBy(xpath = "//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")
	private WebElement btnProceedToCheckout;

	/**
	 * Locator for Button on review order: "Proceed to checkout"
	 */
	@FindBy(xpath = "//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")
	private WebElement btnReviewProceedToCheckout;

	/**
	 * Locator for Button - processAddress: "Proceed to checkout"
	 */
	@FindBy(css = "[name='processAddress']")
	private WebElement btnProcessAddress;

	/**
	 * Locator for Checkbox: "I agree to the terms of service and will adhere to
	 * them unconditionally. (Read the Terms of Service)"
	 */
	@FindBy(css = "#uniform-cgv")
	private WebElement chkBoxIAgree;

	/**
	 * Locator for Button: "Proceed to checkout"
	 */
	@FindBy(css = "[name='processCarrier']")
	private WebElement btnProcessCarrier;

	/**
	 * Locator for Link: "Pay by bank wire"
	 */
	@FindBy(css = ".bankwire")
	private WebElement LinkBankWire;

	/**
	 * Locator for Button: "I confirm my order"
	 */
	@FindBy(xpath = "//*[@id='cart_navigation']/button")
	private WebElement btnIConfirmMyOrder;

	/**
	 * Locator for Breadcrumb: "Shipping"
	 */
	@FindBy(xpath = "//li[@class='step_done step_done_last four']")
	private WebElement LinkShippingBrdCrmb;

	/**
	 * Locator for Breadcrumb: "Payment"
	 */
	@FindBy(xpath = "//li[@id='step_end' and @class='step_current last']")
	private WebElement linkPaymentBrdCrmb;

	/**
	 * Locator for label message: "Your order on My Store is complete."
	 */
	@FindBy(xpath = "//*[@class='cheque-indent']/strong")
	private WebElement lblOrderIsComplete;
	
	/**
	 * Locator for label error message: "An account using this email address has already been registered. Please enter a valid password or request a new one."
	 */
	@FindBy(css = "#create_account_error")
	private WebElement lblErrorMsgPanel;
	
	/**
	 * Locator for button: "Add to cart"
	 */
	@FindBy(css = ".button-container>span")
	private WebElement btnAddToCartOnMouseAction;
	
	/**
	 * Locator for label: "Payment" 
	 */
	@FindBy(css = ".step_current.last")
	private WebElement lblPaymentBrdCrmb;

	// --------------Getters----------------

	/**
	 * Locator for label: "Payment" 
	 * @return lblPaymentBrdCrmb
	 */
	public WebElement getLblPaymentBrdCrmb() {
		return lblPaymentBrdCrmb;
	}

	/**
	 * Locator for button: "Add to cart"
	 * @return btnAddToCartOnMouseAction
	 */
	public WebElement getBtnAddToCartOnMouseAction() {
		return btnAddToCartOnMouseAction;
	}

	/**
	 * Locator for label error message: "An account using this email address has already been registered. Please enter a valid password or request a new one."
	 * @return lblErrorMsgPanel
	 */
	public WebElement getLblErrorMsgPanel() {
		return lblErrorMsgPanel;
	}

	/**
	 * Locator for label message: "Your order on My Store is complete."
	 * 
	 * @return lblOrderIsComplete
	 */
	public WebElement getLblOrderIsComplete() {
		return lblOrderIsComplete;
	}

	/**
	 * Locator for Breadcrumb: "Payment"
	 * 
	 * @return linkPaymentBrdCrmb
	 */
	public WebElement getLinkPaymentBrdCrmb() {
		return linkPaymentBrdCrmb;
	}

	/**
	 * Locator for Breadcrumb: "Shipping"
	 * 
	 * @return LinkShippingBrdCrmb
	 */
	public WebElement getLinkShippingBrdCrmb() {
		return LinkShippingBrdCrmb;
	}

	/**
	 * Locator for Button: "I confirm my order"
	 * 
	 * @return btnIConfirmMyOrder
	 */
	public WebElement getBtnIConfirmMyOrder() {
		return btnIConfirmMyOrder;
	}

	/**
	 * Locator for Link: "Pay by bank wire"
	 * 
	 * @return LinkBankWire
	 */
	public WebElement getLinkBankWire() {
		return LinkBankWire;
	}

	/**
	 * Locator for Button: "Proceed to checkout"
	 * 
	 * @return btnProcessCarrier
	 */
	public WebElement getBtnProcessCarrier() {
		return btnProcessCarrier;
	}

	/**
	 * Locator for Checkbox: "I agree to the terms of service and will adhere to
	 * them unconditionally. (Read the Terms of Service)"
	 * 
	 * @return chkBoxIAgree
	 */
	public WebElement getChkBoxIAgree() {
		return chkBoxIAgree;
	}

	/**
	 * Locator for Button - processAddress: "Proceed to checkout"
	 * 
	 * @return btnProcessAddress
	 */
	public WebElement getBtnProcessAddress() {
		return btnProcessAddress;
	}

	/**
	 * Locator for Button on review order: "Proceed to checkout"
	 * 
	 * @return btnReviewProceedToCheckout
	 */
	public WebElement getBtnReviewProceedToCheckout() {
		return btnReviewProceedToCheckout;
	}

	/**
	 * Locator for Button: "Proceed To Checkout"
	 * 
	 * @return btnProceedToCheckout
	 */
	public WebElement getBtnProceedToCheckout() {
		return btnProceedToCheckout;
	}

	/**
	 * Locator for Button: "Add to Cart"
	 * 
	 * @return BtnAddToCart
	 */
	public WebElement getBtnAddToCart() {
		return BtnAddToCart;
	}

	/**
	 * Locator for Link block: "Faded Short Sleeve T-shirts"
	 * 
	 * @return LinkFadedShortTShirt
	 */
	public WebElement getLinkFadedShortTShirt() {
		return LinkFadedShortTShirt;
	}

	/**
	 * Locator for Menu Link: "Women"
	 * 
	 * @return linkWomen
	 */
	public WebElement getLinkWomen() {
		return linkWomen;
	}

	/**
	 * Locator for Button: "Sign in"
	 * 
	 * @return btnSignIn
	 */
	public WebElement getBtnSignIn() {
		return btnSignIn;
	}

	/**
	 * Locator for Text box: "Email address"
	 * 
	 * @return txtBoxEmail
	 */
	public WebElement getTxtBoxEmail() {
		return txtBoxEmail;
	}

	/**
	 * Locator for Link: "Sign out"
	 * 
	 * @return linkSignOut
	 */
	public WebElement getLinkSignOut() {
		return linkSignOut;
	}

	/**
	 * Locator for Label: "Welcome to your account. Here you can manage all of
	 * your personal information and orders."
	 * 
	 * @return lblSubHeading
	 */
	public WebElement getLblSubHeading() {
		return lblSubHeading;
	}

	/**
	 * Locator for link: "firstname lastname"
	 * 
	 * @return linkUserName
	 */
	public WebElement getLinkUserName() {
		return linkUserName;
	}

	/**
	 * Locator for Label: "My Account"
	 * 
	 * @return lblMyAccount
	 */
	public WebElement getLblMyAccount() {
		return lblMyAccount;
	}

	/**
	 * Locator for Button: "Register"
	 * 
	 * @return btnRegister
	 */
	public WebElement getBtnRegister() {
		return btnRegister;
	}

	/**
	 * Locator for Text box: "Assign an address alias for future reference."
	 * 
	 * @return txtBoxAlias
	 */
	public WebElement getTxtBoxAlias() {
		return txtBoxAlias;
	}

	/**
	 * Locator for Text box: "Mobile phone"
	 * 
	 * @return txtBoxPhoneMobile
	 */
	public WebElement getTxtBoxPhoneMobile() {
		return txtBoxPhoneMobile;
	}

	/**
	 * Locator for Text box: "Home phone"
	 * 
	 * @return txtBoxHomePhone
	 */
	public WebElement getTxtBoxHomePhone() {
		return txtBoxHomePhone;
	}

	/**
	 * Locator for Text box: "Additional information"
	 * 
	 * @return txtBoxAdditionalInfo
	 */
	public WebElement getTxtBoxAdditionalInfo() {
		return txtBoxAdditionalInfo;
	}

	/**
	 * Locator forDropdown: "Country"
	 * 
	 * @return drpDwnCountry
	 */
	public WebElement getDrpDwnCountry() {
		return drpDwnCountry;
	}

	/**
	 * Locator for Text box: "Zip/Postal Code"
	 * 
	 * @return txtBoxPostCode
	 */
	public WebElement getTxtBoxPostCode() {
		return txtBoxPostCode;
	}

	/**
	 * Locator for Dropdown: "State"
	 * 
	 * @return drpDwnState
	 */
	public WebElement getDrpDwnState() {
		return drpDwnState;
	}

	/**
	 * Locator for Text box: "City"
	 * 
	 * @return txtBoxCity
	 */
	public WebElement getTxtBoxCity() {
		return txtBoxCity;
	}

	/**
	 * Locator for Text box: "Address2"
	 * 
	 * @return txtBoxAddress2
	 */
	public WebElement getTxtBoxAddress2() {
		return txtBoxAddress2;
	}

	/**
	 * Locator for Text box: "Address1"
	 * 
	 * @return txtBoxAddress1
	 */
	public WebElement getTxtBoxAddress1() {
		return txtBoxAddress1;
	}

	/**
	 * Locator for Text box: "Company"
	 * 
	 * @return txtBoxCompany
	 */
	public WebElement getTxtBoxCompany() {
		return txtBoxCompany;
	}

	/**
	 * Locator for dropdown: "years"
	 * 
	 * @return drpDwnYears
	 */
	public WebElement getDrpDwnYears() {
		return drpDwnYears;
	}

	/**
	 * Locator for dropdown: "months"
	 * 
	 * @return drpDwnMonths
	 */
	public WebElement getDrpDwnMonths() {
		return drpDwnMonths;
	}

	/**
	 * Locator for dropdown: "days"
	 * 
	 * @return drpDwnDays
	 */
	public WebElement getDrpDwnDays() {
		return drpDwnDays;
	}

	/**
	 * Locator for Text box: "Password"
	 * 
	 * @return txtBoxPassword
	 */
	public WebElement getTxtBoxPassword() {
		return txtBoxPassword;
	}

	/**
	 * Locator for Text box: "Last Name"
	 * 
	 * @return txtBoxLastName
	 */
	public WebElement getTxtBoxLastName() {
		return txtBoxLastName;
	}

	/**
	 * Locator for Text box: "First Name"
	 * 
	 * @return txtBoxFirstName
	 */
	public WebElement getTxtBoxFirstName() {
		return txtBoxFirstName;
	}

	/**
	 * Locator for Radio button: "Mrs."
	 * 
	 * @return rdBtnMrs
	 */
	public WebElement getRdBtnMrs() {
		return rdBtnMrs;
	}

	/**
	 * Locator for Radio button: "Mr."
	 * 
	 * @return rdBtnMr
	 */
	public WebElement getRdBtnMr() {
		return rdBtnMr;
	}

	/**
	 * Locator for button: "Create an account"
	 * 
	 * @return btnCreateAnAcc
	 */
	public WebElement getBtnCreateAnAcc() {
		return btnCreateAnAcc;
	}

	/**
	 * Locator for textbox: "Create An Account"
	 * 
	 * @return txtBoxCreateAcc
	 */
	public WebElement getTxtBoxCreateAcc() {
		return txtBoxCreateAcc;
	}

	/**
	 * Locator for link: "Sign in"
	 * 
	 * @return linkSignin
	 */
	public WebElement getLinkSignin() {
		return linkSignin;
	}

}
