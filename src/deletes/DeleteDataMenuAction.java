package deletes;

import java.util.Scanner;

import menu.ActionMenuItem;

public class DeleteDataMenuAction extends ActionMenuItem {

    public Scanner stdin;

    public DeleteDataMenuAction(String description) {
        this.stdin = new Scanner(System.in);
        this.textDescription = description;
    }

    @Override
    public void execute(ActionMenuItem callingAction) {

        if (callingAction != null) {
            callingAction.execute(null);
        }
    }
}