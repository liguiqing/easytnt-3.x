package com.easytnt.mock;

import com.easytnt.commons.spring.SpringContextUtil;
import com.easytnt.commons.util.NumberUtilWrapper;
import com.easytnt.mock.exam.ExamMock;
import com.easytnt.mock.exam.SubjectMock;
import com.easytnt.mock.examinee.ExamineeMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import sun.java2d.xr.MutableInteger;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Liguiqing
 * @since V3.0
 */

public abstract class AbstractMock implements Mock {

    @Autowired
    protected Repeator repeator;

    private Map<String, Object[]> fvs;

    private String[] fields = null;

    public void createData(JdbcTemplate jdbc) {
        this.genFields(jdbc);
        this.fvs = Arrays.stream(this.fields).collect(Collectors.toMap(k -> k, this::getValue, (k1, k2) -> k2, LinkedHashMap::new));
        this.delete(jdbc);
        this.insert(jdbc);
        this.correct(jdbc);
    }

    @Override
    public int size(){
        return 1;
    }

    @Override
    public int order(){return 0;}

    private void delete(JdbcTemplate jdbc) {
        jdbc.batchUpdate(sqlDelete());
    }

    private void insert(JdbcTemplate jdbc) {
        List<Object[]> args = args();
        String sqlInsert = sqlInsert();
        jdbc.batchUpdate(sqlInsert, args);
    }

    protected void correct(JdbcTemplate jdbc){

    }

    public String getId(int index){
        return this.ids()[index];
    }

    protected String[] getFields(){
        return this.fields;
    }

    private void genFields(JdbcTemplate jdbc){
        SqlRowSet rowSet = jdbc.queryForRowSet("select * from " + this.table() + " limit 0");
        SqlRowSetMetaData metaData = rowSet.getMetaData();
        String[] coloumnName = metaData.getColumnNames();
        this.fields = Stream.of(coloumnName).filter(s -> !"id".equals(s)).toArray(String[]::new);
    }

    private String[] sqlDelete() {
        return Stream.of(ids()).map(this::genDeleteSql).collect(Collectors.toList()).toArray(new String[]{});
    }

    private String genDeleteSql(String id){
        return "DELETE from " + table() + " where " + getIdField() + "='" + id + "'";
    }

    private List<Object[]> args() {
        final int size = this.fields.length;
        Object[] os = this.fvs.get(this.fields[0]);
        List<Object[]> args = new ArrayList<>(os.length);
        for(int i=0;i<os.length;i++){
            args.add(new Object[size]);
        }

        for(int i=0;i<os.length;i++){
            Object[] arg = args.get(i);

            final MutableInteger count = new MutableInteger(0);
            final int m = i;
            this.fvs.forEach((k,v)->{
                int j = count.getValue();
                arg[j] = v[m];
                count.setValue(j+1);
            });
            //args.add(arg);
        }
//
//
//        for(int i=0;i<os.length;i++){
//            args.add(new Object[size]);
//        }
//
//
//        final MutableInteger count = new MutableInteger(0);
//        this.fvs.forEach((k,v)->{
//            final MutableInteger count2 = new MutableInteger(0);
//            int i = count2.getValue();
//            int j = count.getValue();
//            Stream.of(v).forEach(o->{args.get(count2.getValue())[count.getValue()]=o;});
//            count.setValue(j++);
//            count2.setValue(i++);
//        });

        return args;
    }

    private String fieldsAsString(){
        return String.join(",",getFields());
    }

    private String sqlInsert() {
        String[] args = Stream.of(getFields()).map(s -> "?").toArray(String[]::new);//fieldsAsString();
        String arg = String.join(",",args);
        return "INSERT INTO " + table() + "(" + fieldsAsString() + ") " + "VALUES(" + arg + ")";
    }

    protected String getOssServer(){
        return "http://192.168.1.230/zimg/";
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
        return this.repeator.repeatOf(size, id);
    }


    protected String[] repeatOfOtheridsGrop(int size,String prefix,String... suffix){
        String[] ids = new String[suffix.length];
        for(int i=0;i<ids.length;i++){
            ids[i] = IdMocker.genId(prefix,suffix[i]);
        }
        return this.repeator.repeatOfGroup(size, ids);
    }

    protected String[] repeatOfOtheridsGropOfEach(int size,int repeatsOfEach,String prefix,String... suffix){
        return this.repeatOfOtheridsGropOfEachMaxLt(size,repeatsOfEach,1,prefix,suffix);
    }

    protected String[] repeatOfOtheridsGropOfEachMaxLt(int size,int repeatsOfEach,int max,String prefix,String... suffix){
        String[] ids = new String[suffix.length];
        for(int i=0;i<ids.length;i++){
            ids[i] = IdMocker.genId(prefix,suffix[i]);
        }
        return this.repeator.repeatOfGroupOfEachMaxLt(size,repeatsOfEach,max,ids);
    }

    protected <T> T[] repeatOfGroup(int size,T... src){
        return this.repeator.repeatOfGroup(size, src);
    }

    protected <T> T[] repeatOf(int size,T value){
        return this.repeator.repeatOf(size, value);
    }

    protected <T,R extends T> T[] repeatOfMixedRandom(int size,R mixed,double rate,T value){
        return this.repeator.repeatOfMixedRandom(size, mixed, rate, value);
    }

    protected <T,R extends T> T[] repeatOfGroupMixedRandom(int size,R[] rs,double rate,T... src){
        return this.repeator.repeatOfGroupMixedRandom(size, rs, rate, src);
    }

    protected ExamMock getExam(){
        return SpringContextUtil.getBean(ExamMock.class);
    }

    protected SubjectMock getSubject(){
        return SpringContextUtil.getBean(SubjectMock.class);
    }

    protected ExamineeMock getExaminee(){
        return SpringContextUtil.getBean(ExamineeMock.class);
    }

    protected abstract String table();

    protected abstract String getIdField();

    protected abstract Object[] getValue(String key);

}