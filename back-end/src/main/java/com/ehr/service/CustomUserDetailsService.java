package com.ehr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ehr.models.Staff;
import com.ehr.repository.StaffRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StaffRepository staffRepository;
    
    @Override
    public UserDetails loadUserByUsername(String workId) throws UsernameNotFoundException {
        Staff staff = staffRepository.findByWorkId(workId)
            .orElseThrow(() -> new UsernameNotFoundException("Staff not found: " + workId));

        return new User(
            staff.getWorkId(),
            staff.getPassword(), 
            List.of(new SimpleGrantedAuthority("ROLE_" + staff.getRole().name()))
        );
    }
}
