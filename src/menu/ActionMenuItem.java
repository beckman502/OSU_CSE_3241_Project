package menu;

public abstract class ActionMenuItem {
    protected static SQL sqlHandler = new SQL("DroneCompany");

    public String textDescription;

    public abstract void execute(ActionMenuItem callingAction);

    protected final void goBack(ActionMenuItem a) {
        if (a != null) {
            a.execute(null);
        }
    }

    @Override
    public String toString() {
        if (this.textDescription == null) {
            return "";
        }
        return this.textDescription;
    }
}
