package command;

import repository.Repository;
import section.Layout;

public class CreateSectionCommand implements ICommand {

  private String vehicleId;
  private String travelClass;
  private Layout layout;
  private int nbSeats;

  private TravelType travelType;
  private Repository repository;

  public CreateSectionCommand(String vehicleId, String travelClass, Layout layout, int nbSeats, TravelType travelType, Repository repository) {
    this.vehicleId = vehicleId;
    this.travelClass = travelClass;
    this.layout = layout;
    this.nbSeats = nbSeats;
    this.repository = repository;
    this.travelType = travelType;
  }

  @Override
  public void execute() {
    repository.createSection(vehicleId, travelClass, layout, nbSeats, travelType);
  }

    @Override
  public void undo() {
    repository.deleteSection(vehicleId, travelClass, travelType);
  }




}