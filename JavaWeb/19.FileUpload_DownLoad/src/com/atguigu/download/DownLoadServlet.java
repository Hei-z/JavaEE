package com.atguigu.download;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 处理文件的下载
 * 当浏览器无法解析(打开)某个文件时，那么其就会下载该文件，
 * 因此我们只要告诉浏览器不要打开某个文件即可，这样它就会下载该文件
 */
public class DownLoadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = "你好.txt";
        // 获取文件的真实路径
        String realPath = getServletContext().getRealPath("/download/" + fileName);
        // 获取文件的mime 类型
        String mimeType = getServletContext().getMimeType("/download/" + fileName);
        // 设置要下载资源的类型
        response.setContentType(mimeType);
        // 如果文件名是中文，那么需要重新编码，使得浏览器能够解析
        // 首先通过fileName.getBytes()按照当前系统的编码获取到字节数组，然后再创建字符串按照ISO-8859-1进行编码，
        // 按照ISO-8859-1编码的可能原因是tomcat服务器默认的编码是ISO-8859-1，在服务器给浏览器响应时，需要对字符串进行
        // 编码，那么默认就用ISO-8859-1，因此我们按照这种格式解码编码字符串，服务器再次编码时这样就不会出现乱码。
        // 然后浏览器应该是按照ISO-8859-1编码，再按照utf-8解码。
        String encodeFileName = new String(fileName.getBytes(), "ISO-8859-1");
        // 设置文件的处理方式(attachment，附件类型)，以及推荐下载的文件名
        response.setHeader("Content-Disposition", "attachment;filename=" + encodeFileName);
        FileInputStream is = new FileInputStream(realPath);
        // 获取输出流对象
        ServletOutputStream sos = response.getOutputStream();
        // 将输入流中的内容拷贝到输出流中
        IOUtils.copy(is, sos);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
