package com.spa.ecommerce.profile;


import com.spa.ecommerce.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getProfile(Principal principal) {
        Optional<?> user = userService.getCurrentUser(principal);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProfile(@PathVariable("id") Long id, @RequestBody ProfileDTO profileDTO) {
        String msg = userService.updateUser(id, profileDTO);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
