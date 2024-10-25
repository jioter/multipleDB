package com.multipledb.repository;

import com.multipledb.entity.UserEntity;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface UserRepository extends JpaRepositoryImplementation<UserEntity, Long> {

}
