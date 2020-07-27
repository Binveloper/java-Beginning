package siru.md.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;
import siru.md.domain.Address;

@RestController//@controller보다 더욱 강력한 기능
@RequestMapping("rest")
@Log4j
public class RestControllerWithAddress {
	
	@GetMapping(value = "/getText", produces = "text/plain;charset=utf-8")
	public String getText() { //여기서 리턴하는 건 뷰로 이동하는 게 아니라 클라우드 어쩌고 웅앵
		log.info("#getText()");
		
		return "안녕";
	}
	
	@GetMapping(value = "getAddress",
				produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Address getAddress() {
		return new Address(1, "홍길동", "서울시", new Date(2020-1900, 7-1, 20));
	}
	
	@GetMapping(value = "getList",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Address> getList() {
		List<Address> list = new ArrayList<Address>();
		Address a1 = new Address(1, "홍길동", "서울시", new Date(2020-1900, 7-1, 20));
		Address a2 = new Address(1, "이순신", "인천시", new Date(2020-1900, 7-1, 20));
		list.add(a1); list.add(a2);
		
		return list;
	}
	
	@GetMapping(value = "getMap",
			produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Map<String, Address> getMap(){
		Map<String, Address> map = new HashMap<String, Address>();
		Address a1 = new Address(1, "홍길동", "서울시", new Date(2020-1900, 7-1, 20));
		Address a2 = new Address(1, "이순신", "인천시", new Date(2020-1900, 7-1, 20));
		
		map.put("first", a1);
		map.put("second", a2);
		
		return map;
	}
	
	@GetMapping(value = "check")
	public ResponseEntity<Address> check(@RequestParam double height, @RequestParam("weight") double w) {
		log.info("#check() height: " + height + ", weight : " + w);
		Address a = new Address(1, "홍길동", "서울시", new Date(2020-1900, 7-1, 20));
		ResponseEntity<Address> result = null;
		
		if(height>150.0) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(a);
		}else {
			result = ResponseEntity.status(HttpStatus.OK).body(a);
		}
		return result;
	}
	
	//클라이언트 요청 path로부터 값을 추출하는 예
	@GetMapping(value="product/{cat}/{pid}")
	public String[] getPath(@PathVariable("cat") String c, @PathVariable int pid) {
		String strs[] = {"카테고리: " + c + ", 상품 아이디: " + pid};
		
		return strs;
	}
}
