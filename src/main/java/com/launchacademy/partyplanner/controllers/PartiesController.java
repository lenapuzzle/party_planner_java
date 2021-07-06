package com.launchacademy.partyplanner.controllers;

import com.launchacademy.partyplanner.models.Party;
import com.launchacademy.partyplanner.services.LocationService;
import com.launchacademy.partyplanner.services.PartyService;
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
@RequestMapping("/parties")
public class PartiesController {
private final LocationService locationService;
private final PartyService partyService;

@Autowired
public PartiesController(PartyService partyService, LocationService locationService) {
    this.partyService = partyService;
    this.locationService = locationService;
}

@GetMapping("/new")
public String getNewPartyForm(@ModelAttribute Party party, Model model) {
    model.addAttribute("locations", locationService.findAll());
    return "parties/new";
}

@GetMapping("/{id}")
public String getPartyShow(@PathVariable Integer id, Model model) {
    Optional<Party> showParty = partyService.findById(id);

    if(showParty.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    model.addAttribute("party", showParty.get());
    return "parties/show";
}
@PostMapping
public String createNewParty(@ModelAttribute @Valid Party party, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      return "parties/new";
    }
    partyService.save(party);
    return "redirect:/parties/" + party.getId();
   }
}
