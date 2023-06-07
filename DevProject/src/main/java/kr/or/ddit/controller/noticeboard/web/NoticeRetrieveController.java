package kr.or.ddit.controller.noticeboard.web;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.controller.noticeboard.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice")
@Slf4j
public class NoticeRetrieveController {
	
	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value = "/list.do")
	public String noticeList(
			@RequestParam(name="page", required = false, defaultValue = "1")int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model) {
		log.info("noticeList() 실행!");
		
		PaginationInfoVO<NoticeVO> pagingVO = new PaginationInfoVO<NoticeVO>();
		
		// 검색 기능 추가 시 사용 
		if(StringUtils.isNotBlank(searchWord)) {    // 검색함 
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		pagingVO.setCurrentPage(currentPage);	// startRow, endRow, startPage, endPage가 결정 
		int totalRecord = noticeService.selectNoticeCount(pagingVO);	// 총 게시글 수 
		pagingVO.setTotalRecord(totalRecord);
		
		List<NoticeVO> dataList = noticeService.selectNoticeList(pagingVO);
		pagingVO.setDataList(dataList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "notice/list";		
	}
	
	@RequestMapping(value = "/detail.do")
	public String noticeDetail(int boNo, Model model) {
		NoticeVO noticeVO = noticeService.selectNotice(boNo);
		model.addAttribute("notice", noticeVO);
		return "notice/detail";
	}
}
