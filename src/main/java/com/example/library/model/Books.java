package com.example.library.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="books")
public class Books {

    @Id
    private int id;
    private String title;
    private String author;
    private String description;
    private int available;
}
