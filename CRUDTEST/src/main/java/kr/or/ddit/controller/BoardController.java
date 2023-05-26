package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;


@Controller
@RequestMapping("/board")


public class BoardController {
	
	@RequestMapping(value="insert", method = RequestMethod.POST)
	public String boardInsert(Member member, Model model) {
		return "";
	}
	
	
	
	@RequestMapping(value="/list")
	public String list() {
		
		return "pages/ddit_list";
	}
}
