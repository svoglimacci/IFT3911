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
import section.Layout;
import section.TravelClass;
import user.Client;
import vehicle.Airplane;
import view.AdminView;
import view.ClientView;

import java.util.Scanner;
import visitor.AdminTripVisitor;
import visitor.ClientTripVisitor;

public class Main {

  public static void main(String[] args) {
    Repository repository = new Repository();

    repository.addCompany(new Company("AIRCAN"), TravelType.AIR);
    repository.addVehicle(new Airplane("AC481"), TravelType.AIR);
    repository.addTravelClass("F", 1.0, "Première");
    repository.addTravelClass("A", 0.75, "Affaire");
    repository.addTravelClass("E", 0.5, "Économique");
    repository.addTravelClass("P", 0.6, "Économique Premium");


    ClientController clientController = new ClientController(repository);
    ClientView clientView = new ClientView(repository, clientController);
    Client client = new Client("user", "password");

    AdminController adminController = new AdminController(repository);
    AdminView adminView = new AdminView(repository, adminController);



    // ADMIN

    // Create Flights, Airline, Airports, and Sections
    adminView.handleCreateCompany("Airline1", TravelType.AIR);
    adminView.handleCreateHub("YUL", "Montreal", TravelType.AIR);
    adminView.handleCreateHub("YYZ", "Toronto", TravelType.AIR);
    adminView.handleCreateItinerary("1","AIRCAN", "AC481", new String[]{"YUL", "YYZ"}, Calendar.getInstance(), Calendar.getInstance(), 474, TravelType.AIR);
    adminView.handleCreateSection("AC481", "F", Layout.NARROW, 3, TravelType.AIR);


    // Assign Prices
    //  Tous les sièges d'une même section ont le même prix [OCL]. Le prix varie d'une compagnie à l'autre.
    //  Le prix pour F est 100% du plein tarif, A est 75% du plein tarif, P est 60% du plein tarif et E est 50% du plein tarif. Il n'y a pas de taxe.
    adminView.handleAssignPrices("1", 272, TravelType.AIR);

    // Consult Flights with AdminTripVisitor
    adminView.handleDisplayItineraries(TravelType.AIR);

    // Edit Flights, Airline, Airports
    //adminView.handleEditCompany("Airline1", "Airline2");
    //adminView.handleEditHub("YUL", "YYZ", "Toronto");
    //adminView.handleEditItinerary("1", "AC482", "Airline2", new String[]{"YYZ", "YUL"}, Calendar.getInstance(), Calendar.getInstance(), 200);


    // Delete Flights, Airline, Airports
    //adminView.handleDeleteCompany("Airline2");
    //adminView.handleDeleteHub("YYZ");
    //adminView.handleDeleteItinerary("AC482");


    // CLIENT
    // Consult Flights
    clientView.handleDisplayItineraries(TravelType.AIR);

    // Reserve Seat
    // (itineraryId, row, column, travelClass)
    clientView.handleReserveSeat(client, "1", "F",  TravelType.AIR, true);
    clientView.handleReserveSeat(client, "1", "F",  TravelType.AIR, true);

    // this should fail
    clientView.handleReserveSeat(client, "1", "F",  TravelType.AIR, true);

    clientView.handleDisplayItineraries(TravelType.AIR);


    // Pay Seat
    clientView.handlePaySeat(client, client.getReservations().get(0).getReservationNumber(), "John Doe", "email@example.com", "123456789", "1234123412341234");


  }
}