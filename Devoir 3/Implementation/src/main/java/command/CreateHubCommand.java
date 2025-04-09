package command;
import company.Airline;
import factory.AirFactory;
import hub.Airport;
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
    }

}