package com.mysit.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.mysit.sbb.answer.Answer;
import com.mysit.sbb.user.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {		
	//질문정보를 저장하는 테이블 

	//@Entity : DB의 테이블과 매핑 
		//Question : 객체명 = 테이블명 
		//  필드 : 테이블의 컬럼과 연결   
	//@Id : 필드위에 할당 , Primary Key (중복된 값을 못 넣도록 설정 ), 테이블에 반드시 1개가 적용  
	//@GeneratedValue : 자동으로 값을 증가해서 생성해줌 , @ID 같이 부여 
	//@Column : 컬럼의 제약 사항을 적용 
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id ; 
	
	@Column(length=200)
	private String subject; 
	
	@Column(length=20000)
	private String content; 
	private LocalDateTime createDate; 	//질문 생성 날짜 
	private LocalDateTime modifyDate;   //질문을 수정한 날짜 
	
	
	//질문(Question) : 1 , 답변(Answer) : 다 
	//하나의 질문에 대해서 모든 답변을 가져 올 수 있다. 
	// cascade=CascadeType.REMOVE   <== 질문이 제거 될때, 질문에 해당하는 모든 답변을 함께 제거함. 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question", cascade=CascadeType.REMOVE )
	private List<Answer> answerList; 
	

	// 글쓴 사용자 정보 컬럼 추가함. 
	// FK : author_id
	// SiteUser : 부모 , Question : 자식 
	@ManyToOne (fetch = FetchType.LAZY)
	private SiteUser author; 
	
	//글 추천 컬럼 추가 : @ManyToMany <== 새로운 테이블이 생성됨 
	// question :질문 ,    SiteUser : 추천
	// 하나의 질문에 많은 사용자가 추천 , 한 사용자는 많은 질문에 추천 
	// Question 과 SiteUser 와의 관계는 다:다 관계 
		// Set 에는 동일한 값을 중복 해서 넣을 수 없다. 
			//한번 투표한 사용자는 중복투표 하지 못한다. 
	// Question_Voter 라는 별도의 테입블이 생성됨  
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<SiteUser> voter; 
	
}
