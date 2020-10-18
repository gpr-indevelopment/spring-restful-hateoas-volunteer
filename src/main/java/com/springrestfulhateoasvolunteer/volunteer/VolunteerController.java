package com.springrestfulhateoasvolunteer.volunteer;

import com.springrestfulhateoasvolunteer.volunteer.model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @PostMapping
    public ResponseEntity<Void> createVolunteer(@RequestBody @Valid Volunteer volunteer) {
        Volunteer createdVolunteer = volunteerService.create(volunteer);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdVolunteer.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        volunteerService.delete(id);
    }

    @GetMapping
    public List<Volunteer> getAllVolunteers() {
        return volunteerService.findAll();
    }

    @GetMapping("/{id}")
    public Volunteer getVolunteerById(@PathVariable Long id) {
        Volunteer foundVolunteer = volunteerService.findById(id);
        foundVolunteer.add(linkTo(methodOn(this.getClass()).getAllVolunteers()).withRel("all-volunteers"));
        return foundVolunteer;
    }
}
