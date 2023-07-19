package com.example.hr_managament.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.sound.sampled.AudioFileFormat;
import java.sql.Timestamp;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "monthly_salary")
@EntityListeners(AudioFileFormat.class)

public class MonthlySalary {

    @Id
    @GeneratedValue
    private UUID  id;

    private double amount;


    @ManyToOne
    private Month month;

    @ManyToOne
    private User owner;


    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp cratedAt;

    @UpdateTimestamp
    private Timestamp updatedAt;


    @CreatedBy
    @Column(updatable = false)
    private UUID createdBy;

    @LastModifiedBy
    private UUID updatedBy;


    public MonthlySalary(double amount, Month month, User owner) {
        this.amount = amount;
        this.month = month;
        this.owner = owner;
    }
}
