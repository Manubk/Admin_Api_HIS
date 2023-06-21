package com.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.entity.PlanCategoryEntity;

@Repository
public interface PlanCategoryRepo extends JpaRepository<PlanCategoryEntity, Integer> {

}
