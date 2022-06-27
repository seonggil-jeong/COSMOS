package com.cosmos.userservice.jpa;

import com.cosmos.userservice.jpa.entity.UserEntity;
import com.cosmos.userservice.jpa.entity.UserLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserLinkRepository extends JpaRepository<UserLinkEntity, Integer> {

}
