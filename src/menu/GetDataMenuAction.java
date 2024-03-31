package menu;

public class GetDataMenuAction extends ActionMenuItem {

    public GetDataMenuAction(String description) {
        this.textDescription = description;
    }

    @Override
    public void execute(ActionMenuItem callingAction) {
        this.goBack(callingAction);
    }
}