package com.springrestfulhateoasvolunteer.volunteer;

import com.springrestfulhateoasvolunteer.volunteer.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerDao extends JpaRepository<Volunteer, Long> {
}
