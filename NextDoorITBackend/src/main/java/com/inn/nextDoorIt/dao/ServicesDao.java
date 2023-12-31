package com.inn.nextDoorIt.dao;

import com.inn.nextDoorIt.entity.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicesDao extends JpaRepository<ServiceModel, Integer> {

    @Query(nativeQuery = true, value = "select * from services where category_id=:category_id")
    List<ServiceModel> findServicesByCategories(@Param("category_id") int category_id);
}
