package com.easytnt.ashman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author Liguiqing
 * @since V3.0
 */

public class Md5Removeable {
    private static final Logger logger = LoggerFactory.getLogger(Md5Removeable.class);

    private Connection connection;

    private String driver;

    private String url;

    private String username;

    private String password;

    private int start = 0;

    private String sql = "select a.address from ks_studentanswersheetimage a inner join ks_exam b on b.id=a.examid where b.state=3 limit ?,5000;";

    public Md5Removeable(String driver,String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;

        loadDriver();
        connect();
    }

    public Deque<String> next(){
        reConnect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = connection.prepareStatement(this.sql);
            logger.debug("Execute sql {}  with start :{}",sql , start);

            ps.setLong(1,start);
            rs = ps.executeQuery();
            ConcurrentLinkedDeque<String> md5s = new ConcurrentLinkedDeque<>();
            while (rs.next()) {
                md5s.add(rs.getString("address"));
            }
            start = start + md5s.size();
            return md5s;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }finally {
            closer(rs);
            closer(ps);
        }
        return new ConcurrentLinkedDeque<>();
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