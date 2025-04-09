package command;

import repository.Repository;

public class EditHubCommand  implements ICommand {

  private String id;
  private String newCity;
  private String newId;
  private Repository repository;

  public EditHubCommand(String id, String newId, String newCity, Repository repository) {
    this.id = id;
    this.newId = newId;
    this.newCity = newCity;
    this.repository = repository;
  }

  @Override
  public void execute() {
      repository.editHub(id, newId, newCity);

  }

}