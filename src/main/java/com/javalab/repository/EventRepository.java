package com.javalab.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javalab.entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

	/*
	 * @Query("select e, ei, avg(coalesce(r.grade,0)),  count(r) from Event e " +
	 * "left outer join EventImg ei on ei.event = e " +
	 * "left outer join Review  r on r.movie = m group by m ") Page<Object[]>
	 * getListPage(Pageable pageable);
	 * 
	 * 
	 * @Query("select m, mi ,avg(coalesce(r.grade,0)),  count(r)" +
	 * " from Movie m left outer join MovieImage mi on mi.movie = m " +
	 * " left outer join Review  r on r.movie = m "+
	 * " where m.mno = :mno group by mi") List<Object[]> getMovieWithAll(Long mno);
	 */
	}

	

