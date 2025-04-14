package repository;


import command.TravelType;
import company.Company;
import hub.Hub;
import itinerary.Cruise;
import itinerary.Flight;
import itinerary.Itinerary;
import itinerary.TrainRide;
import java.util.ArrayList;
import java.util.Calendar;
import reservation.CreditCard;
import reservation.PayByCreditCard;
import reservation.PayStrategy;
import reservation.Payment;
import reservation.Reservation;
import reservation.Transaction;
import seating.Cabin;
import seating.PlaneSeat;
import seating.Seating;
import seating.TrainSeat;
import section.Layout;
import section.PlaneSection;
import section.Section;
import section.TravelClass;
import user.Client;
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

  public Memento saveState() {
    return new Memento(companies, vehicles, itineraries, hubs, travelClasses);
  }

  public void restoreState(Memento memento) {
    this.companies = memento.getCompanies();
    this.vehicles = memento.getVehicles();
    this.itineraries = memento.getItineraries();
    this.hubs = memento.getHubs();
    this.travelClasses = memento.getTravelClasses();
    notifyObservers();
  }

  public void addCompany(Company company, TravelType travelType) {

      companies.add(company);
      notifyObservers();

  }

  public void addHub(Hub hub, TravelType travelType) {

      hubs.add(hub);
      notifyObservers();

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


  public Hub getHub(String id) {
      for (Hub hub : hubs) {
        if (hub.getId().equals(id)) {
          return hub;
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

  public void addItinerary(Itinerary itinerary) {
      itineraries.add(itinerary);

      notifyObservers();

  }

  public void updateItinerary(String id, String newId, String newCompanyId, String[] newHubsId,
      Calendar newDepartureDate, Calendar newArrivalDate, int newPrice) {

    Company newCompany = getCompany(newCompanyId);

    ArrayList<Hub> newHubs = new ArrayList<>();

    for (String hubId : newHubsId) {
      Hub hub = getHub(hubId);
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

  public void addVehicle(Vehicle vehicle) {
      vehicles.add(vehicle);
      notifyObservers();

  }

  public void createSection(String vehicleId, Section section) {
    for (Vehicle vehicle : vehicles) {
      if (vehicle.getId().equals(vehicleId)) {
        vehicle.addSection(section);
        notifyObservers();
      }
    }
  }

  public TravelClass getTravelClass(String travelClassId) {
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
      if (travelType == TravelType.GROUND && itinerary instanceof TrainRide) {
        result.add(itinerary);
      }

      if (travelType == TravelType.SEA && itinerary instanceof Cruise) {
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

  public void assignPrice(String id, int price) {
    for (Itinerary itinerary : itineraries) {
      if (itinerary.getId().equals(id)) {
        itinerary.setPrice(price);
        notifyObservers();
        return;
      }
    }
  }

  public boolean reserveSeat(Client client, String itineraryId, String travelClass, TravelType travelType, boolean isWindowSeat) {
        for (Itinerary itinerary : itineraries) {
          if (itinerary.getId().equals(itineraryId)) {
            Vehicle vehicle = itinerary.getVehicle();
            if (vehicle == null) {
              System.out.println("Vehicle not found for the itinerary.");
              return false;
            }

            Section section = vehicle.getSection(travelClass);
            if (section == null) {
              System.out.println("Section not found for the travel class.");
              return false;
            }
            ArrayList<Seating> availableSeats = section.getAvailableSeats();
            for (Seating seating : availableSeats) {
             if ((seating instanceof PlaneSeat && ((PlaneSeat) seating).isWindowSeat() == isWindowSeat) ||
                    (seating instanceof TrainSeat && ((TrainSeat) seating).isWindowSeat() == isWindowSeat)) {
                    seating.reserve();
                client.makeReservation(seating, itinerary, client);
                notifyObservers();

                return true;
              }

             else if (seating instanceof Cabin) {
               seating.reserve();
                client.makeReservation(seating, itinerary, client);
                notifyObservers();
                return true;
             }
            }
            break;
          }
        }
        return false;
    };

  public boolean paySeat(Client client, int reservationNumber, String name, String email, String passport) {
    Reservation reservation = client.getReservation(reservationNumber);

    Payment payment = new Payment();
    PayStrategy strategy = new PayByCreditCard();

    int price = reservation.getItinerary().getPrice();

    payment.process(strategy);


    if (strategy.pay(price)) {
      reservation.setPayment(payment);

      Seating seating = reservation.getSeating();
      seating.assignSeat(client);
      System.out.println("Payment successful for reservation number: " + reservation.getReservationNumber() + " with seating: " + seating.getId() + " for client: " + client.getUsername() + "."
          + "\nPayment details: \nName: " + name + "\nEmail: " + email + "\nPassport: " + passport);

      notifyObservers();
      return true;
    } else {
      System.out.println("Payment failed");
      return false;
    }
  }

  public Itinerary getItinerary(String id) {
    for (Itinerary itinerary : itineraries) {
      if (itinerary.getId().equals(id)) {
        return itinerary;
      }
    }
    return null;
  }

  public void deleteSection(String vehicleId, String travelClass, TravelType travelType) {
    for (Vehicle vehicle : vehicles) {
      if (vehicle.getId().equals(vehicleId)) {
        Section section = vehicle.getSection(travelClass);
        if (section != null) {
          vehicle.removeSection(section);
          notifyObservers();
          return;
        }
      }
    }
  }

}