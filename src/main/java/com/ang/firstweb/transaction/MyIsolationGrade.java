package com.ang.firstweb.transaction;

import java.net.ConnectException;
import java.sql.*;

/**
 * Created by adimn on 2019/9/11.
 */
public class MyIsolationGrade {
    public static void main(String[] args) {

        readUncommited();
    }

    public static Connection gotConn(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://zja.monitor.com:8009/angdb?useUnicode=true&characterEncoding=utf8", "root", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void insert(String accountName,String user,Integer money){
        Connection conn = gotConn();
        try {
            conn.setAutoCommit(false);
//            conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            conn.prepareStatement("delete from account").execute();
            conn.commit();
            PreparedStatement statement = conn.prepareStatement("insert into account (accountName,user,money) VALUES (?,?,?)");
            statement.setString(1,accountName);
            statement.setString(2,user);
            statement.setInt(3,money);
            statement.executeUpdate();
//            int i = 1 / 0;
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void select(String user,Connection conn){
        try {
            PreparedStatement statement = conn.prepareStatement("select * from account where user= ?");
            statement.setString(1,user);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("执行查询");

            while (resultSet.next()){
                for(int i=1;i<=4 ;i++){
                    System.out.print(resultSet.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void readUncommited() {
        Thread addT = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                insert("gongshang","埃克森",100);
            }
        };



        Thread selectT = new Thread() {
            @Override
            public void run() {
                try {
                    Connection conn = gotConn();
                    conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
                    conn.setAutoCommit(false);
                    select("埃克森", conn);
                    Thread.sleep(1000);
                    addT.join();
                    select("埃克森", conn);
                    conn.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        selectT.start();
        addT.start();
    }



}
