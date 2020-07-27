package siru.md.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //dto일때는 꼭 써줘야 함
@AllArgsConstructor //생성자 만들어주는것

public class ChartVo {
	private String item;
	private int number;
	
	@Override //tip >>>>> 데이터를 검증할 때 편하게 검증하려고
	public String toString() {
		return "Vo4GoogleChart item: " + item + ", number: " + number;
	}
}
