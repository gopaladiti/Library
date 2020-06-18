package com.example.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponse implements Serializable {

    private String jwt;

    /*public AuthResponse(String jwt) {
        this.jwt = jwt;
    }*/
}
