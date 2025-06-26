package com.mausse.capsuladotempo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Capsule {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name = "owner_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_capsule_user"))
    private User owner;

    @Column(columnDefinition="TEXT")
    private String message;

    private LocalDateTime sendDate;

    @ElementCollection
    private List<String> recipients;

    private boolean sent = false;
}
