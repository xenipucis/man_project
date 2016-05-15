package at.tiby.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class ExampleRest {

	@RequestMapping("/")
	String home() {
		return "Welcome home";
	}
	
}
