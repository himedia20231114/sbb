package com.mysit.sbb.question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
public class Question_Select_Test3 {

	@Autowired
	private QuestionRepository questionRepository; 
	
	@Test
	void question_Select_Test3() {
		
		List<Sort.Order> sorts = new ArrayList<>(); 
		sorts.add(Sort.Order.desc("createDate")); 
		
		// Pageabl 객체에 : 요청하는페이지번호, 한페이지에 출력될 레코드 갯수, 정렬 
		Pageable pageable = PageRequest.of(0, 10, Sort.by(sorts)); 
		
		Page<Question> pages = 
				questionRepository.findAll(pageable); 
		
		System.out.println("전체 레코드 수 : " + pages.getTotalElements());
		System.out.println("페이지당 출력 레코드 갯수 : " + pages.getSize());
		System.out.println("전체 페이지 갯수 : " + pages.getTotalPages());
		
		System.out.println("현재 요청 페이지 번호 : " + pages.getNumber());
		System.out.println("이전페이지 존재여부 : " + pages.hasPrevious());
		System.out.println("다음 페이지 존재여부 : " + pages.hasNext());
		System.out.println("===================================");	
		
		// Page 는 방번호가 없음 : 향상된 FOR, Iterator 를 사용해서 출력 
		
		System.out.println("==== 향상된 FOR 문으로 출력 =======");
		for (Question q : pages) {
			System.out.println(q.getId());
			System.out.println(q.getSubject());
			System.out.println(q.getContent());
			System.out.println();
		}
		
		System.out.println("==== Iterator 으로 출력 =======");
		// Iterator 선언(장착) 
		Iterator<Question> iterator = pages.iterator(); 
		
		while (iterator.hasNext()) {
			
			Question q = iterator.next(); 
			System.out.println(q.getId());
			System.out.println(q.getSubject());
			System.out.println(q.getContent());
			System.out.println();
		}
		
	}
	
}
