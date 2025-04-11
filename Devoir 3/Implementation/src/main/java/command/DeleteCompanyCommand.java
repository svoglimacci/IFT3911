package command;

import repository.Repository;
import company.Company;

public class DeleteCompanyCommand implements ICommand {
   private String id;

  private Repository repository;
  private Company oldCompany;

  public DeleteCompanyCommand(String id, Repository repository) {
    this.id = id;
    this.repository = repository;


  }


  @Override
  public void execute() {
     this.oldCompany = repository.getCompany(id);
      repository.deleteCompany(id);
  }

    @Override
  public void undo() {
      if (oldCompany != null) {
          TravelType travelType = oldCompany.getTravelType();
          repository.addCompany(oldCompany, travelType);
      }

  }
}