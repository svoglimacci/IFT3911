package command;

import java.util.Stack;
import repository.Memento;
import repository.Repository;

public class Invoker {
    private final Stack<ICommand> commandHistory = new Stack<>();
    private final Stack<Memento> mementoHistory = new Stack<>();

    public void save(Repository repository) {
      mementoHistory.push(repository.saveState());
    }

    public void restore(Repository repository) {
        if (!mementoHistory.isEmpty()) {
            Memento memento = mementoHistory.pop();
            repository.restoreState(memento);
            System.out.println("State restored from memento.");
        } else {
            System.out.println("No mementos to restore.");
        }
    }

    public void executeCommand(ICommand cmd) {
        cmd.execute();
        commandHistory.push(cmd);
    }


    public boolean undo() {
        if (!commandHistory.isEmpty()) {
            ICommand cmd = commandHistory.pop();
            cmd.undo();
            System.out.println("Command undone.");
            return true;
        } else {
            System.out.println("No commands to undo.");
            return false;
        }
    }

}