package visitor;

import itinerary.Itinerary;

public class AdminTripVisitor implements IVisitor{

  @Override
  public void visit(Itinerary itinerary) {
     System.out.println("[Admin]" + itinerary.getId());

  }
}