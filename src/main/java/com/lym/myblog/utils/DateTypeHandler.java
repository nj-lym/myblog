package com.lym.myblog.utils;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/** 自定义转换器
 * @Description 自定义时间转换器
 * @Auther lym
 * @Date 2020-07-24 8:47
 * @Version 1.0
 */
@MappedJdbcTypes(JdbcType.DATE)
@MappedTypes(String.class)
public class DateTypeHandler implements TypeHandler<String>
{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 通过preparedStatement对象设置参数，将T类型的数据存入数据库
     * @param statement
     * @param i
     * @param s
     * @param type
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement statement, int i, String s, JdbcType type) throws SQLException
    {

    }

    @Override
    public String getResult(ResultSet set, String columnName) throws SQLException
    {
        //根据列名获取结果集中的时间,并转换格式
        return sdf.format(set.getDate(columnName));
    }

    @Override
    public String getResult(ResultSet set, int columnIndex) throws SQLException
    {
        return sdf.format(set.getDate(columnIndex));
    }

    @Override
    public String getResult(CallableStatement statement, int columnIndex) throws SQLException
    {
        return sdf.format(statement.getDate(columnIndex));
    }
}
