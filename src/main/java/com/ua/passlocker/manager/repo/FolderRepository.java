package com.ua.passlocker.manager.repo;

import com.ua.passlocker.manager.models.entity.Groups;
import com.ua.passlocker.manager.models.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Groups, Long> {

    @Query("select distinct grp from Groups grp inner join grp.childGroup where grp.userDetails = :userDetails")
    List<Groups> findAllByUserDetailsSelfJoin(@Param("userDetails") UserDetails userDetails);

    Optional<Groups> findByIdAndUserDetails(Long id, UserDetails userDetails);
}
