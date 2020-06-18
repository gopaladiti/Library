package com.example.library.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name="user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_Id")
    private Integer userId;

    private String username;

    private String password;

    @Column(name="email_Id")
    private String emailId;
}
