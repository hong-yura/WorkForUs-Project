package site.workforus.forus.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import site.workforus.forus.board.model.PostUploadFileDTO;
import site.workforus.forus.board.service.BoardPostService;

@Controller	
public class SummernoteUploadImageController {
	@Autowired
	private BoardPostService postService;
	
	@RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(HttpServletRequest request , HttpSession session
										, @RequestParam("file") MultipartFile[] multipartFiles)  {
		
		JSONObject json = new JSONObject();
		
		// 내부경로로 저장
		String realPath = request.getServletContext().getRealPath("/resources"); // static의 진짜 파일위치 찾기
		String fileRoot = realPath+"static/images/board"; // 어디에 저장할 건지
		
		for(MultipartFile file : multipartFiles) {
			String originalFileName = file.getOriginalFilename();	//오리지날 파일명
			String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
			String savedFileName = originalFileName + "-" + UUID.randomUUID() + extension;	//저장될 파일 명 -> randomUUID -> 파일명 랜덤으로
					
			File targetFile = new File(fileRoot + savedFileName);	
			try {
				InputStream fileStream = file.getInputStream();
				FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
				json.put("url", request.getContextPath() + "/static/images/board" + savedFileName); // contextroot + resources + 저장할 내부 폴더명
				json.put("responseCode", "success");
			} catch (IOException e) {
				FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
				json.put("responseCode", "error");
				e.printStackTrace();
			}
			// uploadFileDTO에 저장을 해줘야 한다.
			PostUploadFileDTO fileData = new PostUploadFileDTO();
			fileData.setFileNm(savedFileName);
			fileData.setFileType(extension);
			fileData.setPostId((Integer)session.getAttribute("postId"));
			fileData.setSumm_yn("Y");
			fileData.setUploadLocation(realPath);
			fileData.setUploadUrl(request.getContextPath() + "/static/images/board" + savedFileName);
			
			// DTO를 db에 넘긴다.
			boolean result = postService.addPostData()
			
		}
		return json.toJSONString().toString(); // ajax로 저장된 url을 전달
		
	}
}
