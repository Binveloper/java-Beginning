package siru.md.service;

import java.util.List;

import siru.md.domain.Address;

public interface AddressAjaxService {
	List<Address> listS();
	void insertS(Address address);
	void deleteS(long seq);
	
	// for Ajax //
	
	Address selectBySeq(long seq);
	List<Address> selectByName(String name);
	Address selectBySeqS(long seq);
	List<Address> selectByNameS(String name);
}
