package siru.md.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siru.md.domain.Address;
import siru.md.mapper.AddressMapper2;


@Service("AddressServiceImpl2")
public class AddressServiceImpl2 implements AddressService {
	@Autowired
	private AddressMapper2 addressMapper2;
	
	@Override
	public List<Address> listS() {
		// TODO Auto-generated method stub
		return addressMapper2.list();
	}
	@Override
	public void insertS(Address address) {
		// TODO Auto-generated method stub
		addressMapper2.insert(address);
	}
	@Override
	public void deleteS(long seq) {
		// TODO Auto-generated method stub
		addressMapper2.delete(seq);
	}

}
