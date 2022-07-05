package com.cosmos.userservice.jpa;

import com.cosmos.userservice.jpa.entity.UserEvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserEvaluationRepository extends JpaRepository<UserEvaluationEntity, Integer> {
}
