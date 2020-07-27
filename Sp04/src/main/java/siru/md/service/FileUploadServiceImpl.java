package siru.md.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import siru.md.filesetting.Path;

@Log4j
@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Override
	public String saveStore(MultipartFile f) {
		String ofname = f.getOriginalFilename();
		int idx = ofname.lastIndexOf(".");
		String ofheader = ofname.substring(0, idx);
		String ext = ofname.substring(idx);
		long ms = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		sb.append(ofheader); //file 이름
		sb.append("_"); 
		sb.append(ms); //시간
		sb.append(ext); //확장자 (idx부터 끝까지) ex) .jpg .png
		String saveFileName = sb.toString(); //StringBuilder의 메소드.. 어펜드한 문자열들을 합해주는 것
		long fsize = f.getSize();
		
		//log.info("#Service ofname: " + ofname + ", saveFileName: " + saveFileName + ", fsize: " + fsize);
		
		boolean flag = writeFile(f, saveFileName);
		if(flag) {
			log.info("업로드 성공");
		}else{
			log.info("업로드 실패");
		}
		// return Path.FILE_STORE + saveFileName;
		return saveFileName;
	}

	@Override
	public boolean writeFile(MultipartFile f, String saveFileName) {
		File dir = new File(Path.FILE_STORE);
		if(!dir.exists()) dir.mkdirs();
		FileOutputStream fos = null;
		try {
			byte data[] = f.getBytes();
			fos = new FileOutputStream(Path.FILE_STORE + saveFileName);
			fos.write(data);
			fos.flush();
			
			return true;
		}catch(IOException ie) {
			return false;
		}finally {
			try {
				fos.close();
			}catch(IOException ie) {}
		}
	}

}
/*

File.mkdir()

만들고자 하는 디렉토리의 상위 디렉토리가 존재하지 않을 경우, 생성 불가

C:\base\want

want 디렉토리를 만들고자 하는데, base 디렉토리가 없는 경우, 생성 불가

--------------------------------------------------------------
File.mkdirs()

만들고자 하는 디렉토리의 상위 디렉토리가 존재하지 않을 경우, 상위 디렉토리까지 생성

C:\base\want

want 디렉토리를 만들고자 하는데, base 디렉토리가 없는 경우, base 디렉토리까지 생성

*/