package siru.md.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
import siru.md.domain.Address;
import siru.md.service.AddressService;

@RequestMapping("/address/*")
@Log4j
@Controller
public class AddressController {
	@Resource(name="AddressService")
	private AddressService service;
	
	@RequestMapping("/list.do")
	public ModelAndView list() {
		List<Address> list = service.listS();
		return new ModelAndView("address/list", "list", list);
	}
	@GetMapping("/write.do")
	public String write() {
		return "address/write";
	}
	@PostMapping("/write.do")
	public String write(Address address) {
		service.insertS(address);
		return "redirect:list.do";
	}
	@GetMapping("/del.do")
	public String delete(@RequestParam long seq, HttpSession session, HttpServletRequest request, Object page) {
		ServletContext application = session.getServletContext();
		log.info("#application: " + application);
		log.info("#session: " + session);
		log.info("#request: " + request);
		log.info("#page: " + page);
		
		service.deleteS(seq);
		return "redirect:list.do";
	}
}
