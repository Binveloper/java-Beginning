
package siru.md.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
import siru.md.dao.AddressDao;
import siru.md.domain.Address;
import siru.md.mapper.AddressAjaxMapper;


@Service
@Log4j
public class AddressAjaxServiceImpl implements AddressAjaxService {
	@Autowired
	private AddressAjaxMapper mapper;
	
	@Override
	public List<Address> listS() {
		return mapper.list();
	}

	@Override
	public void insertS(Address address) {
		mapper.insert(address);
	}

	@Override
	public void deleteS(long seq) {
		mapper.delete(seq);
	}
	
	//for Ajax
	@Override
	public Address selectBySeqS(long seq) {
		return mapper.selectBySeq(seq);
	}
	@Override
	public List<Address> selectByNameS(String name) {
		return mapper.selectByName(name);
	}

	@Override
	public Address selectBySeq(long seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> selectByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}