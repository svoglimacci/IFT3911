package repository;


import command.TravelType;
import company.Company;
import java.util.ArrayList;
import view.IObserver;

public class Repository {
    private ArrayList<Company> companies = new ArrayList<>();
    private ArrayList<IObserver> observers = new ArrayList<>();

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


  public void editCompany(String id, String newId) {
    for (Company company : companies) {
      if (company.getId().equals(id)) {
        company.setId(newId);
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

  public Company getCompany(String id) {
    for (Company company : companies) {
      if (company.getId().equals(id)) {
        return company;
      }
    }
    return null;
  }

  public ArrayList<Company> getCompanies() {
    return companies;
  }
}