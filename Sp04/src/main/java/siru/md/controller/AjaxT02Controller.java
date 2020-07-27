package siru.md.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;
import siru.md.domain.Address;
import siru.md.service.AddressAjaxService;

@Controller
@RequestMapping("ajax02")
@Log4j
public class AjaxT02Controller {
	
	@Autowired
	private AddressAjaxService service;
	@GetMapping("search01.do")
	public void search01(long seq, HttpServletResponse response) {
		Address address = service.selectBySeqS(seq);
		
		ObjectMapper om = new ObjectMapper();
/*
JSON 파일에 있는 데이터를 Mapping 하여 가지고 오는 방법 중 하나로 ObjectMapper를 이용할 수 있다.
JSON 파일에 있는 Key 값을 java 클래스 파일로 미리 만들어 두고 ObjectMapper의 readValue 함수를 이용하면 쉽게 가지고 올 수 있다.
*/

		try {
			String json = om.writeValueAsString(address);
			response.setContentType("application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.print(json);
		}catch(IOException ie) {}
		
	}
	
	@PostMapping("search02.do")
	public void search02(String name, HttpServletResponse response) {
		List<Address> list = service.selectByNameS(name);
		
		ObjectMapper om = new ObjectMapper();
		try {
			String json = om.writeValueAsString(list);
			response.setContentType("application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.print(json);
		}catch(IOException ie) {}
	}
}
