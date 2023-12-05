package jvdc.book_cpanel_1.controller;


import jvdc.book_cpanel_1.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class AppController {

	@GetMapping(value = {"/","/login"})
	public String login(){
		return "gamen1";
	}
	@GetMapping(value = "/manhinh11_menu")
	public String gotomenu(){
		return "manhinh11_menu";
	}
}
