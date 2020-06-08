package com.example.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="borrowed_books")
public class BorrowedBooks {

    @Id
    private int id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="bookId", referencedColumnName = "id")
    private Books book;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="userId", referencedColumnName = "userId")
    private UserDetails user;

    private LocalDateTime borrowedDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
}
