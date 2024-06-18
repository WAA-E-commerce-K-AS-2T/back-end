package com.spa.ecommerce.user;

import com.spa.ecommerce.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(Constant.ADMIN_USER_URL_PREFIX)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<Collection<UserDTO>> index(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable("id") Long id){
        return userService.get(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO user) {
        Optional<UserDTO> savedUser = userService.save(user);
        return savedUser.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO user) {
        Optional<UserDTO> updatedUserOpt = userService.update(id, user);
        return updatedUserOpt
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable Long id, @RequestBody UserDTO user) {
        Optional<UserDTO> updatedUserOpt = userService.delete(id, user);
        return updatedUserOpt
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<UserDTO> activate(@PathVariable Long id) {
        Optional<UserDTO> updatedUserOpt = userService.activate(id);
        return updatedUserOpt
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<UserDTO> deactivate(@PathVariable Long id) {
        Optional<UserDTO> updatedUserOpt = userService.deactivate(id);
        return updatedUserOpt
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @PutMapping("/{id}/reset-password")
//    public ResponseEntity<String> resetPassword(@PathVariable Long id, @RequestBody ResetPasswordDTO resetPassword) {
//        Optional<String> updatedUserOpt = userService.resetPassword(id, resetPassword);
//        return updatedUserOpt
//                .map(message -> new ResponseEntity<>(message, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
}
