package kr.or.ddit.controller.noticeboard.view;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.view.AbstractView;

public class NoticeDownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> noticeFileMap = (Map<String, Object>) model.get("noticeFileMap");
		
		File saveFile = new File(noticeFileMap.get("fileSavepath").toString());
		String fileName = (String) noticeFileMap.get("fileName");
		String agent = request.getHeader("User-Agent");
		if(StringUtils.containsIgnoreCase(agent, "msie") ||
				StringUtils.containsIgnoreCase(fileName, "trident")) {
			fileName = URLEncoder.encode(fileName, "UTF-8");   // IE. Chrome
		} else {
			fileName = new String(fileName.getBytes(), "ISO-8859-1");	// firefox, chrome
		}
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setHeader("Content-Length", noticeFileMap.get("fileSize").toString());
		
		//try-with-resource
		//()안에 객체를 finally 구문에서 close 하지 않아도 try-catch 구문이 완료가 되면 
		// 자동으로 해당 객체가 close 된다 
		try (
			OutputStream os = response.getOutputStream();
		){ 
			FileUtils.copyFile(saveFile, os); 	//파일 다운로드 
		}
	}

}
