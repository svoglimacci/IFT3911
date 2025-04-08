package view;

import repository.Repository;

public class ClientView implements IObserver {

  public ClientView(Repository repository) {
    repository.addObserver(this);
  }

  @Override
  public void update() {

  }

}