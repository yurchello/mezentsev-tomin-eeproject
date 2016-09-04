package com.mezentsev_tomin.adminpanel.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Mezentsev.Y on 9/3/2016.
 */

@Entity
@Table(name="TEST_MODEL")
public class TestModel implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name="FIRST_NAME", nullable=false)
    private String firstName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "TestModel[" +
                "id=" + id +
                ", firstName=" + firstName +
                ']';
    }
}
