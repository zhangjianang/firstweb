package com.ang.firstweb.mybatis;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;

/**
 * Created by ang on 2019/9/13.
 */
public class LongValueHandler implements TypeHandler {

    @Override
    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        ps.setDate(i,new Date((Long) parameter));
    }

    @Override
    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getDate(columnName).getTime();
    }

    @Override
    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getDate(columnIndex).getTime();
    }

    @Override
    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getDate(columnIndex).getTime();
    }
}
