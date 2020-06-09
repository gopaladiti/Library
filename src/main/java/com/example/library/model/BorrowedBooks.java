package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor(onConstructor_ = @JsonCreator)
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="borrowed_books")
public class BorrowedBooks {

    @Id
    @GeneratedValue
    private int id;

    @JsonProperty("bookId")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private Books book;

    @JsonProperty("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_Id")
    private UserDetails user;

    @JsonProperty("borrowedDate")
    @Column(name="borrowed_Date")
    private LocalDateTime borrowedDate;

    @JsonProperty("dueDate")
    @Column(name="due_Date")
    private LocalDateTime dueDate;

    @JsonProperty("returnDate")
    @Column(name="return_Date")
    private LocalDateTime returnDate;
}
