package com.ehr.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ehr.models.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long>{
    Optional<Staff> findByWorkId(String workId);
    boolean existsByWorkId(String workId);
    
}
