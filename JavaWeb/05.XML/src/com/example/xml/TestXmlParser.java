package com.example.xml;


import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TestXmlParser {
    /**
     * 获取document对象
     */
    @Test
    public void testXml1() throws Exception {
        SAXReader saxReader = new SAXReader();
        // 获取document对象
        Document document = saxReader.read(new File("src/books.xml"));
        System.out.println(document);
    }

    /**
     * 解析XML文件
     */
    @Test
    public void testXml2() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("src/books.xml"));
        // 获取根节点
        Element rootElement = document.getRootElement();
        // 获取子节点
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            // 获取元素名
            // System.out.println(element.getName());

            // 获取某个子元素的文本值
            // System.out.println(element.elementText("name"));
            List<Element> eles = element.elements();

            // 获取文本值
            /*for (Element ele : eles) {
                System.out.println(ele.getText());
            }
            System.out.println();*/
        }
        // 获取某个节点的属性值 element代表book节点
        for (Element element : elements) {
            String sn = element.attributeValue("sn");
            System.out.println(sn);
        }
    }

    // 解析xml文件，并封装成对象
    @Test
    public void testXml3() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("src/books.xml"));
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        List<Book> books = new ArrayList<>();
        for (Element element : elements) {
            // 获取相应的值
            String sn = element.attributeValue("sn");
            String name = element.elementText("name");
            // elementText可以获取子节点的文本值
            Double price = Double.parseDouble(element.elementText("price"));
            String author = element.elementText("author");
            books.add(new Book(sn, name, price, author));
        }
        System.out.println(books);
    }

    /**
     * 将document对象写入到xml文件中
     */
    @Test
    public void testXml4() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("src/books.xml"));
        Element rootElement = document.getRootElement();
        // Returns the first element for the given local name and any namespace.
        Element bookEle = rootElement.element("book");
        bookEle.addAttribute("id", "10");
        Element nameEle = bookEle.element("name");
        nameEle.setText("西游记");
        // 会将输出的xml文件中多余的空白字符去掉
        // OutputFormat compactFormat = OutputFormat.createCompactFormat();
        OutputFormat prettyPrint = OutputFormat.createPrettyPrint();
        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("src/out.xml"), prettyPrint);
        xmlWriter.write(document);
        xmlWriter.close();
    }

    /**
     * 测试xPath
     */
    @Test
    public void testXml5() throws Exception {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File("src/books.xml"));
        // 传入一个xpath表达式，找出特定的元素
        //找出sn="SN12341231"的元素
        Element node = (Element) document.selectSingleNode("//book[@sn='SN12341231']");
        System.out.println(node.getName());
        System.out.println(node.attributeValue("sn"));

        // 找出所有的名为name的元素
        List<Element> nodes = document.selectNodes("//name");
        for (Element element : nodes) {
            System.out.println(element.getText());
        }
    }

}
