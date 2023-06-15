package com.example.cardapio.food;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Data
@Entity(name = "foods")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private BigDecimal price;

    private String image;
}
