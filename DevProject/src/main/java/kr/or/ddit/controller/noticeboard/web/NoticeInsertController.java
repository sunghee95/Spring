package kr.or.ddit.controller.noticeboard.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.controller.noticeboard.service.INoticeService;
import kr.or.ddit.vo.DDITMemberVO;
import kr.or.ddit.vo.NoticeVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice")
@Slf4j
public class NoticeInsertController {
	
	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value = "/form.do", method = RequestMethod.GET)
	public String noticeInsertForm() {
		return "notice/form";
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String noticeInsert(
			HttpServletRequest req,
			RedirectAttributes ra,
			NoticeVO noticeVO, Model model) {
		String goPage = "";
		Map<String, String> errors = new HashMap<String, String>();
		if(StringUtils.isBlank(noticeVO.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요");
		}
		
		if(StringUtils.isBlank(noticeVO.getBoContent())) {
			errors.put("boContent", "내용을 입력해주세요");
		}
		
		if(errors.size() > 0) {	//에러 발생 
			model.addAttribute("errors", errors);
			model.addAttribute("noticeVO", noticeVO);
			goPage = "notice/form";
		} else {
			HttpSession session = req.getSession();
			DDITMemberVO memberVO = (DDITMemberVO) session.getAttribute("SessionInfo");
			if(memberVO != null) {
				noticeVO.setBoWriter(memberVO.getMemId());		// 로그인 한 사용자 아이디로 작성자 세팅
				ServiceResult result = noticeService.insertNotice(req, noticeVO);
				if(result.equals(ServiceResult.OK)) {
					goPage = "redirect:/notice/detail.do?boNo=" + noticeVO.getBoNo();
				} else {
					model.addAttribute("message", "서버에러, 다시 시도해주세요!!!");
					goPage = "notice/form";
				}
			} else {
				ra.addFlashAttribute("message", "로그인 후에 사용 가능합니다 !");
				goPage = "redirect:/notice/login.do";
			}
		}
		return goPage;
	}
	
	// 요청 URI : /notice/generalForm
	@GetMapping("/generalForm")
	public String generalForm() {
		//forwarding
		return "notice/generalForm";
	}
	
	/*
	 * 요청 URI : /notice/generalFormPost
	      요청방식  :post
	      요청 파라미터 : {boTitle = 제목입니다, boContent = 내용입니다, bowriter = 개똥이, boFile = 파일객체}
	   
	   @ResponseBody : json, but 텍스트로 리턴
	 */
//	@ResponseBody
//	@PostMapping("/generalFormPost")
//	public String generalFormPost(NoticeVO noticeVO) {
//		
//		log.info("notice : " + noticeVO);
//		
//		// 파일을 업로드할 대상
//		String uploadFolder = "D:\\A_TeachingMaterial\\6.JspSpring\\02.SPRING2\\workspace_spring2\\DevProject\\src\\main\\webapp\\resources\\upload";
//		//파일 객체 꺼내오기
//		MultipartFile[] boFile = noticeVO.getBoFile();
//		
//		// 파일 배열객체로부터 파일 하나씩 꺼내기
//		for(MultipartFile multipartFile : boFile) {
//			log.info("----------------");
//			log.info("upload file name : " + multipartFile.getOriginalFilename());
//			log.info("upload file size : " + multipartFile.getSize());
//			log.info("upload file contentType : " + multipartFile.getContentType());
//			
//			//File 객체 복사 설계 (복사할 경로, 파일명)
//			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
//			
//			//연/월/일 폴더 생성 
//			
//			// UUID처리(파일명 중복 방지)
//			
//			// 파일 복사 실행 (파일 원본. transferTo(설계))
//			try {
//				multipartFile.transferTo(saveFile);
//				return "Success";
//			} catch (IllegalStateException | IOException e) {
//				log.error(e.getMessage());
//				return "Failed";
//			}
//		}
//		
//		// insert/update/delete의 경우 returnType="int"를 생략
//		this.noticeService.insertNotice(noticeVO);
//		
//		return "Success";
//	}

}
