package ru.BTLab.test.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
@Entity
@Table(name = "truancy")
public class Truancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "truancy_id")
    private final Long id;
    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "reason")
    private Reason reason;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "discounted")
    private Boolean discounted;
    @Column(name = "description")
    private String description;
    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}