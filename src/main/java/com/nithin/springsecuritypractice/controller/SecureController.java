package com.nithin.springsecuritypractice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/corporate-api")
public class SecureController {
	
	@GetMapping("/R1")
	@ResponseStatus(HttpStatus.OK)
	public String R1()
	{
		return "Access to R1 is Allowed";
	}
	
	@GetMapping("/R2")
	@ResponseStatus(HttpStatus.OK)
	public String R2()
	{
		return "Access to R2 is Allowed";
	}
	
	@GetMapping("/R3")
	@ResponseStatus(HttpStatus.OK)
	public String R3()
	{
		return "Access to R3 is Allowed";
	}
	
	@GetMapping("/R4")
	@ResponseStatus(HttpStatus.OK)
	public String R4()
	{
		return "Access to R4 is Allowed";
	}
	
//	@GetMapping("/R5")
//	@ResponseStatus(HttpStatus.OK)
//	public String R5()
//	{
//		return "Access to R5 is Allowed";
//	}
	
}
