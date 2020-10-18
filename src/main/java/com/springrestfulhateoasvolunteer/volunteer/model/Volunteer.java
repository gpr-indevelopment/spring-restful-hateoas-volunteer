package com.springrestfulhateoasvolunteer.volunteer.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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

@ApiModel(description = "Volunteer")
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
    @ApiModelProperty(notes = "Phone number must have between 10 and 11 digits.")
    private Long phoneNumber;

    @Past
    @ApiModelProperty(notes = "Enter date must be a past date.")
    private Date enterDate;
}
