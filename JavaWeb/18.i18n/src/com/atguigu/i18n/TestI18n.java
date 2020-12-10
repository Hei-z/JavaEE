package com.atguigu.i18n;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 测试国际化相关的类
 */
public class TestI18n {
    /**
     * 测试Locale和DateFormat
     */
    @Test
    public void test01() {
        // 根据当前系统环境获取区域信息
        Locale aDefault = Locale.getDefault();
        // 中国的区域信息 zh_CN，区域信息由 语言_国家 组成
        Locale cn = Locale.CHINA;
        System.out.println(cn); // zh_CN
        Locale us = Locale.US;
        System.out.println(us); // en_US
        System.out.println("--------------------");
        // 传入日期的显示格式和区域信息
        DateFormat format1 = DateFormat.getDateInstance(DateFormat.FULL, cn);
        DateFormat format2 = DateFormat.getDateInstance(DateFormat.LONG, cn);
        DateFormat format3 = DateFormat.getDateInstance(DateFormat.MEDIUM, cn);
        DateFormat format4 = DateFormat.getDateInstance(DateFormat.SHORT, cn);
        //DateFormat.DEFAULT默认为DateFormat.MEDIUM
        DateFormat format5 = DateFormat.getDateInstance(DateFormat.DEFAULT, cn);
        System.out.println(format1.format(new Date())); // 2020年12月2日 星期三
        System.out.println(format2.format(new Date())); // 2020年12月2日
        System.out.println(format3.format(new Date())); // 2020-12-2
        System.out.println(format4.format(new Date())); // 20-12-2
        System.out.println(format5.format(new Date())); // 2020-12-2
    }

    /**
     * 测试ResourceBundle
     */
    @Test
    public void test02() throws UnsupportedEncodingException {
        // 编写不同的资源文件 通过ResourceBundle来管理，可以根据不同国家获取不同的值
        // 资源文件后缀为.properties，文件名为 基础名_语言_国家，
        // 如中国i18n_zh_CN.properties，美国i18n_en_US.properties
        // 将网站的提示信息放在这些文件中，然后国通文件动态获取，这些资源文件要放在类路径下

        // 传入文件的基础名和区域信息,getBundle()会根据基础名和区域信息找到相应的资源文件
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", Locale.CHINA);
        // 通过bundle对象可以根据key获取相应的value值
        String login = bundle.getString("login");
        System.out.println(login); // 登录
    }
}
