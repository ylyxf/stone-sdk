package com.siqisoft.stone.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

	@RequestMapping("/home/Home.do")
	public String home(Model model) {
		return "home/Home";
	}
	
	@RequestMapping("/home/Desktop.do")
	public String desktop(Model model) {
		return "home/Desktop";
	}

}
