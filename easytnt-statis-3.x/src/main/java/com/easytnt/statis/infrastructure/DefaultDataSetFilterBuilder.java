package com.easytnt.statis.infrastructure;

import com.easytnt.commons.domain.Identity;
import com.easytnt.share.domain.id.mark.MarkItemId;
import com.easytnt.share.domain.id.mark.MarkerId;
import com.easytnt.share.domain.id.mark.MarkerTeamId;
import com.easytnt.statis.domain.mark.DataSetFilter;
import com.easytnt.statis.domain.mark.DataSetFilterBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class DefaultDataSetFilterBuilder implements DataSetFilterBuilder {


    private static class SingletonHolder{
        private static final DefaultDataSetFilterBuilder instance = new DefaultDataSetFilterBuilder();
    }

    public static DataSetFilterBuilder getInstance(){
        return SingletonHolder.instance;
    }

    private DefaultDataSetFilterBuilder(){};

    @Override
    public DataSetFilter concreate(Map<String, String[]> filterMap){

        String[] ss2 = getParams("markItemIds",filterMap);
        List<Identity> itemIds = Arrays.stream(ss2).map(s-> new MarkItemId(s)).collect(Collectors.toList());
        List<Identity> teamIds = Arrays.stream(ss2).map(s-> new MarkerTeamId(s)).collect(Collectors.toList());
        List<Identity> markerIds = Arrays.stream(ss2).map(s-> new MarkerId(s)).collect(Collectors.toList());
        DataSetFilter itemIdFilter = id -> {
            if(id instanceof  MarkItemId){
                if(itemIds.size() > 0){
                    return !itemIds.contains(id);
                }
                return false;
            }else if(id instanceof MarkerTeamId){
                if(teamIds.size() > 0){
                    return !teamIds.contains(id);
                }
                return false;
            }else if(id instanceof MarkerId){
                if(markerIds.size() > 0){
                    return !markerIds.contains(id);
                }
                return false;
            }
            return false;
        };

        return itemIdFilter;
    }

    private String[] getParams(String key,Map<String, String[]> filterMap){
        String[] ss = filterMap.get(key);
        if(ss == null)
            return new String[]{};
        return ss;
    }
}