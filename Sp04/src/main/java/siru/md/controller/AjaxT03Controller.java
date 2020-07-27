package siru.md.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;
import siru.md.domain.Address;
import siru.md.service.AddressAjaxService;


@Controller
@RequestMapping("ajax03")
@Log4j
public class AjaxT03Controller {
	
	@Autowired
	private AddressAjaxService service;
	@GetMapping("search01")
	public @ResponseBody Address search01(long seq) {
		Address address = service.selectBySeqS(seq);
		return address; //xml, json
	}
	
	@PostMapping("search02")
	public @ResponseBody List<Address> search02(String name) {
		List<Address> list = service.selectByNameS(name);
		return list; //xml, json
	}
	
	@GetMapping("m4Controller")
	public String controllerM() {
		return "ajax/result"; //jsp
	}
	
	@ResponseBody
	@PostMapping("insertObj")
	public Address insertObj(@RequestBody Address json) {
		log.info(">>>>>AjaxT03Controller insertObj() json: " + json);
		service.insertS(json);
		//String jsonParam = request.getParameter("json");
		//log.info("jsonParam: " + jsonParam);
		
		return json;
	}
}
