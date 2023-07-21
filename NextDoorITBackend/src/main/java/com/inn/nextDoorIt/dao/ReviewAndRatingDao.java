package com.inn.nextDoorIt.dao;

import com.inn.nextDoorIt.POJO.ReviewAndRatingsRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewAndRatingDao extends JpaRepository<ReviewAndRatingsRecord, Integer> {
    public List<ReviewAndRatingsRecord> findByServiceId(int serviceId);
}
