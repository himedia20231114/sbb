package com.mysit.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequestMapping("/question")	// 하위 @GetMapping, @PostMapping 의 prefix 가 적용됨 
@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	// @Autowired : 타입으로 객체를 주입함. 동일한 타입이 주입될 수 있다. , JUnit Test 
	
	// 생성자를 통한 객체 주입 : 
	//private final QuestionRepository questionRepository; 
	
	private final QuestionService questionService; 
	
	//http://localhost:8585/question/list
	@GetMapping("/list")
	//@ResponseBody
	public String list(Model model) {
		
		//Model : 서버의 데이터를 client view 페이지로 전송 
		// 메소드 인풋 값으로 선언되면 객체가 자동으로 생성됨 
		
		// client 요청에 대한 비즈니스 로직 처리 : question 테이블의 list 를 출력 하라는 요청 
		List<Question> questionList = questionService.getList();  
		
		// model 에 담아서 client view 페이지로 전송 
							
		model.addAttribute("questionList", questionList); 
				
		//templates/question_list.html  
		// thymeleaf 라이브러리 설치시 view 페이지가 위치할 곳, .html 
		return "question_list" ; 	
	}
	
	//상세 글 조회 
	// http://localhost:8585/question/detail/{id}
	@GetMapping("/detail/{id}")
	public String detail (Model model, 
			@PathVariable("id") Integer id 
			) {
		
		System.out.println(id);
		// 백엔드의 로직 처리 
		Question question = 
				questionService.getQuestion(id); 
		
		System.out.println(question.getSubject());
		System.out.println(question.getContent());
		
		
		// model 에 담아서 client로 전송 
		model.addAttribute("question" , question); 
			
		return "question_detail"; 
	}
	
	// 질문 등록 하기 : 글 등로 뷰 페이지만 전송 
	// http://localhost:8585/question/create
	@GetMapping ("/create")
	public String questionCreate() {	
		return "question_form"; 
	}
	
	// 질문등록 DB에 값을 받아서 저장 
	@PostMapping("/create")
	public String questionCreate(
//			@RequestParam("subject") String subject, 
//			@RequestParam("content") String content
			@Valid QuestionForm questionForm, BindingResult bindingResult
			) {
		
		if ( bindingResult.hasFieldErrors()) {
			return "question_form"; 
		}		
		
		/*
		System.out.println("제목 : " + subject);
		System.out.println("내용 : " + content);
		*/ 
		questionService.create(questionForm.getSubject(), questionForm.getContent()); 
				
		return "redirect:/question/list" ; 
	}
	

}
