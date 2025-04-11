package company;

import command.TravelType;

public class Airline extends Company {

  public Airline(String id) {
    super(id);
  }

  @Override
  public TravelType getTravelType() {
    return TravelType.AIR;
  }

}