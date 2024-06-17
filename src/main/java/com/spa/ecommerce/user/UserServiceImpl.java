package com.spa.ecommerce.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDTOMapper userDTOMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<UserDTO> getAll() {
        return userRepository.findAll().stream().map(userDTOMapper).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> get(Long id) {
        return userRepository.findById(id).map(userDTOMapper);
    }

    public Optional<UserDTO> save(UserDTO user) {
        User newUser = new User();
        newUser.setFullName(user.getFullName());
        newUser.setEmail(user.getEmail());
        userRepository.save(newUser);

        return Optional.of(userDTOMapper.apply(newUser));
    }

    public Optional<UserDTO> update(Long id, UserDTO updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setFullName(updatedUser.getFullName());
            existingUser.setEmail(updatedUser.getEmail());
            userRepository.save(existingUser);

            return Optional.of(userDTOMapper.apply(existingUser));
        } else {
            return Optional.empty();
        }
    }

    public Optional<UserDTO> delete(Long id, UserDTO updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            userRepository.deleteById(id);
            return Optional.of(userDTOMapper.apply(existingUserOpt.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDTO> activate(Long id) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setEnabled(true);
            userRepository.save(existingUser);

            return Optional.of(userDTOMapper.apply(existingUser));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDTO> deactivate(Long id) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setEnabled(false);
            userRepository.save(existingUser);

            return Optional.of(userDTOMapper.apply(existingUser));
        } else {
            return Optional.empty();
        }
    }

//    @Override
//    public Optional<String> resetPassword(Long id, ResetPasswordDTO resetPassword) {
//        Optional<User> existingUserOpt = userRepository.findById(id);
//        if (existingUserOpt.isPresent() && resetPassword.getPassword().equals(resetPassword.getRePassword())) {
//            User existingUser = existingUserOpt.get();
//            existingUser.setPassword(passwordEncoder.encode(resetPassword.getPassword()));
//            userRepository.save(existingUser);
//
//            return Optional.of("Password Successfully Changed");
//        } else {
//            return Optional.empty();
//        }
//    }
}
