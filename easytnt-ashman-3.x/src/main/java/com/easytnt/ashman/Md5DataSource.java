package com.easytnt.ashman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class Md5DataSource {
    private static final Logger logger = LoggerFactory.getLogger(Md5DataSource.class);

    private Connection connection;

    private String driver;

    private String url;

    private String username;

    private String password;

    private String sql = "select a.address from ks_answersheet a where a.examId=? and length(a.address) >0 union all select a.address from ks_studentanswersheetimage a where a.examId=? and length(a.address) >0;";

    public Md5DataSource(String driver,String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;

        loadDriver();
        connect();
    }

    public List<String> next(long examId) {
        reConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = connection.prepareStatement(this.sql);
            logger.debug("Execute sql {}  with examId:{}",sql , examId);

            ps.setLong(1,examId);
            ps.setLong(2,examId);
            rs = ps.executeQuery();
            ArrayList<String> md5s = new ArrayList<>();
            while (rs.next()) {
                md5s.add(rs.getString("address"));
            }
            logger.debug("Size of examId's md5: {} -> {}", examId,md5s.size());
            return md5s;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }finally {
            closer(rs);
            closer(ps);
        }
        return new ArrayList<>();
    }

    private void loadDriver(){
        try {
            Class.forName(this.driver);
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public void closeConnection(){
        closer(this.connection);
    }

    private void reConnect() {
        if(this.connection == null){
            connect();
            return ;
        }

        try {
            if(this.connection.isClosed()){
                connect();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    private void connect(){
        try {
            logger.debug("Connect to DB {}  User {},Password {}",url,username,password);
            this.connection = DriverManager.getConnection(url,username,password);
            logger.debug("Db connected success");
        } catch (SQLException e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    private void closer(AutoCloseable closeable){
        if(closeable != null){
            try {
                closeable.close();
            } catch (Exception e) {
                logger.error(e.getLocalizedMessage());
            }
        }
    }
}