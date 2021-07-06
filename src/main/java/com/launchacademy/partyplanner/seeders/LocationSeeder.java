package com.launchacademy.partyplanner.seeders;

import com.launchacademy.partyplanner.models.Location;
import com.launchacademy.partyplanner.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LocationSeeder implements CommandLineRunner {

  private final LocationService locationService;


  @Autowired
  public LocationSeeder(LocationService locationService) {
    this.locationService = locationService;
  }

  @Override
  public void run(String... args) throws Exception {

    if (locationService.findAll().size() == 0) {
      Location location = new Location();
      location.setName("Launch");
      location.setCity("Boston");
      location.setState("MA");
      location.setRentalPrice(72.59);
      locationService.save(location);

      Location location2 = new Location();
      location2.setName("5guys");
      location2.setState("NY");
      location2.setCity("New York");
      locationService.save(location2);
    }
  }
}
