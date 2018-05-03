package com.quiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quiz.dto.ReturnDTO;

@Controller
public class ViewUserController {

	// @RequestMapping(value = "/**", method = RequestMethod.GET)
	// public String indexPage() {
	// return "forward:index";
	// }

//	@GetMapping({ "/" })
//	public String index() {
//		return "forward:index";
//	}
	// // @PreAuthorize("hasAuthority('ADMIN')")
	// @RequestMapping(method = RequestMethod.GET, value = "/status")
	// public @ResponseBody ReturnDTO getStatus() {
	// return new ReturnDTO(true, "Success");
	// }
}
