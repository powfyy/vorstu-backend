package dev.vorstu.controllers;

import dev.vorstu.configs.jwt.JwtUtils;
import dev.vorstu.entity.Role;
import dev.vorstu.entity.Student;
import dev.vorstu.entity.User;
import dev.vorstu.pojo.JwtResponse;
import dev.vorstu.pojo.LoginRequest;
import dev.vorstu.pojo.MessageResponse;
import dev.vorstu.pojo.SignupRequest;
import dev.vorstu.repositories.StudentRepository;
import dev.vorstu.repositories.UserRepository;
import dev.vorstu.services.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api")
@Slf4j
public class AuthorizationController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt =jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .findFirst()
                .orElseGet(() -> "STUDENT");;

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(), role));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {

        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exist"));
        }
        User newUser = new User (null, signupRequest.getUsername(), Role.STUDENT,passwordEncoder.encode(signupRequest.getPassword()) );
        Student newStudent = new Student(signupRequest.getFio(),signupRequest.getGroup(), signupRequest.getPhoneNumber());
        newUser.setStudent(newStudent);
        studentRepository.save(newStudent);
        userRepository.save(newUser);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }
}
