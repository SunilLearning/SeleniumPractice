package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

	// Old token ID no need to use -->     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
	
	// new token id need to use : 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
	// Repo --> https://github.com/SunilLearning/SeleniumPractice.git
	
	//new token for payement : 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
	
	// this file is created mainly because of for some methods we have provided the hardcoded value
	// we have restrict that one so we have created the app constant class
	
	// for constants we have to use the final keyword this contants for page load time out 
	public static final int DEFAULT_SHORT_TIMEOUT=5;
	public static final int DEFAULT_MEDIUM_TIMEOUT=10;
	public static final int DEFAULT_LONG_TIMEOUT=15;
	
	// we have to create constants for some fixed feature like titleg
	// login page constants
	public static final String DEFAULT_LOGIN_PAGE_TITLE="Account Login";
	public static final String DEFALUT_LOGIN_PAGE_FRACTION_URL="route=account/login";
	
	//my account page constants
	public static final String DEFAULT_ACCOUNT_PAGE_TITLE="My Account";
	public static final String DEFALUT_ACCOUNT_PAGE_FRACTION_URL="route=account/account";
	public static final int DEFALUT_ACCOUNT_PAGE_HEADER_COUNT=2;
	
	// here we are used araays.asList to store the list of values in the list.
	public static final List<String> EXPECTED_ACCOUNTPAGE_HEADERS_LIST=Arrays.asList("My Account","My Orders");
	
	//registration page
	public static final String USER_REG_SUCCESS_MESSG ="Your Account Has Been Created";
	
	
	
	
}
