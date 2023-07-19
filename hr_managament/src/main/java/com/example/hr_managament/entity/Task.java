package com.example.hr_managament.entity;

import com.example.hr_managament.absEntity.BaseEntity;
import com.example.hr_managament.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.sound.sampled.AudioFileFormat;
import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class Task {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "deadline", nullable = false)
    private Timestamp endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private boolean acceptedByOwner = false;

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

    private boolean completed;

    @Enumerated(EnumType.STRING)
    private Status taskStatus;

    public Task(String name, String description, Timestamp endTime, User owner) {
        this.name = name;
        this.description = description;
        this.endTime = endTime;
        this.owner = owner;
    }
}
