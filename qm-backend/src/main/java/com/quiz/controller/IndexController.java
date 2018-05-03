package com.quiz.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Returns index page
 */
@Controller
public class IndexController {

    /**
     * @return index page
     */
//    @GetMapping({"/"})
//    public String index() {
//        return "forward:index";
//    }
	
	// @RequestMapping(value = "/**", method = RequestMethod.GET)
		// public String indexPage() {
		// return "forward:index";
		// }

	// @GetMapping({ "/" })
	// public String index() {
	// return "forward:index";
	// }
		// // @PreAuthorize("hasAuthority('ADMIN')")
		// @RequestMapping(method = RequestMethod.GET, value = "/status")
		// public @ResponseBody ReturnDTO getStatus() {
		// return new ReturnDTO(true, "Success");
		// }

}
