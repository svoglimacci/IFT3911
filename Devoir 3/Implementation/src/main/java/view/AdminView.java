package view;


import command.TravelType;
import repository.Repository;
import controller.AdminController;

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

    public void handleEditCompany(String id, String newId) {
      boolean res = controller.editCompany(id, newId);

      if (res) {
        System.out.println("Company edited successfully");
      } else {
        System.out.println("Failed to edit company");
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
}