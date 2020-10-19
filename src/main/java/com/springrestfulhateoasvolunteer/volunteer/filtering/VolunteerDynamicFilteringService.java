package com.springrestfulhateoasvolunteer.volunteer.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springrestfulhateoasvolunteer.volunteer.model.Volunteer;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class VolunteerDynamicFilteringService {

    public MappingJacksonValue createMapping(Volunteer volunteer, String[] fields) {
        MappingJacksonValue mapping = new MappingJacksonValue(volunteer);
        applyFilters(mapping, fields);
        return mapping;
    }

    public MappingJacksonValue createMapping(List<Volunteer> volunteers, String[] fields) {
        MappingJacksonValue mapping = new MappingJacksonValue(volunteers);
        applyFilters(mapping, fields);
        return mapping;
    }

    private void applyFilters(MappingJacksonValue mapping, String[] fields) {
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        SimpleBeanPropertyFilter filter;
        if(Objects.nonNull(fields)) {
            filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
        } else {
            filter = SimpleBeanPropertyFilter.serializeAll();
        }
        filterProvider.addFilter(VolunteerFilters.PARTIAL_RESPONSE_FILTER, filter);
        mapping.setFilters(filterProvider);
    }
}
