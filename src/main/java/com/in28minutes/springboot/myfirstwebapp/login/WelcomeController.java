package com.in28minutes.springboot.myfirstwebapp.login;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

//@Controller
//@SessionAttributes("myname")
//public class LoginController {
	//using logger to check logs at different levels
//	private Logger logger=LoggerFactory.getLogger(getClass());
	
//	@RequestMapping("loginhere-jsp")
//	public String loginHereJsp() {
//		return "login";
//	}
	
//	http://localhost:8080/loginhere-jsp?name=Shreya
//	@RequestMapping("loginhere-jsp")
//	public String loginHereJsp(@RequestParam String name, ModelMap model) {
////		System.out.println(name); // not recommend instead use logger
//		logger.debug("At debug level{}",name);
//		logger.info("At info level{}",name);
//		logger.warn("At warn level{}",name);
//		model.put("myname",name);//model.put("attribute","thing to put)
//		return "login";
//	}
	
//	@RequestMapping("loginhere-jsp")
//	public String loginHereJsp() {
//		return "login";
//	}
//}
	
//----------------------------------------------------------------
//Without using Spring Security	
//	@Autowired
//	private AuthenticationService myauthentication;
	
//	@RequestMapping(value="loginhere-jsp",method=RequestMethod.GET)
//	public String gotoLoginPage() {
//		return "login";
//	}

	
//we use @RequestParam to catch query params and form fields
//	@RequestMapping(value="loginhere-jsp",method=RequestMethod.POST)
//	public String gotoWelcomepage(@RequestParam String name,
//			@RequestParam String password,ModelMap model) {
//		if(myauthentication.authenticate(name, password)) {
//			model.put("myname", name);
//			return "welcome";
//		}
//		model.put("errormsg","Invalid Credentials! Try Again :)");
//		return "login";
	//here login.jsp are ==notofuselogin.jsp
//		
//	}


//---------------------------------------------------------------------
//Introducing Spring Security using WelcomeController loginController
//renamed as WelcomeController
@Controller
@SessionAttributes("myname")
public class WelcomeController {
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String gotoWelcomePage(ModelMap model) {
//		model.put("myname","Shreya Gour");
		model.put("myname",getLoggedinUsername());
		return "welcome";
	}
	
	private String getLoggedinUsername() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
	
}
