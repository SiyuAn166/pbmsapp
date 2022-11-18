package com.petrobest.pbmsapp.system.vo;

import lombok.Data;

@Data
public class UserPasswordVO {
    private String username;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

}
