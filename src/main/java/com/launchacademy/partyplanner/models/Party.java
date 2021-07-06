
package com.launchacademy.partyplanner.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "parties")
public class Party {

  @Id
  @SequenceGenerator(name = "party_generator",
      sequenceName = "parties_id_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator = "party_generator")
  @Column(name = "id", nullable = false, unique = true, columnDefinition = "serial")
  private Integer id;

  @NotBlank
  @Size(min=1, max=255, message = "Name should be between 3 and 255 characters")
  @Column(name = "name")
  private String name;

  @NotBlank
  @Size(min=1, max=255, message = "Description should be between 3 and 255 characters")
  @Column(name = "description")
  private String description;

  @ManyToOne
  @JoinColumn(name = "location_id", nullable = false)
  @JsonIgnoreProperties("parties")
  private Location location;

  public void setLocation(Location location) {
    this.location = location;
  }
}