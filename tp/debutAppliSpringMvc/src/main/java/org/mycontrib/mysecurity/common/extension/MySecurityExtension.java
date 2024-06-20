package org.mycontrib.mysecurity.common.extension;

public interface MySecurityExtension {
	//end of URL for predefined REST login WS (to call via method=POST)
	//to get JWT token in standalone mode (no Oauth2/Oidc)
	public static final String DEFAULT_REST_STANDALONE_LOGIN_PATH="/rest/api-login/public/login";
	
	//URI of login form (not predefined, may be define in final project if necessary):
	public static final String DEFAULT_SITE_FORM_LOGIN_URI="/site/login";
	
	//URI of logout form (not predefined, may be define in final project if necessary):
	public static final String DEFAULT_SITE_FORM_LOGOUT_URI="/site/logout";
	
	//default value for mysecurity.area.other.whitelist
	public static final String[] DEFAULT_STATIC_WHITELIST = { "/", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg",
			"/**/*.html", "/**/*.css", "/**/*.js" }; 
	
	//partie de l'url (au debut) devant mener à une api REST (ex: /rest/api-xyz/xyz)
	public static final String REST_PART_URI="/rest";
	
	//partie de l'url (au debut) devant mener à une partie du site web (@Controller + JSP ou Thymeleaf)  (ex: /site/xxx/zzz)
	public static final String SITE_PART_URI="/site";
	
	//constantes pour nom à donner au composant de UserDetailsService spécifique au projet
	//EXCLUSIVE si celui ci remplace la config par défaut de ce projet ("InMemory" ou autre)
	//ADDITIONAL si celui s'ajoute à la config par défaut de ce projet
	
	//Si le UserDetailsService peut aussi bien être utilisé sur partie "site" que partie "rest"
	public static final String MY_EXCLUSIVE_USERDETAILSSERVICE_NAME="MyExclusiveUserDetailsService";
	public static final String MY_ADDITIONAL_USERDETAILSSERVICE_NAME="MyAdditionalUserDetailsService";
	
	//Si le UserDetailsService ne doit être utilisé sur partie "rest"
	public static final String MY_EXCLUSIVE_RESTONLY_USERDETAILSSERVICE_NAME="MyExclusiveRestOnlyUserDetailsService";
	public static final String MY_ADDITIONAL_RESTONLY_USERDETAILSSERVICE_NAME="MyAdditionalRestOnlyUserDetailsService";
	
	//Si le UserDetailsService ne doit être utilisé sur partie "site"
	public static final String MY_EXCLUSIVE_SITEONLY_USERDETAILSSERVICE_NAME="MyExclusiveSiteOnlyUserDetailsService";
	public static final String MY_ADDITIONAL_SITEONLY_USERDETAILSSERVICE_NAME="MyAdditionalSiteOnlyUserDetailsService";
}
