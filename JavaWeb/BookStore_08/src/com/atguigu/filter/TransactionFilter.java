package com.atguigu.filter;

import com.atguigu.util.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Connection connection = JDBCUtils.getConnection();
        try {
            // 设置手动提交事务
            connection.setAutoCommit(false);
            // 放行请求，其可以调用Servlet中的方法
            chain.doFilter(req, resp);
            // 执行完Servlet中的方法之后，如果没有出现异常，那么就可以提交事务了
            connection.commit();
        } catch (Exception e) {
            //e.printStackTrace();
            try {
                // 回滚事务
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            // 可以把异常抛出给tomcat处理，这样可以设置error页面，给用户更好的展示效果
            throw new RuntimeException(e);
        } finally {
            // 释放connection对象
            JDBCUtils.releaseConnection(connection);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
