import command.TravelType;
import company.Airline;
import company.Company;
import controller.AdminController;
import controller.ClientController;
import factory.AirFactory;
import factory.TravelFactory;
import hub.Airport;
import hub.Hub;
import itinerary.Cruise;
import java.util.ArrayList;
import java.util.Calendar;
import itinerary.Flight;
import repository.Repository;
import section.Layout;
import section.TravelClass;
import user.Client;
import vehicle.Airplane;
import vehicle.CruiseShip;
import vehicle.Train;
import view.AdminView;
import view.ClientView;

import java.util.Scanner;
import visitor.AdminTripVisitor;
import visitor.ClientTripVisitor;

public class Main {

  public static void main(String[] args) {
    Repository repository = new Repository();




    ClientController clientController = new ClientController(repository);
    ClientView clientView = new ClientView(repository, clientController);
    Client client = new Client("user", "password");

    AdminController adminController = new AdminController(repository);
    AdminView adminView = new AdminView(repository, adminController);
    initializeData(adminView, repository);
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    // print itineraries

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

  private static void initializeData(AdminView view, Repository repository) {

    repository.addVehicle(new Airplane("AC481"));
    repository.addVehicle(new Airplane("AC482"));
    repository.addVehicle(new Train("TC123"));
    repository.addVehicle(new CruiseShip("CR123"));
    repository.addTravelClass("F", 1.0, "Première");
    repository.addTravelClass("A", 0.75, "Affaire");
    repository.addTravelClass("E", 0.5, "Économique");
    repository.addTravelClass("P", 0.6, "Économique Premium");
    repository.addTravelClass("I", 0.5, "Intérieur");
    repository.addTravelClass("O", 0.75, "Vue sur l'Océan");
    repository.addTravelClass("S", 0.9, "Suite");
    repository.addTravelClass("F", 0.9, "Famille");
    repository.addTravelClass("D", 1.0, "Famille Deluxe");

    view.handleCreateHub("YUL", "Montreal", TravelType.AIR);
    view.handleCreateHub("YYZ", "Toronto", TravelType.AIR);
    view.handleCreateHub("YOW", "Ottawa", TravelType.AIR);
    view.handleCreateHub("ABC", "Montreal", TravelType.SEA);
    view.handleCreateHub("DEF", "Montreal", TravelType.SEA);
    view.handleCreateHub("GHI", "Montreal", TravelType.GROUND);
    view.handleCreateHub("JKL", "Montreal", TravelType.GROUND);

    view.handleCreateCompany("AIRCAN", TravelType.AIR);
    view.handleCreateCompany("DELTA", TravelType.AIR);
    view.handleCreateCompany("CARNIVAL", TravelType.SEA);
    view.handleCreateCompany("COSTA", TravelType.SEA);
    view.handleCreateCompany("VIA", TravelType.GROUND);
    view.handleCreateCompany("AMTRAK", TravelType.GROUND);

    view.handleCreateItinerary("YUL-YYZ", "AIRCAN", "AC481", new String[]{"YUL", "YYZ"},
        Calendar.getInstance(), Calendar.getInstance(), 474, TravelType.AIR);
    view.handleCreateItinerary("YYZ-YUL", "AIRCAN", "AC481", new String[]{"YYZ", "YUL"},
        Calendar.getInstance(), Calendar.getInstance(), 474, TravelType.AIR);
    view.handleCreateItinerary("YUL-YOW", "DELTA", "AC482", new String[]{"YUL", "YOW"},
        Calendar.getInstance(), Calendar.getInstance(), 474, TravelType.AIR);

    view.handleCreateItinerary("ABC-DEF", "CARNIVAL", "CR123", new String[]{"ABC", "DEF"},
        Calendar.getInstance(), Calendar.getInstance(), 474, TravelType.SEA);
    view.handleCreateItinerary("DEF-ABC", "CARNIVAL", "CR123", new String[]{"DEF", "ABC"},
        Calendar.getInstance(), Calendar.getInstance(), 474, TravelType.SEA);
    view.handleCreateItinerary("ABC-DEF", "COSTA", "CR123", new String[]{"ABC", "DEF"},
        Calendar.getInstance(), Calendar.getInstance(), 474, TravelType.SEA);

    view.handleCreateItinerary("GHI-JKL", "VIA", "TC123", new String[]{"GHI", "JKL"},
        Calendar.getInstance(), Calendar.getInstance(), 474, TravelType.GROUND);
    view.handleCreateItinerary("JKL-GHI", "VIA", "TC123", new String[]{"JKL", "GHI"},
        Calendar.getInstance(), Calendar.getInstance(), 474, TravelType.GROUND);
    view.handleCreateItinerary("GHI-JKL", "AMTRAK", "TC123", new String[]{"GHI", "JKL"},
        Calendar.getInstance(), Calendar.getInstance(), 474, TravelType.GROUND);

    view.handleCreateSection("AC481", "F", Layout.NARROW, 12, TravelType.AIR);
    view.handleCreateSection("AC481", "A", Layout.COMFORT, 16, TravelType.AIR);
    view.handleCreateSection("AC481", "E", Layout.MEDIUM, 200, TravelType.AIR);
    view.handleCreateSection("AC482", "F", Layout.NARROW, 12, TravelType.AIR);
    view.handleCreateSection("AC482", "A", Layout.COMFORT, 16, TravelType.AIR);
    view.handleCreateSection("AC482", "E", Layout.LARGE, 200, TravelType.AIR);

    view.handleCreateSection("CR123", "I", null , 12, TravelType.SEA);
    view.handleCreateSection("CR123", "O", null, 16, TravelType.SEA);
    view.handleCreateSection("CR123", "S", null, 200, TravelType.SEA);

    view.handleCreateSection("TC123", "F", Layout.NARROW, 12, TravelType.GROUND);
    view.handleCreateSection("TC123", "A", Layout.NARROW, 16, TravelType.GROUND);
    view.handleCreateSection("TC123", "E", Layout.NARROW, 200, TravelType.GROUND);



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
      System.out.println("13. undo last command");
      System.out.println("14. restore last state");
      System.out.println("15. backup current state");
      System.out.println("16. Back to main menu");

      System.out.print("\nEnter command number: ");
      String command = scanner.nextLine();

      switch (command) {
        case "1" -> {
          System.out.println("Enter travel type (AIR/SEA/GROUND): ");
          String travelType = scanner.nextLine();
          System.out.print("Enter company ID: ");
          String companyId = scanner.nextLine();
          adminView.handleCreateCompany(companyId, TravelType.valueOf(travelType));
        }
        case "2" -> {
          System.out.println("Enter travel type (AIR/SEA/GROUND): ");
          String travelType = scanner.nextLine();
          System.out.print("Enter hub ID: ");
          String hubId = scanner.nextLine();
          System.out.print("Enter city: ");
          String city = scanner.nextLine();
          adminView.handleCreateHub(hubId, city, TravelType.valueOf(travelType));
        }
        case "3" -> {
          System.out.println("Enter travel type (AIR/SEA/GROUND): ");
          String travelType = scanner.nextLine();
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
              Calendar.getInstance(), Calendar.getInstance(), price, TravelType.valueOf(travelType));
        }
        case "4" -> {
          System.out.println("Enter travel type (AIR/SEA/GROUND): ");
          String travelType = scanner.nextLine();
          System.out.print("Enter vehicle ID: ");
          String vehId = scanner.nextLine();
          System.out.print("Enter travel class (F/A/E/P/I/O/S/F/D): ");
          String travelClass = scanner.nextLine();
          System.out.print("Enter layout (NARROW/COMFORT/MEDIUM/LARGE): ");
          Layout layout = Layout.valueOf(scanner.nextLine());
          System.out.print("Enter number of seats: ");
          int seats = Integer.parseInt(scanner.nextLine());
          adminView.handleCreateSection(vehId, travelClass, layout, seats, TravelType.valueOf(travelType));
        }
        case "5" -> {
          System.out.print("Enter itinerary ID: ");
          String itinId = scanner.nextLine();
          System.out.print("Enter price: ");
          int prc = Integer.parseInt(scanner.nextLine());
          adminView.handleAssignPrices(itinId, prc);
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
        case "12" -> {
          System.out.println("Enter travel type (AIR/SEA/GROUND): ");
          String travelType = scanner.nextLine();
          adminView.handleDisplayItineraries(TravelType.valueOf(travelType));
        }
        case "13" -> adminView.handleUndo();
        case "14" -> adminView.restore();
        case "15" -> adminView.backup();
        case "16" -> adminRunning = false;
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
        case "1" -> {
          System.out.println("Enter travel type (AIR/SEA/GROUND): ");
          String travelType = scanner.nextLine();
          clientView.handleDisplayItineraries(TravelType.valueOf(travelType));
        }
        case "2" -> {
                    System.out.println("Enter travel type (AIR/SEA/GROUND): ");
          String travelType = scanner.nextLine();
          System.out.print("Enter itinerary ID: ");
          String itineraryId = scanner.nextLine();
          System.out.print("Enter travel class (F/A/E/P/I/O/S/F/D): ");
          String travelClass = scanner.nextLine();
          System.out.print("Window seat? (true/false): ");
          boolean isWindowSeat = Boolean.parseBoolean(scanner.nextLine());
          clientView.handleReserveSeat(client, itineraryId, travelClass,TravelType.valueOf(travelType), isWindowSeat);
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
          clientView.handlePaySeat(client, reservationNumber, name, email, passport);
        }
        case "4" -> clientRunning = false;
        default -> System.out.println("Invalid command number.");
      }
    }
  }
}