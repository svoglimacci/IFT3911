package command;

import java.util.Stack;

public class Invoker {
    private Stack<ICommand> commandHistory = new Stack<>();

    public void executeCommand(ICommand cmd) {
        cmd.execute();
        commandHistory.push(cmd);
    }

    public void undoLast() {
        if (!commandHistory.isEmpty()) {
            ICommand cmd = commandHistory.pop();
            cmd.undo();
        } else {
            System.out.println("Nothing to undo.");
        }
    }
}