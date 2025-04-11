package command;

import hub.Airport;
import hub.Port;
import repository.Repository;
import hub.Hub;

public class DeleteHubCommand  implements ICommand {
  private String id;
  private Repository repository;

  private Hub oldHub;

  public DeleteHubCommand(String id, Repository repository) {
    this.id = id;
    this.repository = repository;
  }

  @Override
  public void execute() {
    this.oldHub = repository.getHub(id);

    repository.deleteHub(id);
  }

  @Override
  public void undo() {
    if (oldHub != null) {
      TravelType travelType;
      if (oldHub instanceof Airport) {
        travelType = TravelType.AIR;
      } else if (oldHub instanceof Port) {
        travelType = TravelType.SEA;
      } else {
        travelType = TravelType.GROUND;
      }
      repository.addHub(oldHub, travelType);
    }
  }


}