package com.javalab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javalab.dto.QnADTO;
import com.javalab.service.QnAService;

@Controller
@RequestMapping("/qna")
public class QnAController {
	
	@Autowired
    private final QnAService qnaService;

    public QnAController(QnAService qnaService) {
        this.qnaService = qnaService;
    }

    @GetMapping("/list")
    public String getAllQnA(Model model) {
    	List<QnADTO> result = qnaService.getAllQnA();
    	model.addAttribute("result", result);
        return "qna/list";      
    }

    @GetMapping("/{chatQ}")
    @ResponseBody
    public String getQnAByChatQ(@PathVariable String chatQ) {
        QnADTO qnaDTO = qnaService.getQnAByChatQ(chatQ);
        if (qnaDTO != null) {
            return qnaDTO.getChatA();
        } else {
            return "No matching answer found.";
        }
    }
}
