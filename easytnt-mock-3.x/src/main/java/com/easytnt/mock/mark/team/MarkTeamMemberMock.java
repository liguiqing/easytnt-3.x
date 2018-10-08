package com.easytnt.mock.mark.team;

import com.easytnt.mock.AbstractMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Liguiqing
 * @since V3.0
 */
@Component
public class MarkTeamMemberMock extends AbstractMock {

    @Autowired
    private MarkerMock marker;

    @Override
    public String table() {
        return "ps_mark_team_member";
    }

    @Override
    public String getIdField() {
        return "team_id";
    }

    @Override
    public Object[] getMockValue(String key) {
        switch(key){
            case "team_id": return ids();
            case "mark_item_id": return markItemIds();
            case "marker_id": return markerIds();
            case "role": return role();
            case "is_del": return this.repeator.repeatOf(this.size(),0);
            default: return this.repeator.repeatOf(this.size(),null);
        }
    }

    private Object[] markItemIds(){
        return marker.getValues("mark_item_id");
    }

    private Object[] markerIds(){
        return marker.getValues("marker_id");
    }

    private Object[] role(){
        return marker.getValues("role");
    }

    @Override
    public int size(){
        return marker.size();
    }

    @Override
    public String[] ids() {
        MarkTeamMock markTeam = getOtherMock(MarkTeamMock.class);
        return Arrays.stream(valuesFromOtherMock("mark_item_id","mark_item_id","team_id",false,markTeam))
                .map(id->(String)id).collect(Collectors.toList()).toArray(new String[]{});
    }
}