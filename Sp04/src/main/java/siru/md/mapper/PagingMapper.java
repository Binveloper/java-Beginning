package siru.md.mapper;

import java.util.List;

import siru.md.domain.Paging;
import siru.md.domain.PagingVo;

public interface PagingMapper {
	List<Paging> selectPerPage(PagingVo pagingVo);
	long selectCount();
	Paging selectBySeq(long seq);
	void insert(Paging paging);
	void delete(long seq);
	void deleteAll();
}
