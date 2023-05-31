package kr.or.ddit.vo;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class Member {
	@NotBlank(message = "ID 비었다 이녀석아~!")
	private String userId = "a001";
	@NotBlank(message = "이름 비었다 이녀석아~!")
	@Size(max = 3)
	private String userName = "chosh";
	@NotBlank(message = "비밀번호 비었다 이녀석아~!")
	private String password = "1234";
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	@Email
	private String email;
	
	
	private int coin = 100;
	private String gender;
	private String hobby;
	private String[] hobbyArray;
	private List<String> hobbyList;
	private boolean foreigner;
	private String developer;
	private String nationality;
	// 중첩된 자바빈즈의 입력값 검증을 지정 
	@Valid
	private Address address;
	// 중첩된 자바빈즈의 입력값 검증을 지정 
	@Valid
	private List<Card> cardList;
	private String cars;
	private String[] carArray;
	private List<String> carList;	
	private String introduction;
	private String birthDay;
}
