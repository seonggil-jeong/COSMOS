package com.cosmos.userservice.jpa;

import com.cosmos.userservice.jpa.entity.UserEntity;
import com.cosmos.userservice.jpa.entity.UserStackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserStackRepository extends JpaRepository<UserStackEntity, Integer> {

}
