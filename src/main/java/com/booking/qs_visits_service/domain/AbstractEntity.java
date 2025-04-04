package com.booking.qs_visits_service.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Persistable;

import java.time.Instant;

@MappedSuperclass
@Data
public abstract class AbstractEntity<ID> implements Persistable<ID> {

    @JsonIgnore
    @CreationTimestamp
    Instant createdAt;

    @JsonIgnore
    @UpdateTimestamp
    Instant updatedAt;

    @Transient
    @JsonIgnore
    private boolean isNew = false;

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PostLoad
    public void postload() {
        this.isNew = false;
    }
}
