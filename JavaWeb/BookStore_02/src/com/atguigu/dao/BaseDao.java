package com.atguigu.dao;

import com.atguigu.bean.User;
import com.atguigu.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseDao<T> {
    private QueryRunner runner = new QueryRunner();
    private Class<T> type;

    public BaseDao() {
        // 此处的this指向子类
        ParameterizedType baseDaoClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        type = (Class<T>) baseDaoClass.getActualTypeArguments()[0];
    }

    public T getBean(String sql, Object... params) {
        Connection connection = JDBCUtils.getConnection();
        T query = null;
        try {
            query = runner.query(connection, sql, new BeanHandler<>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseConnection(connection);
        }
        return query;
    }

    public List<T> getBeanList(String sql, Object... params) {
        Connection connection = JDBCUtils.getConnection();
        List<T> query = null;
        try {
            query = runner.query(connection, sql, new BeanListHandler<>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseConnection(connection);
        }
        return query;
    }

    public int update(String sql, Object... params) {
        int cnt = 0;
        Connection connection = JDBCUtils.getConnection();
        try {
            cnt = runner.update(connection, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseConnection(connection);
        }
        return cnt;
    }
}
