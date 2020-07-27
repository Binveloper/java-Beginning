package siru.md.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import oracle.jdbc.proxy.annotation.Post;
import siru.md.domain.Board;
import siru.md.domain.BoardListResult;
import siru.md.domain.Files;
import siru.md.filesetting.Path;
import siru.md.service.BoardService2;

@RequestMapping("board2")
@Controller
@Log4j
@AllArgsConstructor
public class BoardController2 {
	private BoardService2 service;
	
	@GetMapping("list.do")
	public ModelAndView list(HttpServletRequest request, HttpSession session) {
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		
		//(1) cp 
		int cp = 1;
		if(cpStr == null) {
			Object cpObj = session.getAttribute("cp");
			if(cpObj != null) {
				cp = (Integer)cpObj;
			}
		}else {
			cpStr = cpStr.trim();
			cp = Integer.parseInt(cpStr);
		}
		session.setAttribute("cp", cp);
		
		//(2) ps 
		int ps = 3;
		if(psStr == null) {
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				ps = (Integer)psObj;
			}
		}else {
			psStr = psStr.trim();
			int psParam = Integer.parseInt(psStr);
			
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				int psSession = (Integer)psObj;
				if(psSession != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}else {
				if(ps != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}
			
			ps = psParam;
		}
		session.setAttribute("ps", ps);
		
		//(3) ModelAndView
		BoardListResult listResult = service.getBoardListResult(cp, ps);
		ModelAndView mv = new ModelAndView("board2/list", "listResult", listResult);
		if(listResult.getList().size() == 0 && cp > 1) {
			if(cp > 1) {
				return new ModelAndView("redirect:list.do?cp="+(cp-1));
			}else {
				return new ModelAndView("redirect:list.do", "listResult", null);
			}
		}else {
			return mv;
		}
	}
	@GetMapping("write.do")
	public String write() {
		return "board2/write";
	}
	
	@PostMapping("write.do")
	public String write(Board board, ArrayList<MultipartFile> files) {
		//service.write(board);
		log.info(">>>>>>>>>>>>>>>board from view : "+board);

        log.info(">>>>>>>>>>>>>>>files from view : "+files);
        //board.setFiles(files);
        log.info(">>>>>>>>>>>>>>>files.size from view : "+files.size());

		service.writeTest(board, files);
		return "redirect:list.do";
	}
	
	@GetMapping("content.do")
	public ModelAndView content(long seq) {
		Board board = service.getBoard(seq);
		List<Files> files = service.getFiles(seq);
		ModelAndView mv = new ModelAndView("board2/content");
		mv.addObject("board", board);
		mv.addObject("files", files);

		return mv;
	}
	
	@GetMapping("file_del")
	public String del(@RequestParam String fname, @RequestParam long seq) {
		File file = new File(Path.FILE_STORE, fname);
		if(file.exists()) {
			file.delete();
			service.removeFile(fname);
			
		}
		
		return "redirect:content.do?seq="+seq;
	}
	
	@GetMapping("file_download")
	public ModelAndView download(@RequestParam String fname) {
		File file = new File(Path.FILE_STORE, fname);
		if(file.exists()) {
			return new ModelAndView("fileDownloadView", "downloadFile", file);
		}else {
			return new ModelAndView("redirect:list.do");
		}
	}
	
	@GetMapping("update.do")
	public ModelAndView update(long seq) {
		Board board = service.getBoard(seq);
		ModelAndView mv = new ModelAndView("board2/update", "board", board);
		return mv;
	}
	
	@PostMapping("update.do")
	public String update(Board board) {
		service.edit(board);
		return "redirect:list.do";
	}
	
	@GetMapping("del.do")
	public String delete(long seq) {
		service.remove(seq);
		return "redirect:list.do";
	}
}
