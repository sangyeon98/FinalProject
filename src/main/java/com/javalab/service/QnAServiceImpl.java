package com.javalab.service;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.javalab.dto.QnADTO;
import com.javalab.entity.QnA;
import com.javalab.repository.QnARepository;

import java.util.Optional;

import java.util.List;

@Service
public class QnAServiceImpl implements QnAService {
    private final QnARepository qnaRepository;
    private final ModelMapper modelMapper;

    public QnAServiceImpl(QnARepository qnaRepository, ModelMapper modelMapper) {
        this.qnaRepository = qnaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<QnADTO> getAllQnA() {
        List<QnA> qnaList = qnaRepository.findAll();
        return qnaList.stream()
                .map(qna -> modelMapper.map(qna, QnADTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public QnADTO getQnAByChatQ(String chatQ) {
        Optional<QnA> qnaOptional = qnaRepository.findByChatQ(chatQ);
        if (qnaOptional.isPresent()) {
            QnA qna = qnaOptional.get();
            return modelMapper.map(qna, QnADTO.class);
        }
        return null;
    }
}