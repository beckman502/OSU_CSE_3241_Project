package selects;

import menu.ActionMenuItem;
import menu.GetDataMenuAction;

public class GetCommunityMembersDataAction extends GetDataMenuAction {

    public GetCommunityMembersDataAction(String description) {
        super(description);
    }

    @Override
    public void execute(ActionMenuItem callingAction) {
        String sql = "SELECT * FROM Community_Members;";
        ActionMenuItem.sqlHandler.sqlQuery(sql);
        this.goBack(callingAction);
    }
}