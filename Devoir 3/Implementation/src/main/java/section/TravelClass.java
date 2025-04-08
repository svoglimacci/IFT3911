package section;

public class TravelClass {

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

}