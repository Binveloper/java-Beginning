package siru.md.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import siru.md.domain.Board;
import siru.md.domain.BoardVo;
import siru.md.domain.Files;

public interface BoardMapper1 {

		List<Board> selectPerPage(BoardVo boardVo);
		long selectCount();
		Board selectBySeq(long seq);
		List<Files> selectFilesBySeq(long seq);
		List<Board> selectByWriter(BoardVo boardVo);
		void insert(Board board);
		void insertTest(Files files);
		void update(Board board);
		void delete(long seq);
		void deleteFile(String fname);
}
