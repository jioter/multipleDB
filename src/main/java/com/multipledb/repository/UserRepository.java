package com.multipledb.repository;

import com.multipledb.entity.UserEntity;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepositoryImplementation<UserEntity, Long> {

}
