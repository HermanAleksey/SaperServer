package com.bsuir.herman.auth.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @MappedSuperclass
 * @EntityListeners(AuditingEntityListener.class)
 * @Getter
 * @Setter
 * @NoArgsConstructor
 * public abstract class AbstractAuditingEntity {
 *     @CreatedDate
 *     name = "created_date", updatable = false
 *     @JsonIgnore
 *     private String createdDate = Instant.now().toString();
 *
 *     @LastModifiedDate
 *     name = "last_modified_date"
 *     @JsonIgnore
 *     private String lastModifiedDate = Instant.now().toString();
 *
 *     name = "created_by"
 *     @CreatedBy
 *     private String createdBy;
 *
 *     name = "modified_by"
 *     @LastModifiedBy
 *     private String modifiedBy;
 * }
 *
 *
 */


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created")
    private Date created;

    @LastModifiedDate
    @Column(name = "updated")
    private Date updated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
