package siru.md.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	String saveStore(MultipartFile f);
	boolean writeFile(MultipartFile f, String saveFileName);
}
