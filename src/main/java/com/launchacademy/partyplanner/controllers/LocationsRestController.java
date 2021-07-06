package com.launchacademy.partyplanner.controllers;

import com.launchacademy.partyplanner.models.Location;
import com.launchacademy.partyplanner.services.LocationRestService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationsRestController {

  private LocationRestService locationRestService;

  @Autowired
  public LocationsRestController(LocationRestService locationRestService) {
    this.locationRestService = locationRestService;
  }

  @GetMapping
  public Map<String, Iterable<Location>> getList(Pageable pageable) {
    Map<String, Iterable<Location>> map = new HashMap<>();
    map.put("locations", locationRestService.findAll(pageable)); //////
    return map;
  }

  @GetMapping("/{id}")
  public Location getLocationInfo(@PathVariable Integer id) {
    Optional<Location> location = locationRestService.findById(id);
    if(location.isPresent()) {
      return location.get();
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }
}
