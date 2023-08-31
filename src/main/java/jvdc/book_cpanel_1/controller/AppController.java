package jvdc.book_cpanel_1.controller;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

	@GetMapping(value = {"/","/login"})
	public String login(){
		return "gamen1";
	}


}
