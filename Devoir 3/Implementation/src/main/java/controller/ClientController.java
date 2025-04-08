package controller;

import command.Invoker;
import java.util.ArrayList;
import itinerary.Flight;
import repository.Repository;

public class ClientController {

  private final Repository repository;

  private final Invoker invoker;

  public ClientController(Repository repository) {
    this.repository = repository;
    this.invoker = new Invoker();
  }


  public void undo() {
    invoker.undoLast();
  }


}