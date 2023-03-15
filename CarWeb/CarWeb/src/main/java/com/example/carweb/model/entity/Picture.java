package com.example.carweb.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    @Column
    private String name;

    @Column
    private String contentType;

    @Lob
    @Column(length = Integer.MAX_VALUE)
    private byte[] data;

    @ManyToOne
    private Car car;
}
