package repository;


import command.TravelType;
import company.Company;
import hub.Hub;
import itinerary.Flight;
import itinerary.Itinerary;
import java.util.ArrayList;
import java.util.Calendar;
import section.Layout;
import section.PlaneSection;
import section.Section;
import section.TravelClass;
import vehicle.Airplane;
import vehicle.Vehicle;
import view.IObserver;

public class Repository {

  private ArrayList<Company> companies = new ArrayList<>();
  private ArrayList<Vehicle> vehicles = new ArrayList<>();
  private ArrayList<Itinerary> itineraries = new ArrayList<>();
  private ArrayList<Hub> hubs = new ArrayList<>();
  private ArrayList<IObserver> observers = new ArrayList<>();

  private ArrayList<TravelClass> travelClasses = new ArrayList<>();

  public void addObserver(IObserver observer) {
    observers.add(observer);
  }

  public void removeObserver(IObserver observer) {
    observers.remove(observer);
  }

  private void notifyObservers() {
    for (IObserver obs : observers) {
      obs.update();
    }
  }

  public void addCompany(Company company, TravelType travelType) {
    if (travelType == TravelType.AIR) {
      companies.add(company);
      notifyObservers();
    }
  }

  public void addHub(Hub hub, TravelType travelType) {
    if (travelType == TravelType.AIR) {
      hubs.add(hub);
      notifyObservers();
    }
  }

  public void editCompany(String id, String newId) {
    for (Company company : companies) {
      if (company.getId().equals(id)) {
        company.setId(newId);
        notifyObservers();
        return;
      }
    }
  }

  public void editHub(String id, String newId, String newCity) {
    for (Hub hub : hubs) {
      if (hub.getId().equals(id)) {
        hub.setId(newId);
        hub.setCity(newCity);
        notifyObservers();
        return;
      }
    }
  }

  public void deleteCompany(String id) {
    for (int i = 0; i < companies.size(); i++) {
      if (companies.get(i).getId().equals(id)) {
        companies.remove(i);
        notifyObservers();
        return;
      }
    }
  }

  public void deleteHub(String id) {
    for (int i = 0; i < hubs.size(); i++) {
      if (hubs.get(i).getId().equals(id)) {
        hubs.remove(i);
        notifyObservers();
        return;
      }
    }
  }


  public Hub getHub(String id, TravelType travelType) {
    if (travelType == TravelType.AIR) {
      for (Hub hub : hubs) {
        if (hub.getId().equals(id)) {
          return hub;
        }
      }
    }
    return null;
  }

  public Company getCompany(String companyId) {
      for (Company company : companies) {
        if (company.getId().equals(companyId)) {
          return company;
        }
      }
    return null;
  }

  public void addItinerary(Flight flight, TravelType travelType) {
    if (travelType == TravelType.AIR) {
      itineraries.add(flight);
      notifyObservers();
    }
  }

  public void updateItinerary(String id, String newId, String newCompanyId, String[] newHubsId, Calendar newDepartureDate, Calendar newArrivalDate, int newPrice) {

    Company newCompany = getCompany(newCompanyId);

    ArrayList<Hub> newHubs = new ArrayList<>();

    for (String hubId : newHubsId) {
      Hub hub = getHub(hubId, TravelType.AIR);
      if (hub != null) {
        newHubs.add(hub);
      }
    }


    for (Itinerary itinerary : itineraries) {
      if (itinerary.getId().equals(id)) {
        itinerary.setId(newId);
        itinerary.setCompany(newCompany);
        itinerary.setHubs(newHubs);
        itinerary.setDepartureDate(newDepartureDate);
        itinerary.setArrivalDate(newArrivalDate);
        itinerary.setPrice(newPrice);
        notifyObservers();
        return;
      }
    }
  }

  public void deleteItinerary(String id) {
    for (int i = 0; i < itineraries.size(); i++) {
      if (itineraries.get(i).getId().equals(id)) {
        itineraries.remove(i);
        notifyObservers();
        return;
      }
    }
  }

  public void addVehicle(Airplane airplane, TravelType travelType) {
    if (travelType == TravelType.AIR) {
      vehicles.add(airplane);
      notifyObservers();
    }
  }

  public void createSection(String vehicleId, String travelClassId, Layout layout, int nbSeats, TravelType travelType) {
    TravelClass travelClass = getTravelClass(travelClassId);

    for (Vehicle vehicle : vehicles) {
      if (vehicle.getId().equals(vehicleId)) {
        switch (travelType) {
          case AIR:
            Section section = new PlaneSection(travelClass, layout, nbSeats);
            vehicle.addSection(section);
            break;
          default:
            break;
        }
        notifyObservers();
        return;
      }
    }
  }

  private TravelClass getTravelClass(String travelClassId) {
    for (TravelClass travelClass : travelClasses) {
      if (travelClass.getId().equals(travelClassId)) {
        return travelClass;
      }
    }
    return null;
  }

  public void addTravelClass(String id, double rate, String name) {
    travelClasses.add(new TravelClass(id, name, rate));
  }

  public ArrayList<Itinerary> getItineraries(TravelType travelType) {
    ArrayList<Itinerary> result = new ArrayList<>();
    for (Itinerary itinerary : itineraries) {
      if (travelType == TravelType.AIR && itinerary instanceof Flight) {
        result.add(itinerary);
      }
    }
    return result;
  }

  public Vehicle getVehicle(String vehicleId) {
    for (Vehicle vehicle : vehicles) {
      if (vehicle.getId().equals(vehicleId)) {
        return vehicle;
      }
    }
    return null;
  }
}