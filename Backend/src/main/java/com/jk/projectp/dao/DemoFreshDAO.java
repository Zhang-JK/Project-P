package com.jk.projectp.dao;

import com.jk.projectp.model.DemoFresh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface DemoFreshDAO extends JpaRepository<DemoFresh, Integer> {

    int countByDemoId(int demoId);

    List<DemoFresh> findAllByDemoIdOrderByTimeIndexAsc(int interviewId);

    DemoFresh findByFreshId(Integer freshId);
}
