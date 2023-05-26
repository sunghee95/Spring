package kr.or.ddit.vo;

import lombok.Data;

@Data
public class BoardVO {
	
	private int boNo;      			// 게시판 번호  
	private String boTitle;			// 게시판 제목
	private String boWriter;		// 게시판 작성자
	private String boContent;		// 게시판 내용
	private String boDate;			// 게시판 작성일 
	private int boHit;				// 게시판 조회수
	

}
