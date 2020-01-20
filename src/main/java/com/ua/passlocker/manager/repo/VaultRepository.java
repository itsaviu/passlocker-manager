package com.ua.passlocker.manager.repo;

import com.ua.passlocker.manager.models.entity.Folders;
import com.ua.passlocker.manager.models.entity.Passmanager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PassManagerRepository extends JpaRepository<Passmanager, Long> {

    List<Passmanager> findByGroupId(Folders groupId);
}
