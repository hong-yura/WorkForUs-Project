package site.workforus.forus.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	
	@GetMapping(value="/thema")
	public String getThema(HttpSession session) {
		log.info("thema page");
		
		return "admin/thema";
	}
	
	
	/*
	@SuppressWarnings("unchecked")
	@ResponseBody
	@GetMapping(value = "/thema", produces="application/json; charset=utf-8")
	public String getImage(HttpSession session, HttpServletRequest request
			, MultipartHttpServletRequest mhsr
			, MultipartFile multipartFile
			, @RequestParam(value="adminFileId", defaultValue="19") int adminFileId) {
		
		log.info("ajax image");
		
		JSONObject json = new JSONObject();
		
		String realPath = request.getServletContext().getRealPath("/resources");
		String filePath = realPath + "/static/images/logo/"; // 저장 경로
		
		// 디폴트 이미지
		String defaultFileName = "default.png";
		String defaultUuid = UUID.randomUUID() + "_" + defaultFileName;
		
		// 서버에 업로드 된 파일 정보를 객체로 만들어 줌(input값)
		MultipartFile logo = mhsr.getFile("imageFile");
		
		if(!logo.isEmpty()) {
			// 파일명이 존재하면 해당 파일이 조회되게
			AdminFileUploadDTO maxAdminFileId = adminFileService.getMaxAdminFileId(adminFileId);
			
			String originFileName = logo.getOriginalFilename();
			
			json.put("logoImgPath", "/static/images/logo/" + originFileName);
			
		} else {
			// 파일명이 없으면 임의의 디폴트 이미지 업로드
			AdminFileUploadDTO adminFileData = new AdminFileUploadDTO();
			adminFileData.setAdminFileNm(defaultFileName);
			adminFileData.setAdminUuidFileNm(defaultUuid);
			adminFileData.setAdminUploadPath(request.getContextPath() + "/static/images/logo/" + defaultUuid);
			adminFileData.setAdminFileSize(multipartFile.getSize());
			
			adminFileService.addAdminFile(adminFileData);
			
			json.put("logoImgPath", "/static/images/logo/" + defaultFileName);
			
			//List<AdminFileUploadDTO> adminFileData = adminFileService.getAdminFile(adminFileId);
		}
		 
		return json.toString();
	}	
	*/
	
	
	// 추가
	@SuppressWarnings("unchecked")
	@ResponseBody
	@PostMapping(value = "/logoUpload", produces="application/json; charset=utf-8")
	public String adminImageUpload(HttpSession session, HttpServletRequest request
			, MultipartHttpServletRequest mhsr
			, @RequestParam(value="imageFile") MultipartFile multipartFile) throws ServletException, IOException {
		
		log.info("adminImageUpload(multipartFile={})", multipartFile);
		
		JSONObject json = new JSONObject();
		
		String realPath = request.getServletContext().getRealPath("/resources"); // 파일이 저장될 실제 경로 구하기
		String filePath = realPath + "/static/images/logo/"; // 저장 경로
		String originFileName = multipartFile.getOriginalFilename(); // 실제 파일명
		String uuidFileName = UUID.randomUUID() + "_" + originFileName; // 저장 파일명 난수 랜덤으로 중복 방지
		
		/*
		// 서버에 업로드 된 파일 정보를 객체로 만들어 줌(input값)
		MultipartFile logo = mhsr.getFile("imageFile");
		
		if(!logo.isEmpty()) { // 파일명이 존재하면 해당 파일이 조회되게
			AdminFileUploadDTO maxAdminFileId = adminFileService.getMaxAdminFileId(adminFileId);
		} else { // 파일명이 없으면 디폴트 이미지로
			int defaultId = 19;
			adminFileId = defaultId;
			AdminFileUploadDTO adminFileData = adminFileService.getAdminFile(adminFileId);
		}
		*/
		
		
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
