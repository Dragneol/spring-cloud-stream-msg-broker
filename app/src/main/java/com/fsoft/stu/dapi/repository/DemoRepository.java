package com.fsoft.stu.dapi.repository;

import com.fsoft.stu.dapi.model.DemoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoRepository extends JpaRepository<DemoModel, Long> {

}
