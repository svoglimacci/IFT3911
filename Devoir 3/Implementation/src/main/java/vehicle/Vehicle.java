package vehicle;

import itinerary.Itinerary;
import java.util.ArrayList;
import section.Section;
import section.TravelClass;

public class Vehicle {

  private String id;
  private ArrayList<Itinerary> itineraries;
  private ArrayList<Section> sections;

  public Vehicle(String id) {
    this.id = id;
    this.itineraries = new ArrayList<>();
    this.sections = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Section getSections(TravelClass travelClass) {
    for (Section section : sections) {
      if (section.getTravelClass().equals(travelClass)) {
        return section;
      }
    }
    return null;
  }

  public boolean deleteSection(Section section) {
    if (sections.contains(section)) {
      sections.remove(section);
      return true;
    }
    return false;
  }

  public boolean addSection(Section section) {
    if (section != null) {
      sections.add(section);
      return true;
    }
    return false;
  }


}