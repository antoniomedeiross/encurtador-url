package com.antonio.encurtador.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String urlLonga;
    private String urlCurta;

    @Column(updatable = false)
    private LocalDate createdAt = LocalDate.now();
}
