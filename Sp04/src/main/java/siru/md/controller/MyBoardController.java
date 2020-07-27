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
import siru.md.domain.MyBoardVo;
import siru.md.domain.Paging;
import siru.md.domain.PagingVo;
import siru.md.filesetting.Path;
import siru.md.service.BoardService2;
import siru.md.service.MyBoardService;

@RequestMapping("MyBoard")
@Controller
@Log4j
@AllArgsConstructor
public class MyBoardController {
	private MyBoardService service;
	
	@RequestMapping("list.do")
	public ModelAndView list(MyBoardVo myBoardVo) {
		List<Board> list = service.selectPerPageS(myBoardVo);
		long totalCount = service.selectCount();
		myBoardVo.setTotal(totalCount);
		
		ModelAndView mv = new ModelAndView("paging/list");
		mv.addObject("list", list);
		mv.addObject("p", myBoardVo);
		
		return mv;
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
		ModelAndView mv = new ModelAndView("myboard/content");
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
		ModelAndView mv = new ModelAndView("myboard/update", "board", board);
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
