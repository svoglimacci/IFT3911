package command;
import company.Airline;
import factory.AirFactory;
import factory.GroundFactory;
import factory.SeaFactory;
import hub.Airport;
import hub.Port;
import hub.TrainStation;
import repository.Repository;

public class CreateHubCommand  implements ICommand {
   private String id;
   private String city;
    private TravelType travelType;
   private Repository repository;

    public CreateHubCommand(String id, String city, TravelType travelType, Repository repository) {
      this.id = id;
      this.city = city;
      this.travelType = travelType;
      this.repository = repository;
    }

    @Override
    public void execute() {
       if (travelType == TravelType.AIR) {
           AirFactory airFactory = AirFactory.getInstance();
           Airport airport = airFactory.createHub(id, city);
           repository.addHub(airport, travelType);
       }

       if (travelType == TravelType.SEA) {
           SeaFactory seaFactory = SeaFactory.getInstance();
         Port port = seaFactory.createHub(id, city);
           repository.addHub(port, travelType);
       }

       if (travelType == TravelType.GROUND) {
         GroundFactory groundFactory = GroundFactory.getInstance();
         TrainStation trainStation = groundFactory.createHub(id, city);
          repository.addHub(trainStation, travelType);
       }
    }

@Override
  public void undo() {
      repository.deleteHub(id);
}

}