package com.javalab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.javalab.entity.QnA;

@Repository
public interface QnARepository extends JpaRepository<QnA, String> {
    Optional<QnA> findByChatQ(String chatQ);
}