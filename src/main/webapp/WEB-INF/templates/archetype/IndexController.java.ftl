package ${project.basicPackage}.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {


	@RequestMapping("/index/Index.do")
	public String index( Model model) {
		return "index/Index";
	}


}
