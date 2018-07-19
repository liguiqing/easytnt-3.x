package com.easytnt.mock;

import com.easytnt.commons.spring.SpringContextUtil;
import com.easytnt.mock.answersheet.AnswerSheetMock;
import com.easytnt.mock.exam.ExamMock;
import com.easytnt.mock.exam.SubjectMock;
import com.easytnt.mock.examinee.ExamineeMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Liguiqing
 * @since V3.0
 */

public abstract class AbstractMock implements Mock {

    @Autowired(required = false)
    private DataSet dataSet;

    @Autowired
    protected Repeator repeator;

    private Map<String, Object[]> fvs;

    private String[] fields = null;

    public void createData(JdbcTemplate jdbc) {
        this.genFields(jdbc);
        this.fvs = Arrays.stream(this.fields).collect(Collectors.toMap(k -> k, this::getValues, (k1, k2) -> k2, LinkedHashMap::new));
        this.delete(jdbc);
        this.insert(jdbc);
        this.correct(jdbc);
    }

    public Object[] getValues(String key){
        if(this.dataSet != null){
            return dataSet.getValuesOf(key,this);
        }
        return this.getMockValue(key);
    }

    public Object[] getFieldValus(String field){
        return this.fvs.get(field);
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
        String[] columnNames = metaData.getColumnNames();
        this.fields = Stream.of(columnNames).filter(s -> !"id".equals(s)).toArray(String[]::new);
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
            final MutableInt count = new MutableInt(0);
            final int m = i;
            this.fvs.forEach((k,v)->{
                int j = count.getValue();
                arg[j] = v[m];
                count.setValue(j+1);
            });
        }
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

    protected String[] fromTo(String prefix,int from,int to){
        int length = to - from + 1;
        String[] ss = new String[length];
        for(int i=0;i<length;i++){
            ss[i] = prefix+(from++);
        }
        return ss;
    }

    protected String[] fromTo(int from,int to,String suffix){
        int length = to - from + 1;
        String[] ss = new String[length];
        for(int i=0;i<length;i++){
            ss[i] = (from++)+suffix;
        }
        return ss;
    }

    protected String[] genIds(String prefix,String midfix,String... suffix){
        String[] ids = new String[suffix.length];
        for(int i=0;i<ids.length;i++){
            if((midfix==null||midfix.length()==0))
                ids[i] = IdMocker.genId(prefix,suffix[i]);
            else
                ids[i] = IdMocker.genId(prefix,midfix+"-"+suffix[i]);
        }
        return ids;
    }

    protected  <T> void  setArray(T t, T[] ts, int start, int end){
        for(int i=start;i<=end;i++){
            ts[i] = t;
        }
    }

    protected  <T,V> void  setArray( T[] ts, int start, int end,V v,Function<V,T> func){
        for(int i=start;i<=end;i++){
            ts[i] = func.apply(v);
        }
    }

    public ExamMock getExam(){
        return SpringContextUtil.getBean(ExamMock.class);
    }

    public SubjectMock getSubject(){
        return SpringContextUtil.getBean(SubjectMock.class);
    }

    public ExamineeMock getExaminee(){
        return SpringContextUtil.getBean(ExamineeMock.class);
    }

    public AnswerSheetMock getAnswerSheet(){
        return SpringContextUtil.getBean(AnswerSheetMock.class);
    }

    public abstract String table();

    public abstract String getIdField();

    public abstract Object[] getMockValue(String key);

}