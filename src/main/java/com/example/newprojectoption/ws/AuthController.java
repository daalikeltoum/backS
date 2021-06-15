package com.example.newprojectoption.ws;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.newprojectoption.bean.ERole;
import com.example.newprojectoption.bean.Role;
import com.example.newprojectoption.bean.Userr;
import com.example.newprojectoption.dao.RoleDao;
import com.example.newprojectoption.dao.UserDao;
import com.example.newprojectoption.jwt.JwtUtils;
import com.example.newprojectoption.request.SignupRequest;
import com.example.newprojectoption.response.JwtResponse;
import com.example.newprojectoption.response.MessageResponse;
import com.example.newprojectoption.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.newprojectoption.request.LoginRequest;

@RestController
//@CrossOrigin(origins = {"http://localhost:4200"})
@CrossOrigin(origins = "*",maxAge=3600)
@RequestMapping("/api/auth")
public class AuthController {



    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String roles = userDetails.getAuthoritie().getAuthority();
        //String roles = userDetails.getAuthoritie()


        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles
                ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        //test de l'existece
        if (userDao.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userDao.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // cree u compte
        Userr user = new Userr(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        //Set<String> strRoles = signUpRequest.getRole();
        //Set<Role> roles = new HashSet<>();
        Role strRole =signUpRequest.getRole();

        Role role =new Role();
        if (strRole == null) {

             role = roleDao.findByName("prof");
        } else {
            role=roleDao.findByName(strRole.getName());
                /*switch (role) {
                    case "admin":
                        Role adminRole = roleDao.findByName("admin")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleDao.findByName(ERole.ROLE_COORDONNATEUR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleDao.findByName(ERole.ROLE_PROF)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }*/

        }

        user.setRole(role);
        userDao.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}

