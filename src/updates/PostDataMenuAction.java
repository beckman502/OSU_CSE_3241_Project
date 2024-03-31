package updates;

import java.util.Scanner;

import menu.ActionMenuItem;

public class PostDataMenuAction extends ActionMenuItem {

    public Scanner stdin;

    public PostDataMenuAction(String description) {
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