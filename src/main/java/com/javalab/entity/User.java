package com.javalab.entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_USER")
public class User {

    @Id
    @Column(name = "USER_ID", length = 8, nullable = false)
    private String userId;

    @Column(name = "USER_PW", length = 16, nullable = false)
    private String userPassword;

    @Column(name = "USER_NAME", length = 10, nullable = false)
    private String userName;

    @Column(name = "USER_HP", length = 13)
    private String userPhoneNumber;

    @Column(name = "USER_ADDRESS", length = 50)
    private String userAddress;

    @OneToMany(mappedBy = "user")
    private List<ScrapPlace> scrapPlaces;

    @OneToMany(mappedBy = "user")
    private List<ScrapCourse> scrapCourses;

    @OneToMany(mappedBy = "user")
    private List<Notice> notices;

    
    // User 생성자 
    
    public User(String userId) {
    	this.userId = userId;
    }
    
    public User(String userId, String userPassword, String userName, String userPhoneNumber, String userAddress) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userPhoneNumber = userPhoneNumber;
        this.userAddress = userAddress;
    }

}
