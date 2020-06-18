package com.example.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookRequest {

    private Books book;

    private UserInfo user;

    private LocalDateTime borrowedDate;

    private LocalDateTime dueDate;

    private LocalDateTime returnDate;
}
