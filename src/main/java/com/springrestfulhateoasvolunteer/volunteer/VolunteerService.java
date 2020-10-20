package com.springrestfulhateoasvolunteer.volunteer;

import com.springrestfulhateoasvolunteer.volunteer.exceptions.InvalidVolunteerCreationException;
import com.springrestfulhateoasvolunteer.volunteer.model.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    public List<Volunteer> findAll() {
        return volunteerRepository.findAll();
    }

    public void delete(Long id) {
        if(volunteerRepository.existsById(id)) {
            volunteerRepository.deleteById(id);
        } else {
            throw new VolunteerNotFoundException("A volunteer could not be found for ID " + id);
        }
    }

    public Volunteer create(Volunteer volunteer) {
        if(Objects.isNull(volunteer.getId())) {
            return volunteerRepository.save(volunteer);
        } else {
            throw new InvalidVolunteerCreationException("The input volunteer already has an ID.");
        }
    }

    public Volunteer findById(Long id) {
        return volunteerRepository.findById(id).orElseThrow(() -> new VolunteerNotFoundException("A volunteer could not be found for ID " + id));
    }
}
