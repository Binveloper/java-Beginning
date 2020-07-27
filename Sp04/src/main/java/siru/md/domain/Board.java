package siru.md.domain;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	private long seq;
	private String writer;
	private String email;
	private String subject;
	private String content;
	private Date rdate;
	private ArrayList<MultipartFile> files;
	
	public Board(long seq, String writer, String email, String subject, String content, Date rdate) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.email = email;
		this.subject = subject;
		this.content = content;
		this.rdate = rdate;
	}
	
	
}
