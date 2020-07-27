package siru.md.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface DragdropService {
	MultipartHttpServletRequest getMultipartRequest();
	void setMultipartRequest(MultipartHttpServletRequest multipartrequest);
	Map<String, List<String>> getUpdateFileName();
}
