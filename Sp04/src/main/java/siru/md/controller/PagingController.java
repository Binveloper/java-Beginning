package siru.md.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import siru.md.domain.Paging;
import siru.md.domain.PagingVo;
import siru.md.service.PagingService;

@Controller
@RequestMapping("paging")
public class PagingController {
	@Autowired
	private PagingService service;
	
	@RequestMapping("list.do")
	public ModelAndView list(PagingVo pagingVo) {
		List<Paging> list = service.selectPerPageS(pagingVo);
		long totalCount = service.selectCountS();
		pagingVo.setTotal(totalCount);
		
		ModelAndView mv = new ModelAndView("paging/list");
		mv.addObject("list", list);
		mv.addObject("p", pagingVo);
		
		return mv;
	}
	
	private Random r = new Random();
	@GetMapping("write.do")
	public String write(long num) {
		for(int i=0; i<num; i++) {
			Paging paging = new Paging(-1, "Á¦¸ñ: " + r.nextLong(), null);
			service.insertS(paging);
		}
		
		return "redirect:list.do";
	}
	
	@GetMapping("del.do")
	public String delete(long seq) {
		service.deleteS(seq);
		
		return "redirect:list.do";
	}
	
	@GetMapping("delAll.do")
	public String delete() {
		service.deleteAllS();
		
		return "redirect:list.do";
	}
	
	
}
