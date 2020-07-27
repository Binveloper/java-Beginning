package siru.md.mapper;

import org.apache.ibatis.annotations.Insert;

public interface TxSample1Mapper {
	@Insert("insert into tbl_sample1 values(#{data})")
	public int insertT1(String data);
}
