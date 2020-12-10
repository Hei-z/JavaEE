package com.atguigu.dao;

import com.atguigu.bean.User;
import com.atguigu.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseDao<T> {
    private QueryRunner runner = new QueryRunner();
    private Class<T> type;

    public BaseDao() {
        // 因为this指定当前对象，在构建子类中会调用父类的构造器，那么此时this执行子类对象
        ParameterizedType baseDaoClass = (ParameterizedType) this.getClass().getGenericSuperclass();
        // 获取第一个泛型参数的Class类型
        type = (Class<T>) baseDaoClass.getActualTypeArguments()[0];
    }

    /**
     * 查询一个对象并封装成Bean
     */
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

    /**
     * 查询对象列表
     */
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

    /**
     * 执行增删改
     */
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

    public Object getSingleValue(String sql, Object... params) {
        Connection connection = JDBCUtils.getConnection();
        Object res = null;
        try {
            res = runner.query(connection, sql, new ScalarHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.releaseConnection(connection);
        }
        return res;
    }
}
