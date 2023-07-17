package com.javalab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javalab.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

}
