package com.javalab.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalab.dto.NoticeDTO;
import com.javalab.dto.PageRequestDTO;
import com.javalab.dto.PageResultDTO;
import com.javalab.dto.UserDTO;
import com.javalab.entity.Notice;
import com.javalab.entity.User;
import com.javalab.service.NoticeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/notice")
@Controller
public class NoticeController {

    private final NoticeService noticeService;
    
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }
    
    // 공지사항 목록 조회
    @GetMapping("/list")
    public void noticeList(PageRequestDTO pageRequestDTO, Model model) {
    	PageResultDTO<NoticeDTO, Notice> result = noticeService.noticeList(pageRequestDTO);
        model.addAttribute("result", result);
        
    }
    
    // 상세보기
    @GetMapping("/read")
    public String getNoticeDetails(
    			@RequestParam Integer noticeNo, Model model,
    			HttpSession session, HttpServletResponse response) {
        log.info("getNoticeDetails");
        
        log.info("여기는 read 컨트롤러 " );
        NoticeDTO dto = noticeService.read(noticeNo);
        model.addAttribute("notice", dto);          
        
        log.info("NoticeDTO 값1 확인 : " + dto.getNoticeNo());
        log.info("NoticeDTO 값2 확인 : " + dto.getNoticeTitle());
        log.info("NoticeDTO 값3 확인 : " + dto.getNoticeContent());
        log.info("NoticeDTO 값4 확인 : " + dto.getNoticeDate());
        log.info("NoticeDTO 값4 확인 : " + dto.getUser().getUserId());

        UserDTO loggedInUser = (UserDTO) session.getAttribute("loggedInUser");
        
        // Check if the user is the author of the notice or an admin
        if (loggedInUser == null || (!loggedInUser.getUserId().equals(dto.getUser().getUserId()) && !loggedInUser.getUserId().equals("admin"))) {
            response.setContentType("text/html;charset=UTF-8");
            try {
                PrintWriter out = response.getWriter();
                out.println("<script>alert('작성자만 확인 가능한 게시물입니다.'); history.go(-1);</script>");
                out.flush();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (loggedInUser != null && loggedInUser.getUserId().equals("admin")) {
            return "notice/read";
        }
        
        return "notice/read";
    }
  
  
    
    // 수정
    @GetMapping("/modify")
    public void modify(@RequestParam("noticeNo") Integer noticeNo,
                       @ModelAttribute("noticedto") NoticeDTO noticedto,
                       BindingResult bindingResult,
                       Model model) {
        log.info("modify - get");

        NoticeDTO dto = noticeService.read(noticeNo);
        model.addAttribute("notice", dto);
    }

    @PostMapping("/modify")
    public String modify(@ModelAttribute("noticedto") @Valid NoticeDTO dto,
                         BindingResult bindingResult,
                         Model model) {
        log.info("modify - post dto: " + dto.toString());

        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error : fieldErrors) {
                log.error(error.getField() + " " + error.getDefaultMessage());
            }

            model.addAttribute("notice", dto);
            
            return "notice/modify";
        }

        noticeService.modify(dto);

        return "redirect:/notice/list";
    }
    
    
    // 글쓰기
    @GetMapping("/register")
    public String registerForm(Model model, 
    		@ModelAttribute("noticeDTO") NoticeDTO noticeDTO,
    		BindingResult bindingResult, HttpSession session, HttpServletResponse response) {
        log.info("createForm");
        model.addAttribute("notice", new Notice());
        
        UserDTO loggedInUser = (UserDTO) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
        	 response.setContentType("text/html;charset=UTF-8");
             try {
                 PrintWriter out = response.getWriter();
                 out.println("<script>alert('로그인 후 작성이 가능합니다.'); location.href='/user/login';</script>");
                 out.flush();
                 return null;
             } catch (IOException e) {
                 e.printStackTrace();
             }	 
        } else {
        	 NoticeDTO notice = NoticeDTO.builder()
                     .user(User.builder().userId(loggedInUser.getUserId()).build())
                     .build();
             model.addAttribute("noticeDTO", notice);
             model.addAttribute("loggedInUser", loggedInUser); // 현재 로그인한 사용자 아이디 추가
        }
        return "notice/register";
        }
 
    
    @PostMapping("/register")
    public String register(@ModelAttribute @Valid NoticeDTO notice, 
    						BindingResult bindingResult, 
    						Model model, HttpSession session) {
        log.info("register");
        
        UserDTO loggedInUser = (UserDTO) session.getAttribute("loggedInUser");
  
        
        // 검증시 오류 있으면
        if (bindingResult.hasErrors()) {
        	
            // Log field errors
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error : fieldErrors) {
                log.error( error.getField() + " "+ error.getDefaultMessage());
            }

            /*
             * 오류값 객체 전송
             * "org.springframework.validation.BindingResult.categoryDTO"
             *  - 페이지에서 th:errors="${categoryDTO.categoryName}" 형태로 값을
             *    꺼내서 사용할 수 있음. 
             */
			//ra.addFlashAttribute("org.springframework.validation.BindingResult.categoryDTO", bindingResult);
            
            model.addAttribute("notice", notice);
            log.info("register");
            
            return "notice/register";
        }        
        
        // 검증 오류 없음
        notice.setUser(User.builder().userId(loggedInUser.getUserId()).build());
        noticeService.register(notice);
        
        return "redirect:/notice/list";
    }
    
    
    // 삭제
    @GetMapping("/delete/{noticeNo}")
    public String deleteNotice(@PathVariable Integer noticeNo) {
    	
        boolean deleted = noticeService.remove(noticeNo);
        
        return "redirect:/notice/list";
    }

}