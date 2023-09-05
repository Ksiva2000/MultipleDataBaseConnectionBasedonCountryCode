package com.tanasvi.multidb.entity;

import lombok.Data;

import javax.annotation.ManagedBean;
import javax.persistence.*;

import org.springframework.jmx.export.annotation.ManagedResource;

@Entity(name = "user")
@Table(name = "Users")
@Data
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

}
