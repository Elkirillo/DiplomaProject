package com.company.service_request.entity;

import com.company.service_request.helpingpack.Status;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "REQUEST", indexes = {
        @Index(name = "IDX_REQUEST_ASSIGNEE_ID", columnList = "ASSIGNEE_ID"),
        @Index(name = "IDX_REQUEST_ASSIGNOR_ID", columnList = "ASSIGNOR_ID"),
        @Index(name = "IDX_REQUEST_DEPARTAMENT_ID", columnList = "DEPARTAMENT_ID")
})
@Entity
public class Request {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME", nullable = false)
    @NotNull
    private String name;

    @NotNull
    @JoinColumn(name = "ASSIGNOR_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User assignor;

    @JoinColumn(name = "ASSIGNEE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User assignee;

    @Composition
    @OneToMany(mappedBy = "request")
    private List<Report> reports;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @Column(name = "ESTIMATED_TIME")
    private Integer estimatedTime;

    @Column(name = "STATUS")
    private String status;

    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    @Lob
    private String description;

    @JoinColumn(name = "DEPARTAMENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Departament departament;

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public void setStatus(Status status) {
        this.status = status == null ? null : status.getId();
    }

    public Status getStatus() {
        return status == null ? null  : Status.fromId(status);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }

    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Integer estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public User getAssignor() {
        return assignor;
    }

    public void setAssignor(User assignor) {
        this.assignor = assignor;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}