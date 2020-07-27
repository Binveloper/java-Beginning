package siru.md.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import siru.md.domain.Board;
import siru.md.domain.BoardListResult;
import siru.md.domain.BoardVo;
import siru.md.domain.Files;
import siru.md.domain.MyBoardVo;
import siru.md.mapper.BoardMapper1;
import siru.md.mapper.MyBoardMapper;

@Log4j
@Service
@AllArgsConstructor

public class MyBoardServiceImpl implements MyBoardService {
	@Autowired
	private MyBoardMapper boardMapper;
	@Autowired
	private FileUploadService fService;
	
	@Override
	public List<Board> selectPerPageS(MyBoardVo myBoardVo) {
		return boardMapper.selectPerPage(myBoardVo);
	}

	
	@Override
	public Board getBoard(long seq) {
		return boardMapper.selectBySeq(seq);
	}

	@Override
	public List<Files> getFiles(long seq) {
		return boardMapper.selectFilesBySeq(seq);
	}

	@Override
	public BoardListResult getBoardListResultByKeyword(String keyword, int page, int pageSize) {
		long totalCount = boardMapper.selectCount();
		BoardVo boardVo = new BoardVo(keyword, page, pageSize);
		List<Board> list = boardMapper.selectByWriter(boardVo);
		return new BoardListResult(page, pageSize, totalCount, list);
	}

	@Override
	public void write(Board board) {
		boardMapper.insert(board);

	}

	@Override
	public void edit(Board board) {
		boardMapper.update(board);

	}

	@Override
	public void remove(long seq) {
		boardMapper.delete(seq);

	}

	@Override
	public void removeFile(String fname) {
		boardMapper.deleteFile(fname);
		
	}
	@Transactional
    @Override
    public void writeTest(Board board, ArrayList<MultipartFile> files) {
		log.info("<<<<<<<<<<<<board from view : "+board);

        log.info("<<<<<<<<<<<<files from view : "+files);
        //board.setFiles(files);
        log.info("<<<<<<<<<<<<files.size from view : "+files.size());

        boardMapper.insert(board);
        for(MultipartFile file : files) {
            String ofname = file.getOriginalFilename();
            if(ofname != null) {
                ofname = ofname.trim();
                if(ofname.length()!=0) {
                    String fname = fService.saveStore(file);
                    if(fname.length()!=0 || fname!=null) {
                        boardMapper.insertTest(new Files(-1L, fname, ofname, file.getSize(), -1L));
                    }
                }else {
                    log.info("#length==0");
                }
            }else {
                log.info("#ofname==null");
            }
        }
    }


	@Override
	public long selectCount() {
		return boardMapper.selectCount();
	}
	
	
}
