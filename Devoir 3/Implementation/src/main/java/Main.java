import command.TravelType;
import company.Airline;
import company.Company;
import controller.AdminController;
import controller.ClientController;
import factory.AirFactory;
import factory.TravelFactory;
import hub.Airport;
import hub.Hub;
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
    repository.addVehicle(new Airplane("AC481"));
    repository.addVehicle(new Airplane("AC482"));
    repository.addTravelClass("F", 1.0, "Première");
    repository.addTravelClass("A", 0.75, "Affaire");
    repository.addTravelClass("E", 0.5, "Économique");
    repository.addTravelClass("P", 0.6, "Économique Premium");



    ClientController clientController = new ClientController(repository);
    ClientView clientView = new ClientView(repository, clientController);
    Client client = new Client("user", "password");

    AdminController adminController = new AdminController(repository);
    AdminView adminView = new AdminView(repository, adminController);
    initializeData(adminView);
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    while (running) {
      System.out.println("\nEnter command (admin/client/exit): ");
      String command = scanner.nextLine().toLowerCase();

      switch (command) {
        case "admin" -> handleAdminCommands(adminView, scanner);
        case "client" -> handleClientCommands(clientView, client, scanner);
        case "exit" -> running = false;
        default -> System.out.println("Invalid command. Please enter 'admin', 'client', or 'exit'.");
      }
    }
  }

  private static void initializeData(AdminView view) {

    view.handleCreateHub("YUL", "Montreal", TravelType.AIR);
    view.handleCreateHub("YYZ", "Toronto", TravelType.AIR);
    view.handleCreateHub("YOW", "Ottawa", TravelType.AIR);
    view.handleCreateCompany("AIRCAN", TravelType.AIR);
    view.handleCreateCompany("DELTA", TravelType.AIR);

    view.handleCreateItinerary("YUL-YYZ", "AIRCAN", "AC481", new String[]{"YUL", "YYZ"},
        Calendar.getInstance(), Calendar.getInstance(), 474, TravelType.AIR);
    view.handleCreateItinerary("YYZ-YUL", "AIRCAN", "AC481", new String[]{"YYZ", "YUL"},
        Calendar.getInstance(), Calendar.getInstance(), 474, TravelType.AIR);
    view.handleCreateItinerary("YUL-YOW", "DELTA", "AC482", new String[]{"YUL", "YOW"},
        Calendar.getInstance(), Calendar.getInstance(), 474, TravelType.AIR);

    view.handleCreateSection("AC481", "F", Layout.NARROW, 12, TravelType.AIR);
    view.handleCreateSection("AC481", "A", Layout.COMFORT, 16, TravelType.AIR);
    view.handleCreateSection("AC481", "E", Layout.MEDIUM, 200, TravelType.AIR);
    view.handleCreateSection("AC482", "F", Layout.NARROW, 12, TravelType.AIR);
    view.handleCreateSection("AC482", "A", Layout.COMFORT, 16, TravelType.AIR);
    view.handleCreateSection("AC482", "E", Layout.LARGE, 200, TravelType.AIR);

  }

  private static void handleAdminCommands(AdminView adminView, Scanner scanner) {
    boolean adminRunning = true;
    while (adminRunning) {
      System.out.println("\nAdmin commands:");
      System.out.println("1. Create Company");
      System.out.println("2. Create Hub");
      System.out.println("3. Create Itinerary");
      System.out.println("4. Create Section");
      System.out.println("5. Assign Prices");
      System.out.println("6. Edit Company");
      System.out.println("7. Edit Hub");
      System.out.println("8. Edit Itinerary");
      System.out.println("9. Delete Company");
      System.out.println("10. Delete Hub");
      System.out.println("11. Delete Itinerary");
      System.out.println("12. Display Itineraries");
      System.out.println("13. Back to main menu");

      System.out.print("\nEnter command number: ");
      String command = scanner.nextLine();

      switch (command) {
        case "1" -> {
          System.out.print("Enter company ID: ");
          String companyId = scanner.nextLine();
          adminView.handleCreateCompany(companyId, TravelType.AIR);
        }
        case "2" -> {
          System.out.print("Enter hub ID: ");
          String hubId = scanner.nextLine();
          System.out.print("Enter city: ");
          String city = scanner.nextLine();
          adminView.handleCreateHub(hubId, city, TravelType.AIR);
        }
        case "3" -> {
          System.out.print("Enter itinerary ID: ");
          String itineraryId = scanner.nextLine();
          System.out.print("Enter company ID: ");
          String compId = scanner.nextLine();
          System.out.print("Enter vehicle ID: ");
          String vehicleId = scanner.nextLine();
          System.out.print("Enter hubs (comma-separated): ");
          String[] hubs = scanner.nextLine().split(",");
          System.out.print("Enter price: ");
          int price = Integer.parseInt(scanner.nextLine());
          adminView.handleCreateItinerary(itineraryId, compId, vehicleId, hubs, 
              Calendar.getInstance(), Calendar.getInstance(), price, TravelType.AIR);
        }
        case "4" -> {
          System.out.print("Enter vehicle ID: ");
          String vehId = scanner.nextLine();
          System.out.print("Enter travel class (F/A/E/P): ");
          String travelClass = scanner.nextLine();
          System.out.print("Enter layout (NARROW/COMFORT/MEDIUM/LARGE): ");
          Layout layout = Layout.valueOf(scanner.nextLine());
          System.out.print("Enter number of seats: ");
          int seats = Integer.parseInt(scanner.nextLine());
          adminView.handleCreateSection(vehId, travelClass, layout, seats, TravelType.AIR);
        }
        case "5" -> {
          System.out.print("Enter itinerary ID: ");
          String itinId = scanner.nextLine();
          System.out.print("Enter price: ");
          int prc = Integer.parseInt(scanner.nextLine());
          adminView.handleAssignPrices(itinId, prc, TravelType.AIR);
        }
        case "6" -> {
          System.out.print("Enter company ID to edit: ");
          String oldCompId = scanner.nextLine();
          System.out.print("Enter new company ID: ");
          String newCompId = scanner.nextLine();
          adminView.handleEditCompany(oldCompId, newCompId);
        }
        case "7" -> {
          System.out.print("Enter hub ID to edit: ");
          String oldHubId = scanner.nextLine();
          System.out.print("Enter new hub ID: ");
          String newHubId = scanner.nextLine();
          System.out.print("Enter new city: ");
          String newCity = scanner.nextLine();
          adminView.handleEditHub(oldHubId, newHubId, newCity);
        }
        case "8" -> {
          System.out.print("Enter itinerary ID to edit: ");
          String oldItinId = scanner.nextLine();
          System.out.print("Enter new itinerary ID: ");
          String newItinId = scanner.nextLine();
          System.out.print("Enter new company ID: ");
          String newComp = scanner.nextLine();
          System.out.print("Enter new hubs (comma-separated): ");
          String[] newHubs = scanner.nextLine().split(",");
          System.out.print("Enter new price: ");
          int newPrice = Integer.parseInt(scanner.nextLine());
          adminView.handleEditItinerary(oldItinId, newItinId, newComp, newHubs, 
              Calendar.getInstance(), Calendar.getInstance(), newPrice);
        }
        case "9" -> {
          System.out.print("Enter company ID to delete: ");
          String delCompId = scanner.nextLine();
          adminView.handleDeleteCompany(delCompId);
        }
        case "10" -> {
          System.out.print("Enter hub ID to delete: ");
          String delHubId = scanner.nextLine();
          adminView.handleDeleteHub(delHubId);
        }
        case "11" -> {
          System.out.print("Enter itinerary ID to delete: ");
          String delItinId = scanner.nextLine();
          adminView.handleDeleteItinerary(delItinId);
        }
        case "12" -> adminView.handleDisplayItineraries(TravelType.AIR);
        case "13" -> adminRunning = false;
        default -> System.out.println("Invalid command number.");
      }
    }
  }

  private static void handleClientCommands(ClientView clientView, Client client, Scanner scanner) {
    boolean clientRunning = true;
    while (clientRunning) {
      System.out.println("\nClient commands:");
      System.out.println("1. Display Itineraries");
      System.out.println("2. Reserve Seat");
      System.out.println("3. Pay Seat");
      System.out.println("4. Back to main menu");

      System.out.print("\nEnter command number: ");
      String command = scanner.nextLine();

      switch (command) {
        case "1" -> clientView.handleDisplayItineraries(TravelType.AIR);
        case "2" -> {
          System.out.print("Enter itinerary ID: ");
          String itineraryId = scanner.nextLine();
          System.out.print("Enter travel class (F/A/E/P): ");
          String travelClass = scanner.nextLine();
          System.out.print("Window seat? (true/false): ");
          boolean isWindowSeat = Boolean.parseBoolean(scanner.nextLine());
          clientView.handleReserveSeat(client, itineraryId, travelClass, TravelType.AIR, isWindowSeat);
        }
        case "3" -> {
          System.out.print("Enter reservation number: ");
          int reservationNumber = Integer.parseInt(scanner.nextLine());
          System.out.print("Enter name: ");
          String name = scanner.nextLine();
          System.out.print("Enter email: ");
          String email = scanner.nextLine();
          System.out.print("Enter passport: ");
          String passport = scanner.nextLine();
          System.out.print("Enter credit card number: ");
          String ccNumber = scanner.nextLine();
          clientView.handlePaySeat(client, reservationNumber, name, email, passport, ccNumber);
        }
        case "4" -> clientRunning = false;
        default -> System.out.println("Invalid command number.");
      }
    }
  }
}