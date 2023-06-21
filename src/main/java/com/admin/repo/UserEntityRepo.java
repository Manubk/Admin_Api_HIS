package com.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.admin.entity.UserEntity;
import java.util.List;


@Repository
public interface UserEntityRepo extends JpaRepository<UserEntity, Long>{

	public UserEntity  findByEmail(String email);
}
