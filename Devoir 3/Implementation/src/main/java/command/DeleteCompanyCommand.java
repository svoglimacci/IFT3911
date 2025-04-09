package command;

import repository.Repository;
import company.Company;

public class DeleteCompanyCommand implements ICommand {
   private String id;

  private Repository repository;

  public DeleteCompanyCommand(String id, Repository repository) {
    this.id = id;
    this.repository = repository;
  }

  @Override
  public void execute() {
      repository.deleteCompany(id);

  }
}