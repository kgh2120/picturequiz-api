package com.kk.picturequizapi.domain.users.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable @NoArgsConstructor
public class UserId implements Serializable {

    @Column(name = "user_id")
    private Long id;

    private UserId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(id, userId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static UserId of(Long id) {
        return new UserId(id);
    }
}
