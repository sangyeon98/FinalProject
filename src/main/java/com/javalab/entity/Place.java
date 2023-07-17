
package com.javalab.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TBL_PLACE")
public class Place {

	@Id
    @Column(name = "PLACE_TITLE", length = 50, nullable = false)
    private String placeTitle;

    @Column(name = "PLACE_COMMENT", length = 200)
    private String placeComment;

    @Column(name = "PLACE_CONTENT", length = 225, nullable = false)
    private String placeContent;
    
    @Column(name = "PLACE_PARKING", length = 30)
    private String placeParking;
    
    @Column(name = "PLACE_OFF", length = 30)
    private String placeOff;

    @Column(name = "PLACE_TIME", length = 200)
    private String placeTime;

    @Column(name = "PLACE_PRICE", length = 200)
    private String placePrice;

    @Column(name = "PLACE_URL", length = 100)
    private String placeURL;

    @Column(name = "PLACE_TEL", length = 13)
    private String placeTel;

    @ManyToOne
    @JoinColumn(name = "CITY_NAME")
    private City city;

    @OneToMany(mappedBy = "place")
    private List<ScrapPlace> scrapPlaces;
    
    public Place(String placeTitle) {
    	this.placeTitle=placeTitle;
    }
}