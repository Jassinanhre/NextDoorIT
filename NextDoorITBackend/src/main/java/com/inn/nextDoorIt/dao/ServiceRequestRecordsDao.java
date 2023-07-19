package com.inn.nextDoorIt.dao;

import com.inn.nextDoorIt.POJO.ServiceRequestRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRequestRecordsDao extends JpaRepository<ServiceRequestRecord, Integer> {
}
