package siru.md.service;

import java.util.List;

import siru.md.domain.Address;

public interface AddressService {
	List<Address> listS();
	void insertS(Address address);
	void deleteS(long seq);
}
