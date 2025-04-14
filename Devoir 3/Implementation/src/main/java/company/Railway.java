package company;

import command.TravelType;

public class Railway extends Company {

  public Railway(String id) {
    super(id);
  }

    @Override
  public TravelType getTravelType() {
    return TravelType.GROUND;
  }


}