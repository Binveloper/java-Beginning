package siru.md.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Files {
	   private long f_num;
	   private String fname;
	   private String ofname;
	   private long fSize;
	   private long seq;
}
