package com.example.midterm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Double price;
    @Column
    private String description;
    @Column
    private String imageUrl;
    @Column
    @Lob
    private byte[] image;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Color color;

    public enum Category {
        Phone,
        Tablet,
        Headset,
        Keyboard
    }

    public enum Color {
        Black,
        Pink,
        BLue,
        Red,
    }
}