package com.example.hr_managament.entity;

import com.example.hr_managament.enums.InOutStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.sound.sampled.AudioFileFormat;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "input_output")
@Data
@EntityListeners(AudioFileFormat.class)
public class InputOutput {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    private InOutStatus inOutStatus;

    @ManyToOne
    private User user;

    @CreationTimestamp
    private Timestamp createdAt;

    public InputOutput(User user, Timestamp createdAt) {
        this.user = user;
        this.createdAt = createdAt;
    }
}
