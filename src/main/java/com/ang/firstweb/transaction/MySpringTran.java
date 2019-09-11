package com.ang.firstweb.transaction;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by adimn on 2019/9/11.
 */
public class MySpringTran {
    private static String url = "jdbc:mysql://zja.monitor.com:8009/angdb?useUnicode=true&characterEncoding=utf8";
    private static String user = "root";
    private static String password = "123456";


    public static void main(String[] args) {
        final DataSource ds = new DriverManagerDataSource(url, user, password);

        final TransactionTemplate template = new TransactionTemplate();

        template.setTransactionManager(new DataSourceTransactionManager(ds));

        template.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                Connection conn = DataSourceUtils.getConnection(ds);
                Object savePoint = null;
                try {
                    {
                        // 插入
                        PreparedStatement prepare = conn.
                                prepareStatement("insert INTO account (accountName,user,money) VALUES (?,?,?)");
                        prepare.setString(1, "111");
                        prepare.setString(2, "aaaa");
                        prepare.setInt(3, 10000);
                        prepare.executeUpdate();
                    }
                    // 设置保存点
                    savePoint = status.createSavepoint();
                    {
                        // 插入
                        PreparedStatement prepare = conn.
                                prepareStatement("insert INTO account (accountName,user,money) VALUES (?,?,?)");
                        prepare.setString(1, "222");
                        prepare.setString(2, "bbb");
                        prepare.setInt(3, 10000);
                        prepare.executeUpdate();
                    }
                    {
                        // 更新
                        PreparedStatement prepare = conn.
                                prepareStatement("UPDATE account SET money= money+1 where user=?");
                        prepare.setString(1, "asdflkjaf");

                        int i=1/0;

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println("更新失败");
                    if (savePoint != null) {
                        status.rollbackToSavepoint(savePoint);
                    } else {
                        status.setRollbackOnly();
                    }
                }
                return null;
            }
        });
    }

}
