package com.inn.nextDoorIt.dao;

import com.inn.nextDoorIt.entity.EnrollmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingEnrollmentDao extends JpaRepository<EnrollmentRecord, Integer> {
}
