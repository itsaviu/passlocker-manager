package com.ua.passlocker.manager.repo;

import com.ua.passlocker.manager.models.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetails, Long> {

    Optional<UserDetails> findByEmailId(String emailId);

}
