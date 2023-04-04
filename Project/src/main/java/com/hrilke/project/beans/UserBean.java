package com.hrilke.project.beans;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBean {

	private int user_num;

	@Size(min = 2, max = 4)
	@Pattern(regexp = "[가-힣]*")
	private String user_name;

	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String user_id;

	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String user_pw;

	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String user_pw2;

	// 쿠키
	private boolean cook;
	// 사용자가 중복확인을 했는지 안했는지 검사
	private boolean userIdExist;
	// 사용자가 로그인을 했는지 안했는지에대한 판단 여부 : 로그인 성공하면 true값으로 변경되게
	private boolean userLogin;

	public UserBean() {
		this.userIdExist = false;
		this.userLogin = false;
		this.cook = false;
	}

	// DTO : 서로 다른 계층 간에 데이터를 전송하는 데 사용 (로직을 가지지 않는 순수한 데이터객체) getter/setter
	// DAO : 데이터베이스의 data에 접근하고 데이터 액세스 논리를 비즈니스 논리에서 분리하는 데 사용
			// 유저가 form데이터를 DTO에 넣어서 전송 -> 해당 DTO를 받은 서버가 DAO를 이용해서 데이터 베이스로 넣음

	// VO : 데이터를 단일 객체로 캡슐화하는 데 사용 (Read-Only 특징)
			// 사용하는 도중에 변경 불가능하게 만들고 오직 읽기만 가능하게 DTO와 유사하지만 setter가 없어 값이 변하지 않음!

//	public class VO {
//	    private int productId;
//	    private String productName;
//	    private BigDecimal price;
//
//	    public ProductVO(int productId, String productName, BigDecimal price) {
//	        this.productId = productId;
//	        this.productName = productName;
//	        this.price = price;
//	    }
//
//	    public int getProductId() {
//	        return productId;
//	    }
//
//	    public String getProductName() {
//	        return productName;
//	    }
//
//	    public BigDecimal getPrice() {
//	        return price;
//	    }
//	}
}
