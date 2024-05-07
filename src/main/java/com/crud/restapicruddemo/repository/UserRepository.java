package com.crud.restapicruddemo.repository;

import com.crud.restapicruddemo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
