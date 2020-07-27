package siru.md.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siru.md.domain.Address;
import siru.md.mapper.AddressMapper1;


@Service("AddressServiceImpl1")
public class AddressServiceImpl1 implements AddressService {
	@Autowired
	private AddressMapper1 addressMapper1;
	
	@Override
	public List<Address> listS() {
		// TODO Auto-generated method stub
		return addressMapper1.list();
	}
	@Override
	public void insertS(Address address) {
		// TODO Auto-generated method stub
		addressMapper1.insert(address);
	}
	@Override
	public void deleteS(long seq) {
		// TODO Auto-generated method stub
		addressMapper1.delete(seq);
	}

}
