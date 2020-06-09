package com.example.library.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="user_details")
public class UserDetails {

    @Id
    @Column(name="user_Id")
    private int userId;

    private String username;

    private String password;

    @Column(name="email_Id")
    private String emailId;
}
