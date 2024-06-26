package com.mysit.sbb.answer;

import java.time.LocalDateTime;
import java.util.Set;

import com.mysit.sbb.question.Question;
import com.mysit.sbb.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Answer {
	// 답변을 저장하는 테이블 : 질문에 대한 답변을 저장하는 테이블 
	//  Question : 부모 , Answer : 자식 (Question 테이블의 ID 컬럼을 참조해서 답변을 저장 ) 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length = 20000)
	private String content; 
	
	private LocalDateTime createDate; 	// 컬럼이름 : create_date
	private LocalDateTime modifyDate;   // 수정한 날짜. 
	
	//Foreign Key : Answer 테이블은 Question 테이블을 참조함. 
	// @MayToOne : JPA에서 테이블 간의 관계 설정 , 자식 테이블(Answer)에서 부모 테이블(Question)을 참조 
	// question 컬럼은 Question 테이블의 Primary Key 의 값이 들어옴 
	// question 컬럼이름이 question_id 로 이름이 바뀜. 
	@ManyToOne(fetch = FetchType.LAZY)
	private Question question ; 
	
	
	// 글쓴 사용자 정보 컬럼 추가함. 
	// FK : author_id
	// SiteUser : 부모 , Question : 자식 
	@ManyToOne(fetch = FetchType.LAZY)
	private SiteUser author;
	
	// 별도의 테이블이 생성됨 : answer_voter 테이블이 생성됨 
		// 컬럼 2개 생성 : FK 
		// answer_id : answer 테이블의 id를 참조
		// voter_id : site_user 테이블의 id를 참조함. 
	
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<SiteUser> voter; 

}
