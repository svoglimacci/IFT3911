package section;

import java.util.ArrayList;
import seating.Available;
import seating.PlaneSeat;
import seating.Seating;

public abstract class Section {

  private TravelClass travelClass;
  private Layout layout;

  private int nbSeats;
  private ArrayList<Seating> seatings;

  public Section(TravelClass travelClass, Layout layout, int nbSeats) {
    this.travelClass = travelClass;
    this.layout = layout;
    this.nbSeats = nbSeats;
    this.seatings = new ArrayList<>();
  }

  public double getRate() {
    return travelClass.getRate();
  }

  public TravelClass getTravelClass() {
    return travelClass;
  }

  public boolean addSeating(Seating seating) {
    if (seating != null) {
      seatings.add(seating);
      return true;
    }
    return false;
  }

  public boolean deleteSeating(Seating seating) {
    if (seating != null && seatings.contains(seating)) {
      seatings.remove(seating);
      return true;
    }
    return false;
  }



  public ArrayList<Seating> getAvailableSeats() {
    ArrayList<Seating> availableSeats = new ArrayList<>();
    for (Seating seating : seatings) {
      if (seating.getState() instanceof Available) {
        availableSeats.add(seating);
      }
    }
    return availableSeats;
  }

  public Layout getLayout() {
    return layout;
  }

  public ArrayList<Seating> getSeatings() {
    return seatings;
  }


}