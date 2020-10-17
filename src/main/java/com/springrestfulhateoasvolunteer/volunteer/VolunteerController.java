package com.springrestfulhateoasvolunteer.volunteer;

import com.springrestfulhateoasvolunteer.volunteer.model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid Volunteer volunteer) {
        Volunteer createdVolunteer = volunteerService.create(volunteer);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdVolunteer.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        volunteerService.delete(id);
    }

    @GetMapping
    public List<Volunteer> getAll() {
        return volunteerService.findAll();
    }

    @GetMapping("/{id}")
    public Volunteer getVolunteer(@PathVariable Long id) {
        Volunteer foundVolunteer = volunteerService.findById(id);
        foundVolunteer.add(linkTo(methodOn(this.getClass()).getAll()).withRel("all-volunteers"));
        return foundVolunteer;
    }
}
