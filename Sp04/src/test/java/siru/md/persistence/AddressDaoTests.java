package siru.md.persistence;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;
import siru.md.dao.AddressDao;
import siru.md.domain.Address;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AddressDaoTests {
	//@Autowired
	//@Inject
	@Resource
	private AddressDao addressDao;
	
	/*@Test
	public void testList() {
		List<Address> list = addressDao.list();
		log.info("#testList() list: " + list);
	}*/
	
	/*
	@Test
	public void testInsert() {
		Address address = new Address(-1L, "이수빈", "평택시", null);
		addressDao.insert(address);
		
		List<Address> list = addressDao.list();
		log.info("#testList() list(1):" + list);
	}
	*/
	
	
	@Test
	public void testDelete() {
		addressDao.delete(3);
		
		List<Address> list = addressDao.list();
		log.info("#testList() list(2):" + list);
	}
	
}
