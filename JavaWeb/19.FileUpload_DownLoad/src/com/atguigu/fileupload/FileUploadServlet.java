package com.atguigu.fileupload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public class FileUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // DiskFileItemFactory 可以用创建 FileItem，FileItem 封装了数据的内容
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // ServletFileUpload 用于操作文件上传
        ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
        request.setCharacterEncoding("utf-8");
        try {
            // 获取FileItem，每个FileItem 就是一个form 表单中的部件
            List<FileItem> list = servletFileUpload.parseRequest(request);
            for (FileItem item : list) {
                // 如果为true，那么说明该项就是普通的表单项
                if (item.isFormField()) {
                    // getName()返回文件名，如果是普通表单项，那么返回null
                    String name = item.getName();
                    // form 表单中input表单项中的name属性的值
                    String fieldName = item.getFieldName();
                    System.out.println("普通表单项的fieldName: " + fieldName);
                    System.out.println("普通表单项的name: " + name);
                    // 获取普通表单项的value值
                    String string = item.getString();
                    // 以utf-8 的方式获取表单项的值
                    String string1 = item.getString("utf-8");
                    System.out.println(string);
                } else {
                    // 如果为false，那么说明是文件
                    // getName()返回文件名，如果是文件表单项，那么返回上传的文件名
                    // 如果是ie 浏览器，那么会文件名会全路径，此时我们需要截取最后的文件名
                    StringBuilder name = new StringBuilder(item.getName());
                    int lastIdx = name.lastIndexOf("\\");
                    name = new StringBuilder(name.substring(lastIdx + 1));
                    // form 表单中input表单项中的name属性的值
                    String fieldName = item.getFieldName();
                    System.out.println("普通表单项的fieldName: " + fieldName);
                    System.out.println("普通表单项的name: " + name);
                    ServletContext servletContext = getServletContext();
                    // 获取项目打包后的真实路径(磁盘路径)
                    String realPath = servletContext.getRealPath("/upload");
                    // 生成随机的前缀，防止文件名重复
                    String prefix = UUID.randomUUID().toString();
                    name.insert(0, prefix + "_");
                    FileOutputStream fos = new FileOutputStream(realPath + "/" + name.toString());
                    // 获取上传文件的流对象
                    InputStream is = item.getInputStream();
                    // 将输入流中的内容拷贝到输出流中
                    IOUtils.copy(is, fos);
                    IOUtils.closeQuietly(fos);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
