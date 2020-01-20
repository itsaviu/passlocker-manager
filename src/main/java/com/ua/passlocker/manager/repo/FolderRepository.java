package com.ua.passlocker.manager.repo;

import com.ua.passlocker.manager.models.entity.Folders;
import com.ua.passlocker.manager.models.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<Folders, Long> {

    List<Folders> findAllByUserDetails(UserDetails userDetails);

    Optional<Folders> findByIdAndUserDetails(Long id, UserDetails userDetails);
}
