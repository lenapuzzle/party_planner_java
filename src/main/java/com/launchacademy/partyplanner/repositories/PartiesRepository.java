package com.launchacademy.partyplanner.repositories;

import com.launchacademy.partyplanner.models.Party;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartiesRepository extends CrudRepository<Party, Integer> {

}
