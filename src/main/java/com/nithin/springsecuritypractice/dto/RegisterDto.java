package com.nithin.springsecuritypractice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String name;
    private String username;
    private String email;
    private String password;
    
    private Boolean isDeveloper;
    private Boolean isLead;
    private Boolean isManager;
    private Boolean isCXO;
    private Boolean isFreelancer;
}
