package com.ehr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.ehr.dto.StaffCreateDto;
import com.ehr.dto.StaffLoginRequest;
import com.ehr.dto.StaffLoginResponse;
import com.ehr.models.Staff;
import com.ehr.service.StaffService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private StaffService staffService;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/create")
    public ResponseEntity<?> createStaff(@RequestBody StaffCreateDto dto) {
        Staff staff = staffService.createStaff(dto);
        return ResponseEntity.ok(staff);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody StaffLoginRequest req, HttpServletRequest request) {
        try {
            UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(req.getWorkId(), req.getPassword());

            Authentication auth = authenticationManager.authenticate(authToken);
            
            SecurityContextHolder.getContext().setAuthentication(auth);

            HttpSession session = request.getSession(true);

            return ResponseEntity.ok(
                new StaffLoginResponse("Login successful", session.getId())
            );
        } catch (Exception e) {
            return ResponseEntity.status(401).body(
                new StaffLoginResponse("Login failed: " + e.getMessage(), null)
            );
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(new StaffLoginResponse("Logout successful", null));
    }
}
