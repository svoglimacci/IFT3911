package section;

public class TravelClass {

  public static final TravelClass PREMIERE_ETROITE = new TravelClass("PS", "Première étroite", 1.0);
  public static final TravelClass AFFAIRE_MOYENNE = new TravelClass("AM", "Affaire moyenne", 0.75);
  public static final TravelClass ECONOMIE_LARGE = new TravelClass("EL", "Économie large", 0.5);

  private String id;
  private String name;
  private double rate;

  public TravelClass(String id, String name, double rate) {
    this.id = id;
    this.name = name;
    this.rate = rate;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getRate() {
    return rate;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }

  @Override
  public String toString() {
    return id;
  }
}