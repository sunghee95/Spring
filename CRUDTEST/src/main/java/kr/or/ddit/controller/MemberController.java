package kr.or.ddit.controller;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.service.IMemberService;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/main")
@Slf4j
public class MemberController {
	@Inject
	private IMemberService memberService;
	
	@RequestMapping(value="/signin", method = RequestMethod.GET)
	public String signin() {			
		return "pages/ddit_signin";
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public String signup(Member member, Model model) {
		log.info("signup() 실행!");
		String goPage = "";
		Map<String, String> error = new HashMap<String, String>();
		
		
		
		return "pages/ddit_signup";
	}


}
