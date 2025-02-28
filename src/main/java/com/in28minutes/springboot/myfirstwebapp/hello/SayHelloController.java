package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
	//we made use of responsebody to return response as it is
	@RequestMapping("say-hello")
	@ResponseBody
	public String sayHello() {
		return "Hello! What are you doing?";
	}
	
	@GetMapping("say-bye")
	@ResponseBody
	public String sayBye() {
		return "It was nice to meet you!";
	}
	
	@GetMapping("say-hello-html")
	@ResponseBody
	public String sayHelloHtml() {
		StringBuffer sb=new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title>My First Page</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("Excited for my first page");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
	
	@RequestMapping("say-hello-jsp")
	public String sayHelloJsp() {
		return "sayHello";
	}
}
