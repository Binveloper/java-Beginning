package siru.md.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import siru.md.domain.Paging;
import siru.md.domain.PagingVo;
import siru.md.mapper.PagingMapper;

@Service
@AllArgsConstructor

public class PagingServiceImpl implements PagingService {
	private PagingMapper mapper;
	@Override
	public List<Paging> selectPerPageS(PagingVo pagingVo) {
		return mapper.selectPerPage(pagingVo);
	}

	@Override
	public long selectCountS() {
		return mapper.selectCount();
	}

	@Override
	public Paging selectBySeqS(long seq) {
		return mapper.selectBySeq(seq);
	}

	@Override
	public void insertS(Paging paging) {
		mapper.insert(paging);
	}

	@Override
	public void deleteS(long seq) {
		mapper.delete(seq);
	}

	@Override
	public void deleteAllS() {
		mapper.deleteAll();
	}

}
