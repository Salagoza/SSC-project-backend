package io.muzoo.ssc.project.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "restaurant")
public class RestaurantEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "rating")
    private double rating = 0;

    @Column(name = "rating_count")
    private int ratingCount = 0;
}
