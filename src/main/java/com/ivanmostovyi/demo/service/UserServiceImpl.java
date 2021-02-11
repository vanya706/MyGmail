package com.ivanmostovyi.demo.service;

import com.ivanmostovyi.demo.domain.User;
import com.ivanmostovyi.demo.domain.UserRole;
import com.ivanmostovyi.demo.dto.UserFormDto;
import com.ivanmostovyi.demo.repository.UserRepository;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService, UserDetailsService, AdminUserInitializer {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private Environment environment;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           Environment environment,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.environment = environment;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Override
    public void create(UserFormDto userFormDto) {

        User user = User.builder()
                .username(userFormDto.getUsername())
                .password(passwordEncoder.encode(userFormDto.getPassword()))
                .role(UserRole.ROLE_USER)
                .active(true)
                .locked(false)
                .build();

        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    @Override
    public void createAdminUserIfNotExists() {

        if (userRepository.existsByRole(UserRole.ROLE_ADMIN)) {
            return;
        }

        User newAdminUser = User.builder()
                .username(environment.getProperty("admin.default.username"))
                .password(bCryptPasswordEncoder.encode(
                        Objects.requireNonNull(environment.getProperty("admin.default.password")))
                )
                .role(UserRole.ROLE_ADMIN)
                .active(Boolean.TRUE)
                .build();

        userRepository.save(newAdminUser);

    }

}
