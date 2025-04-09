package section;



public enum Layout {
  NARROW("NARROW", "S"), MEDIUM("MEDIUM", "M"), COMFORT("COMFORT", "C"), LARGE("LARGE", "L");

  private String name;
  private String code;

  Layout(String name, String code) {
    this.name = name;
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }
}