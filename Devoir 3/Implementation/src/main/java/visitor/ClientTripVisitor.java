package visitor;

import itinerary.Itinerary;

public class ClientTripVisitor implements IVisitor{

  @Override
  public void visit(Itinerary itinerary) {
    System.out.println("[Client]" + itinerary.getId());
  }
}