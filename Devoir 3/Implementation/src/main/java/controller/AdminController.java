package controller;

import command.CreateCompanyCommand;
import command.DeleteCompanyCommand;
import command.EditCompanyCommand;
import command.Invoker;
import command.TravelType;
import company.Company;
import factory.AirFactory;
import repository.Repository;

public class AdminController {
    private final Repository repository;
    private final Invoker invoker;

    public AdminController(Repository repository) {
        this.repository = repository;
        this.invoker = new Invoker();
    }

    public void undo() {
        invoker.undoLast();
    }


    public boolean createCompany(String id, TravelType travelType) {
        if (travelType == TravelType.AIR) {
          CreateCompanyCommand createCompanyCommand = new CreateCompanyCommand(id, travelType, repository);
            invoker.executeCommand(createCompanyCommand);
            return true;
        }
        return false;
    }


  public boolean editCompany(String id, String newId) {
      EditCompanyCommand editCompanyCommand = new EditCompanyCommand(id, newId, repository);
      invoker.executeCommand(editCompanyCommand);
      return true;
  }

  public boolean deleteCompany(String id) {
      DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(id, repository);
      invoker.executeCommand(deleteCompanyCommand);
      return true;
  }

}