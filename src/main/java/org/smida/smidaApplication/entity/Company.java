package org.smida.smidaApplication.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Company {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String registrationNumber;
    private String address;
    private Timestamp createdAt;


}
