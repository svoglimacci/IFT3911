package command;

import repository.Repository;

public class EditHubCommand  implements ICommand {

  private String id;
  private String newCity;
  private String oldCity;
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
      this.oldCity = repository.getHub(id).getCity();
      repository.editHub(id, newId, newCity);

  }

  @Override
  public void undo() {
      repository.editHub(newId, id, oldCity);
  }

}