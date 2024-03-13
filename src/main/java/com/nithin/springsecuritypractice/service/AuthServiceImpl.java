package com.nithin.springsecuritypractice.service;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nithin.springsecuritypractice.dto.LoginDto;
import com.nithin.springsecuritypractice.dto.RegisterDto;
import com.nithin.springsecuritypractice.entity.Role;
import com.nithin.springsecuritypractice.entity.User;
import com.nithin.springsecuritypractice.exception.CredentialsException;
import com.nithin.springsecuritypractice.repository.RoleRepository;
import com.nithin.springsecuritypractice.repository.UserRepository;
import com.nithin.springsecuritypractice.security.JwtTokenProvider;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public User register(RegisterDto registerDto) {

        // check username is already exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new CredentialsException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        // check email is already exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new CredentialsException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        
        if(registerDto.getIsDeveloper())
        {	
        	Role developerRole = roleRepository.findByName("ROLE_DEVELOPER");
        	roles.add(developerRole);
        }	

        if(registerDto.getIsLead())
        {	
        	Role leadRole = roleRepository.findByName("ROLE_LEAD");
        	roles.add(leadRole);
        }	
        
        if(registerDto.getIsManager())
        {	
        	Role managerRole = roleRepository.findByName("ROLE_MANAGER");
        	roles.add(managerRole);
        }	
        
        if(registerDto.getIsCXO())
        {	
        	Role cxoRole = roleRepository.findByName("ROLE_CXO");
        	roles.add(cxoRole);
        }
        
      if(registerDto.getIsFreelancer())
      {	
      	Role freelancerRole = roleRepository.findByName("ROLE_FREELANCER");
      	roles.add(freelancerRole);
      }        
        user.setRoles(roles);
        return  userRepository.save(user);
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }
}
