package com.springrestfulhateoasvolunteer.volunteer.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Getter @Setter
public class Volunteer extends RepresentationModel<Volunteer> {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Gender gender;

    @Digits(fraction = 0, integer = 11)
    @Min(1_000_000_000)
    private Long phoneNumber;

    @Past
    private Date enterDate;
}
