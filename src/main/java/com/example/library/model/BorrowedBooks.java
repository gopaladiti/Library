package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name="borrowed_books")
public class BorrowedBooks {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name="book_Id", referencedColumnName = "id", unique = true)
    @NotNull
    private Books book;

    @ManyToOne
    @JoinColumn(name="user_Id")
    @NotNull
    private UserDetails user;

    @Column(name="borrowed_Date")
    private LocalDateTime borrowedDate;

    @Column(name="due_Date")
    private LocalDateTime dueDate;

    @Column(name="return_Date")
    private LocalDateTime returnDate;

    @JsonCreator
    public BorrowedBooks(@JsonProperty("book_Id") Books book,
                         @JsonProperty("user_Id") UserDetails user,
                         @JsonProperty("borrowed_Date") LocalDateTime borrowedDate,
                         @JsonProperty("due_Date") LocalDateTime dueDate,
                         @JsonProperty("return_Date") LocalDateTime returnDate) {
        this.book = book;
        this.user = user;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }
}
