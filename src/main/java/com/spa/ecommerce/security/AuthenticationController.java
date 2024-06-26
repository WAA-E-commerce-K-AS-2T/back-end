package com.spa.ecommerce.security;

import com.spa.ecommerce.user.User;
import com.spa.ecommerce.user.UserDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RequestMapping()
@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {
    private final UserDTOMapper userDTOMapper;
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(UserDTOMapper userDTOMapper, JwtService jwtService, AuthenticationService authenticationService) {
        this.userDTOMapper = userDTOMapper;
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        return authenticationService.signup(registerUserDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setUser(userDTOMapper.apply(authenticatedUser));

        return ResponseEntity.ok(loginResponse);
    }



    @PostMapping("/reset-password")
    public ResponseEntity<String> resetToken(@RequestBody LoginUserDto loginUserDto) {
        Optional<String> msg = authenticationService.resetToken(loginUserDto);
        return msg
                .map(message -> new ResponseEntity<>(message, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/reset-password/{token}")
    public ResponseEntity<String> resetPassword(@PathVariable String token, @RequestBody ResetPasswordDTO resetPasswordDTO) {
        Optional<String> msg = authenticationService.resetPassword(token, resetPasswordDTO);
        return msg
                .map(message -> new ResponseEntity<>(message, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
