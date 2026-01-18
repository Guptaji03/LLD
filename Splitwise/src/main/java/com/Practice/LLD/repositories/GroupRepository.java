package com.Practice.LLD.repositories;

import com.Practice.LLD.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository  extends JpaRepository<Group,Long> {
}
