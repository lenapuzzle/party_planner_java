package com.launchacademy.partyplanner.seeders;

import com.launchacademy.partyplanner.models.Location;
import com.launchacademy.partyplanner.models.Party;
import com.launchacademy.partyplanner.services.LocationService;
import com.launchacademy.partyplanner.services.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartySeeder {

  private final PartyService partyService;
  private final LocationService locationService;

  @Autowired
  public PartySeeder(PartyService partyService, LocationService locationService) {
    this.partyService = partyService;
    this.locationService = locationService;
  }

  public void run(String... args) throws Exception {

    if(partyService.findAll().size() == 0) {

      Location launch = locationService.findByName("Launch");
      Party newYear = new Party();
      newYear.setName("new years party");
      newYear.setDescription("starts at 10");
      newYear.setLocation(launch);
      partyService.save(newYear);

      Party birthday = new Party();
      birthday.setName("birthday");
      birthday.setDescription("starts at 10");
      birthday.setLocation(launch);
      partyService.save(birthday);

      Location fiveGuys = locationService.findByName("5guys");
      Party dimSum = new Party();
      dimSum.setName("dim sum");
      dimSum.setDescription("starts at 10");
      dimSum.setLocation(fiveGuys);
      partyService.save(dimSum);

      Party promotion = new Party();
      promotion.setName("great promotion");
      promotion.setDescription("starts at 10");
      promotion.setLocation(fiveGuys);
      partyService.save(promotion);
    }
  }
}

