
package com.launchacademy.partyplanner.repositories;

import com.launchacademy.partyplanner.models.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Integer> {
  Location findByName(String name);
}
