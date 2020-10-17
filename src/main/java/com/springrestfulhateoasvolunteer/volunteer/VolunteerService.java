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
    private VolunteerDao volunteerDao;

    public List<Volunteer> findAll() {
        return volunteerDao.findAll();
    }

    public void delete(Long id) {
        if(volunteerDao.existsById(id)) {
            volunteerDao.deleteById(id);
        } else {
            throw new VolunteerNotFoundException("A volunteer could not be found for ID " + id);
        }
    }

    public Volunteer create(Volunteer volunteer) {
        if(Objects.isNull(volunteer.getId())) {
            return volunteerDao.save(volunteer);
        } else {
            throw new InvalidVolunteerCreationException("The input volunteer already has an ID.");
        }
    }

    public Volunteer findById(Long id) {
        return volunteerDao.findById(id).orElseThrow(() -> new VolunteerNotFoundException("A volunteer could not be found for ID " + id));
    }
}
