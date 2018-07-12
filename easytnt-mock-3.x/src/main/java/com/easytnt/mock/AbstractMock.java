package com.easytnt.mock;

import com.easytnt.commons.domain.Identity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Liguiqing
 * @since V3.0
 */

public abstract class AbstractMock implements Mock {

    private Map<String, Object[]> fvs;

    public void createData(JdbcTemplate jdbc) {
        this.fvs = Arrays.stream(getFields().split(",")).map(String::trim)
                .collect(Collectors.toMap(k -> k, this::getValue, (k1, k2) -> k2, LinkedHashMap::new));
        this.delete(jdbc);
        this.insert(jdbc);
    }

    private void delete(JdbcTemplate jdbc) {
        jdbc.batchUpdate(sqlDelete());
    }

    private void insert(JdbcTemplate jdbc) {
        List<Object[]> args = args();
        String sqlInsert = sqlInsert();
        jdbc.batchUpdate(sqlInsert, args);
    }

    @Override
    public void userMocks(List<Mock> mocks) {

    }

    public String getId(int index){
        return this.ids()[index].id();
    }

    private String[] sqlDelete() {
        int size = this.ids().length;
        String[] sql = new String[size];
        int i = 0;
        for (Identity id : ids()) {
            sql[i++] = "DELETE from " + table() + " where " + getIdField() + "='" + id.id() + "'";
        }
        return sql;
    }

    private List<Object[]> args() {
        final int size = this.fvs.size();
        List<Object[]> args = new ArrayList<>();
        Iterator<String> keys = this.fvs.keySet().iterator();
        int i = 0;
        while (keys.hasNext()) {
            String key = keys.next();
            Object[] os = this.fvs.get(key);
            int j = 0;
            for (Object o : os) {
                if (args.size() < (j + 1)) {
                    args.add(new Object[size]);
                }

                Object[] oo = args.get(j);
                oo[i] = o;
                j++;
            }
            i++;
        }
        return args;
    }

    private String sqlInsert() {
        StringBuffer sb = new StringBuffer();
        this.fvs.forEach((key, value) -> sb.append("?,"));
        sb.delete(sb.length() - 1, sb.length());
        return "INSERT INTO " + table() + "(" + getFields() + ") " + "VALUES(" + sb + ")";
    }

    protected String[] fromTo(int from,int to){
        return fromTo(from,to,"");
    }

    protected String[] fromTo(int from,int to,String prefix){
        int length = to - from;
        String[] ss = new String[length];
        for(int i=0;i<length;i++){
            ss[i] = prefix+(to++);
        }
        return ss;
    }


    protected String[] otherids(int size,String prefix,String midfix){
        String[] ids = new String[size];
        for(int i=0;i<size;i++){
            ids[i] = IdMocker.genId(prefix,midfix+"-"+i);
        }
        return ids;
    }

    protected String[] repeatOfOtherids(int size,String prefix,String suffix){
        String id = IdMocker.genId(prefix,suffix);
        return this.repeatOf(size, id);
    }


    protected String[] repeatOfOtheridsGrop(int size,String prefix,String... suffix){
        String[] ids = new String[suffix.length];
        for(int i=0;i<ids.length;i++){
            ids[i] = IdMocker.genId(prefix,suffix[i]);
        }
        return this.repeatOfGroup(size, ids);
    }

    protected <T> T[] repeatOfGroup(int size,T... src){
        int group = src.length;
        T[] values = (T[]) Array.newInstance(src[0].getClass(), size);
        int j = 0;
        for(int i=0;i<size;i++){
            values[i] = src[j++];
            if(j==group)
                j=0;
        }
        return values;
    }

    protected <T> T[] repeatOf(int size,T value){
        T[] values = (T[]) Array.newInstance(value.getClass(), size);
        for(int i=0;i<size;i++){
            values[i] = value;
        }
        return values;
    }

    protected abstract String table();

    protected abstract String getIdField();

    protected abstract Object[] getValue(String key);

    protected abstract String getFields();

}