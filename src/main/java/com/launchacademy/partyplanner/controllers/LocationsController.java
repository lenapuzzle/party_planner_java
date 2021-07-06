
package com.launchacademy.partyplanner.controllers;

import com.launchacademy.partyplanner.models.Location;
import com.launchacademy.partyplanner.models.Party;
import com.launchacademy.partyplanner.services.LocationService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/locations")
public class LocationsController {

  private final LocationService locationService;

  @Autowired
  public LocationsController(LocationService locationService) {
    this.locationService = locationService;
  }

  @GetMapping
  public String getLocationsIndex(Model model) {
    List<Location> locations = locationService.findAll();
    model.addAttribute("locations", locations);
    return "locations/index";
  }

  @GetMapping("/{id}")
  public String getLocationShow(@PathVariable Integer id, Model model) {
    Optional<Location> location = Optional.ofNullable(locationService.findById(id));
    if(location.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
      model.addAttribute("location", location.get());
      List<Party> parties = location.get().getParties();
      if (parties.size() > 0) {
        model.addAttribute("parties", parties);
      }
    return "locations/show";
  }

  @GetMapping("/new")
  public String getNewLocation(@ModelAttribute @Valid Location location, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      return "locations/new";
    }
      locationService.save(location);
    return "redirect:/locations";
    }

  @PostMapping
  public String createLocation(@ModelAttribute @Valid Location location, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      return "locations/new";
    }
    locationService.save(location);
    return "redirect:/locations";
  }
}