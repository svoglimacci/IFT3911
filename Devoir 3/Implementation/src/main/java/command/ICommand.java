package command;

public interface ICommand {

  default void execute() {

  }

  default void undo() {

  }
}