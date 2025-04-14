package view;


import command.TravelType;
import itinerary.Flight;
import itinerary.Itinerary;
import java.util.ArrayList;
import java.util.Calendar;
import repository.Repository;
import controller.AdminController;
import section.Layout;
import section.TravelClass;
import visitor.AdminTripVisitor;

public class AdminView implements IObserver {
  private final AdminController controller;

  public AdminView(Repository repository, AdminController controller) {
    this.controller = controller;
    repository.addObserver(this);
  }

    @Override
    public void update() {
    }

    public void handleCreateCompany(String id, TravelType travelType) {
    boolean res = controller.createCompany(id, travelType);

    if (res) {
      System.out.println("Company created successfully");
    } else {
      System.out.println("Failed to create company");
    }
    }

    public void handleCreateHub(String id, String city, TravelType travelType) {
      boolean res = controller.createHub(id, city, travelType);

      if (res) {
        System.out.println("Hub created successfully");
      } else {
        System.out.println("Failed to create hub");
      }
    }

    public void handleEditCompany(String id, String newId) {
      boolean res = controller.editCompany(id, newId);

      if (res) {
        System.out.println("Company edited successfully");
      } else {
        System.out.println("Failed to edit company");
      }
    }

  public void handleEditHub(String id, String newId, String newCity) {
    boolean res = controller.editHub(id, newId, newCity);

    if (res) {
      System.out.println("Hub edited successfully");
    } else {
      System.out.println("Failed to edit hub");
    }
  }

  public void handleDeleteCompany(String id) {
    boolean res = controller.deleteCompany(id);

    if (res) {
      System.out.println("Company deleted successfully");
    } else {
      System.out.println("Failed to delete company");
    }
  }

  public void handleDeleteHub(String id) {
    boolean res = controller.deleteHub(id);

    if (res) {
      System.out.println("Hub deleted successfully");
    } else {
      System.out.println("Failed to delete hub");
    }
  }

  public void handleCreateItinerary(String id, String companyId, String vehicleId, String[] hubs, Calendar departureDate, Calendar arrivalDate, int price, TravelType travelType) {
    boolean res = controller.createItinerary(id, companyId, vehicleId, hubs, departureDate, arrivalDate, price, travelType);

    if (res) {
      System.out.println("Itinerary created successfully");
    } else {
      System.out.println("Failed to create itinerary");
    }
  }

  public void handleEditItinerary(String id, String newId, String newCompany, String[] newHubs, Calendar newDepartureDate, Calendar newArrivalDate, int newPrice) {
    boolean res = controller.editItinerary(id, newId, newCompany, newHubs, newDepartureDate, newArrivalDate, newPrice);

    if (res) {
      System.out.println("Itinerary edited successfully");
    } else {
      System.out.println("Failed to edit itinerary");
    }
  }

  public void handleDeleteItinerary(String id) {
    boolean res = controller.deleteItinerary(id);

    if (res) {
      System.out.println("Itinerary deleted successfully");
    } else {
      System.out.println("Failed to delete itinerary");
    }
  }

  public void handleCreateSection(String vehicleId, String travelClass, Layout layout, int nbSeats, TravelType travelType) {
    boolean res = controller.createSection(vehicleId, travelClass, layout, nbSeats, travelType );

    if (res) {
      System.out.println("Section created successfully");
    } else {
      System.out.println("Failed to create section");
    }
  }

  public void handleDisplayItineraries(TravelType travelType) {

    ArrayList<Itinerary> itineraries =  controller.displayItineraries(travelType);
    AdminTripVisitor adminTripVisitor = new AdminTripVisitor();
    for (Itinerary itinerary : itineraries) {
      itinerary.accept(adminTripVisitor);
    }
  }

  public void handleAssignPrices(String itineraryId, int price) {

    boolean res = controller.assignPrices(itineraryId, price);

    if (res) {
      System.out.println("Price assigned successfully");
    } else {
      System.out.println("Failed to assign price");
    }
  }

  public void handleUndo() {
        if (controller.undo()) {
            System.out.println("Undo successful.");
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    public void backup() {
        controller.save();
    }

    public void restore() {
        controller.restore();
    }

}