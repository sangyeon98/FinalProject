package com.javalab.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_COURSE")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_NO", nullable = false)
    private int courseNo;

    @Column(name = "COURSE_TITLE", length = 225, nullable = false)
    private String courseTitle;

    @Column(name = "COURSE_CONTENT", length = 225, nullable = false)
    private String courseContent;

    @Column(name = "COURSE_KILOMETER", length = 225)
    private String courseKilometer;

    @Column(name = "COURSE_DAY", length = 225)
    private String courseDay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PLACE_TITLE")
    private Place place;

    @OneToMany(mappedBy = "course")
    private List<ScrapCourse> scrapCourses;
    
    public Course(Integer courseNo) {
    	this.courseNo = courseNo;
    }

}
