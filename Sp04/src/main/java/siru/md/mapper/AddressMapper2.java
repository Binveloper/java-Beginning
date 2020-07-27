package siru.md.mapper;

import java.util.List;

import siru.md.domain.Address;

public interface AddressMapper2 {
	List<Address> list();
	void insert(Address address);
	void delete(long seq);
}
