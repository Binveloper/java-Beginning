package siru.md.service;

import java.util.List;

import siru.md.domain.Paging;
import siru.md.domain.PagingVo;

public interface PagingService {
	List<Paging> selectPerPageS(PagingVo pagingVo);
	long selectCountS();
	Paging selectBySeqS(long seq);
	void insertS(Paging paging);
	void deleteS(long seq);
	void deleteAllS();
}


//PagingMapper 메소드에서  S 붙임