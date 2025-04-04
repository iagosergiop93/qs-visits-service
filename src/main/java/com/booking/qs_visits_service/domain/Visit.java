package com.booking.qs_visits_service.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "visits")
public class Visit {
    @Id
    private String id;
    private String orgId;
    private String locationId;
    private Timeslot timeslot;
    private String confirmationCode;

    @ManyToOne
    private VisitType visitType;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "visitId")
    private List<VisitStatus> status;

    @Override
    public String toString() {
        return "Visit{" +
                "id='" + id + '\'' +
                ", orgId='" + orgId + '\'' +
                ", locationId='" + locationId + '\'' +
                ", confirmationCode='" + confirmationCode + '\'' +
                '}';
    }
}
