package company;

import command.TravelType;

public class CruiseLine extends Company {

  public CruiseLine(String id) {
    super(id);
  }

    @Override
  public TravelType getTravelType() {
    return TravelType.SEA;
  }


}