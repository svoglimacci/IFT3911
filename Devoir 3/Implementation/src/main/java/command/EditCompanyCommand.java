package command;

import repository.Repository;
import company.Company;

public class EditCompanyCommand implements ICommand {
   private String id;
  private String newId;
  private Repository repository;

  public EditCompanyCommand(String id, String newId, Repository repository) {
    this.id = id;
    this.newId = newId;
    this.repository = repository;
  }

  @Override
  public void execute() {
      repository.editCompany(id, newId);

  }
}