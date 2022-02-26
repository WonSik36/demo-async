package com.example.demoasync;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "some")
@NoArgsConstructor
public class Some {
    public Some(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
}
