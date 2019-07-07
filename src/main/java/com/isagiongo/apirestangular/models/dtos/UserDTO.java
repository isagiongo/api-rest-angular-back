package com.isagiongo.apirestangular.models.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDTO {

    private Long id;

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email.")
    private String email;

    public UserDTO(){ }

    public UserDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
