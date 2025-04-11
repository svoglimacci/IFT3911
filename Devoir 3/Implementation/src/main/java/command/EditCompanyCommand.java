package command;

import repository.Repository;
import company.Company;

public class EditCompanyCommand implements ICommand {
   private String id;
  private String newId;
  private Repository repository;

  private String oldId;

  public EditCompanyCommand(String id, String newId, Repository repository) {
    this.id = id;
    this.newId = newId;
    this.repository = repository;
  }

  @Override
  public void execute() {
    this.oldId = this.id;
      repository.editCompany(id, newId);

  }

  @Override
  public void undo() {
       if (oldId != null) {
           repository.editCompany(newId, oldId);
       }

  }
}