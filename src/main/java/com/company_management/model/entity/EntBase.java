package com.company_management.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class EntBase implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @CreatedBy
    @Column(name = "CREATED_USER")
    private Long createdUser;

    @LastModifiedDate
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @LastModifiedBy
    @Column(name = "UPDATED_USER")
    private Long updatedUser;

    @Column(name = "IS_ACTIVE")
    private Integer isActive = 1;

    @Override
    public String toString() {
        return "[id:" + this.getId() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj instanceof EntBase) {
            if (this.getId() != null) {
                return this.getId().equals(((EntBase) obj).getId());
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdDate, createdUser, updatedDate, updatedUser, isActive);
    }
}
