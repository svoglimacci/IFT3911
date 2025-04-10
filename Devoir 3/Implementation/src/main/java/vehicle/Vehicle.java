package vehicle;

import company.Company;
import itinerary.Itinerary;
import java.util.ArrayList;
import section.PlaneSection;
import section.Section;
import section.TravelClass;

public abstract class Vehicle {

  private String id;
  private ArrayList<Itinerary> itineraries;
  private ArrayList<Section> sections;
  private Company company;

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

  public ArrayList<Itinerary> getItineraries() {
    return itineraries;
  }


  public Section getSection(String travelClass) {
    for (Section section : sections) {
      if (section.getTravelClass().getId().equals(travelClass)) {
        return section;
      }
    }
    return null;
  }

  public Company getCompany() {
    return company;
  }

  public void addSection(Section section) {
    sections.add(section);
  }

  public ArrayList<Section> getSections() {
    return sections;
  }
}