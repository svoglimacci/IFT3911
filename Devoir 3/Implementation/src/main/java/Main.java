import command.TravelType;
import company.Company;
import controller.AdminController;
import controller.ClientController;
import factory.AirFactory;
import factory.TravelFactory;
import java.util.ArrayList;
import java.util.Calendar;
import itinerary.Flight;
import repository.Repository;
import view.AdminView;
import view.ClientView;

import java.util.Scanner;
import visitor.AdminTripVisitor;
import visitor.ClientTripVisitor;

public class Main {

  public static void main(String[] args) {
    Repository repository = new Repository();


    ClientController clientController = new ClientController(repository);
    ClientView clientView = new ClientView(repository);

    AdminController adminController = new AdminController(repository);
    AdminView adminView = new AdminView(repository, adminController);



    // ADMIN

    // Create Flights, Airline, Airports, and Sections
    adminView.handleCreateCompany("Airline1", TravelType.AIR);

    // Assign Prices

    // Consult Flights


    // Edit Flights, Airline, Airports, and Sections
    adminView.handleEditCompany("Airline1", "Airline2");


    // Delete Flights, Airline, Airports, and Sections
    adminView.handleDeleteCompany("Airline2");


    // CLIENT
    // Consult Flights
    // Reserve Seat
    // Pay Seat


  }
}