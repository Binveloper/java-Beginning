package siru.md.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;
import siru.md.domain.Address;
import siru.md.service.AddressAjaxService;

@RestController //jsp, model and view 리턴 불가능
@RequestMapping("ajax04")
@Log4j
public class AjaxT04Controller {
	
	@Autowired
	private AddressAjaxService service;
	@GetMapping(value = "search01",
				produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Address search01(long seq) {
		Address address = service.selectBySeqS(seq);
		return address;
	}
	
	/*
	MediaType은 header에 기입되는 application/json;charset=UTF-8과 같은 content Type을 상수로써 기술할 수 있도록 도와주는 api입니다.
	이것을 사용하므로써 문자열을 적는 것보다 type safe한 효과를 얻을 수 있습니다.
	consumes의 값으로는 문자열이 와야 하기 때문에 MediaType.APPLICATION_JSON_UTF8_VALUE를 사용한 것을 볼 수 있습니다.
	상수의 맨 뒤에 _VALUE가 붙어있는 경우에는 객체가 아닌 문자열로 된 값을 얻어오게 됩니다.
	MediaType객체를 매개변수로 원하는 곳에서는 _VALUE가 붙지 않은 상수를 사용하면 됩니다.
	
	*/
	 
	@PostMapping(value = "search02",
				 produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Address> search02(String name) {
		List<Address> list = service.selectByNameS(name);
		return list;
	}
}
