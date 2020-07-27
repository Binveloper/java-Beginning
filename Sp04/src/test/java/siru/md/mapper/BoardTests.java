package siru.md.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import siru.md.domain.Board;
import siru.md.domain.BoardVo;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardTests {
	@Autowired
	private BoardMapper1 boardMapper;
	
	@Test
	public void testSelectPerPage() {
		BoardVo boardVo = new BoardVo("", 1, 2);
		List<Board> list = boardMapper.selectByWriter(boardVo);
		log.info("#testSelectPerPage() 호출");
		for(Board board : list) {
			log.info("#seq: " + board.getSeq() + ", writer: " + board.getWriter());
		}
	}
	
	@Test
	public void selectCount(){
		log.info("testSelectCount() count:" + boardMapper.selectCount());
	}
	
	@Test
	public void testSelectBySeq() {
		long seq = 2;
		Board board = boardMapper.selectBySeq(seq);
		log.info("#testSelectBySeq(): " + board);
	}
	
	@Test
	public void testSelectByWriter() {
		BoardVo boardVo = new BoardVo("이", 1, 2);
		List<Board> list = boardMapper.selectByWriter(boardVo);
		log.info("#testSelectByWriter() 호출");
		for(Board board : list) {
			log.info("#seq: " + board.getSeq() + ", writer: " + board.getWriter());
		}
	}
	
	@Test
	public void testInsert() {
		Board board = new Board(-1L, "writer", "email", "subject", "content", null);
		boardMapper.insert(board);
		log.info("testInsert() 성공");
	}
	
	@Test
	public void testUpdate() {
		Board board = new Board(3, "", "email2", "subject2", "content2", null);
		boardMapper.update(board);
		log.info("#testUpdate 성공");
	}
	
	@Test
	public void testDelete() {
		boardMapper.delete(17);
		log.info("#testDelete 성공");
	}
	
}
