package com.javalab.service;



import java.util.List;



import com.javalab.dto.NoticeReviewDTO;
import com.javalab.entity.Notice;
import com.javalab.entity.NoticeReview;
import com.javalab.entity.User;




public interface NoticeReviewService {

	NoticeReview createComment(NoticeReviewDTO comment);
	
	List<NoticeReviewDTO> getCommentsByNoticeNo(Integer noticeNo);
	

	
	default NoticeReview dtoToEntity(NoticeReviewDTO dto) {
		return NoticeReview.builder()
				.reviewContent(dto.getReviewContent())
				.notice(new Notice(dto.getNoticeNo()))
				.user(new User(dto.getUserId()))
				.build();
	}	
	
	default NoticeReviewDTO entityToDTO(NoticeReview noticeReview) {
		 NoticeReviewDTO dto = new NoticeReviewDTO();
		    dto.setReviewNo(noticeReview.getReviewNo());
		    dto.setReviewContent(noticeReview.getReviewContent());

		    // Notice 엔티티에서 noticeNo 가져오기
			Integer noticeno = noticeReview.getNotice().getNoticeNo();
			dto.setNoticeNo(noticeno);
			 
		    // User 엔티티에서 userId 가져오기
		    String userId = noticeReview.getUser().getUserId();
		   	dto.setUserId(userId);

		    return dto;
		}

}