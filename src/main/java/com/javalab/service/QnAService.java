package com.javalab.service;


import com.javalab.dto.QnADTO;

import java.util.List;


public interface QnAService {
	 List<QnADTO> getAllQnA();
	   QnADTO getQnAByChatQ(String chatQ);
}