package repository;

import command.Invoker;
import company.Company;
import hub.Hub;
import java.util.ArrayList;
import itinerary.Itinerary;
import section.TravelClass;
import vehicle.Vehicle;


public class Memento {
     private final ArrayList<Company> companies;
    private final ArrayList<Vehicle> vehicles;
    private final ArrayList<Itinerary> itineraries;
    private final ArrayList<Hub> hubs;
    private final ArrayList<TravelClass> travelClasses;

    public Memento(ArrayList<Company> companies, ArrayList<Vehicle> vehicles, ArrayList<Itinerary> itineraries, ArrayList<Hub> hubs,  ArrayList<TravelClass> travelClasses) {
        this.companies = new ArrayList<>(companies);
        this.vehicles = new ArrayList<>(vehicles);
        this.itineraries = new ArrayList<>(itineraries);
        this.hubs = new ArrayList<>(hubs);
        this.travelClasses = new ArrayList<>(travelClasses);
    }

    public ArrayList<Company> getCompanies() {
        return new ArrayList<>(companies);
    }

    public ArrayList<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    public ArrayList<Itinerary> getItineraries() {
        return new ArrayList<>(itineraries);
    }

    public ArrayList<Hub> getHubs() {
        return new ArrayList<>(hubs);
    }

    public ArrayList<TravelClass> getTravelClasses() {
        return new ArrayList<>(travelClasses);
    }
}