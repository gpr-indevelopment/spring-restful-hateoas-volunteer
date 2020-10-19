package com.springrestfulhateoasvolunteer.volunteer;

import com.springrestfulhateoasvolunteer.volunteer.filtering.VolunteerDynamicFilteringService;
import com.springrestfulhateoasvolunteer.volunteer.model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/volunteers")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    private VolunteerDynamicFilteringService volunteerDynamicFilteringService;

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
    public MappingJacksonValue getAllVolunteers(@RequestParam(required = false) String[] fields) {
        return volunteerDynamicFilteringService.createMapping(volunteerService.findAll(), fields);
    }

    @GetMapping("/{id}")
    public MappingJacksonValue getVolunteerById(@PathVariable Long id, @RequestParam(required = false) String[] fields) {
        Volunteer foundVolunteer = volunteerService.findById(id);
        foundVolunteer.add(linkTo(methodOn(this.getClass()).getAllVolunteers(null)).withRel("all-volunteers"));
        return volunteerDynamicFilteringService.createMapping(foundVolunteer, fields);
    }
}
