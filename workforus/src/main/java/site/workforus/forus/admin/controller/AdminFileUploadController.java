package site.workforus.forus.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import site.workforus.forus.admin.model.AdminFileUploadDTO;
import site.workforus.forus.admin.service.AdminFileUploadService;

@Slf4j
@Controller
@MultipartConfig
@RequestMapping(value = "/admin")
public class AdminFileUploadController {
	
	@Autowired
	private AdminFileUploadService adminFileService;
	
	@GetMapping(value = "/thema")
	public String getImage(HttpSession session) {
		log.info("thema page");
		return "admin/thema";
	}	
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@PostMapping(value = "/logoUpload", produces="application/json; charset=utf-8")
	public String adminImageUpload(HttpSession session, HttpServletRequest request
			, @RequestParam(value="imageFile", required=false) MultipartFile multipartFile) throws ServletException, IOException {
		
		log.info("adminImageUpload(multipartFile={})", multipartFile);
		
		JSONObject json = new JSONObject();
		
		String realPath = request.getServletContext().getRealPath("/resources"); // 파일이 저장될 실제 경로 구하기
		String filePath = realPath + "/static/images/logo/"; // 저장 경로
		
		String originFileName = multipartFile.getOriginalFilename(); // 실제 파일명
		String uuidFileName = UUID.randomUUID() + "_" + originFileName; // 중복방지 랜덤 파일명


		File saveFile = new File(filePath + uuidFileName); // 저장경로 + 저장될 데이터가 담긴 파일명
		try {
			multipartFile.transferTo(saveFile);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
		AdminFileUploadDTO adminFileData = new AdminFileUploadDTO();
		adminFileData.setAdminFileNm(originFileName);
		adminFileData.setAdminUuidFileNm(uuidFileName);
		adminFileData.setAdminUploadPath(request.getContextPath() + "/static/images/logo/" + uuidFileName);
		adminFileData.setAdminFileSize(multipartFile.getSize());
		
		adminFileService.addAdminFile(adminFileData);
		return json.toString();
	}
	
}
