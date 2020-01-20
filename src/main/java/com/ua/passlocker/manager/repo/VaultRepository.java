package com.ua.passlocker.manager.repo;

import com.ua.passlocker.manager.models.entity.Folders;
import com.ua.passlocker.manager.models.entity.UserDetails;
import com.ua.passlocker.manager.models.entity.Vault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface VaultRepository extends JpaRepository<Vault, Long> {

    List<Vault> findByFolderId(Folders folders);

    Optional<Vault> findByIdAndUserDetailId(Long id, UserDetails userDetailId);
}
