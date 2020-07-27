package siru.md.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.log4j.Log4j;
import siru.md.domain.Board;
import siru.md.service.BoardService;
import siru.md.vo.ListResult;

@Log4j
@RequestMapping("/board1/*")
@Controller
public class BoardController1 {
	@Resource(name="BoardServiceImpl1")
	private BoardService service;
	
	@RequestMapping("/list.do")
	public ModelAndView list(String cp, String ps, HttpServletRequest request, HttpSession session) {


        //(1) cp 
                int currentPage = 1;
                if(cp == null) {

                    Object cpObj = session.getAttribute("cp");
                    if(cpObj != null) {
                        currentPage = (Integer)cpObj;
                    }
                }else {
                    cp = cp.trim();
                    currentPage = Integer.parseInt(cp);
                }
                session.setAttribute("cp", currentPage);

                //(2) ps 
                int pageSize = 3;
                if(ps == null) {
                    Object psObj = session.getAttribute("ps");
                    if(psObj != null) {
                        pageSize = (Integer)psObj;
                    }
                }else {
                    ps = ps.trim();
                    int psParam = Integer.parseInt(ps);

                    Object psObj = session.getAttribute("ps");
                    if(psObj != null) {
                        int psSession = (Integer)psObj;
                        if(psSession != psParam) {
                            currentPage = 1;
                            session.setAttribute("cp", currentPage);
                        }
                    }else {
                        if(pageSize != psParam) {
                            currentPage = 1;
                            session.setAttribute("cp", currentPage);
                        }
                    }
                    pageSize = psParam;
                }
                session.setAttribute("ps", pageSize);

        ListResult listResult =  service.getListResult(currentPage, pageSize);
        return new ModelAndView("board1/List", "listResult", listResult);
    }
	
	@GetMapping("/content.do")
	public ModelAndView contents(long seq) {
		Board board = service.contentsS(seq);
		log.info(">>> list.seq : "+board.getSeq());
		return new ModelAndView("board1/content", "board", board);
	}
	
	@GetMapping("/write.do")
	public String insert() {
		return "board1/WriteH";
	}
	
	@PostMapping("/write.do")
	public ModelAndView insert(Board board) {
		boolean flag = service.insertS(board);
		return new ModelAndView("board1/Write", "flag", flag);
	}
	
	@GetMapping("/delete.do")
	public String delete(long seq) {
		service.deleteS(seq);
		return "redirect:list.do";
	}
	
	@GetMapping("/update.do")
	public ModelAndView update(long seq) {
		Board board = service.contentsS(seq);
		return new ModelAndView("board1/Update", "board", board);
	}
	
	@PostMapping("/update.do")
	public ModelAndView Update(Board board) {
		boolean flag = service.updateS(board);
		return new ModelAndView("board1/Update2", "flag", flag);
	}
}
