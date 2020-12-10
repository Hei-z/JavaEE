package com.atguigu.test;

import com.atguigu.bean.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestJson {
    /**
     * 将JavaBean对象转成json字符串
     */
    @Test
    public void test1() {
        Gson gson = new Gson();
        Student stu = new Student("zrh", 20);
        // 将对象转成json字符串
        String json = gson.toJson(stu);
        System.out.println(json); // {"name":"zrh","age":20}
    }

    /**
     * 将json字符串转成JavaBean对象
     */
    @Test
    public void test2() {
        Gson gson = new Gson();
        String str = "{\"name\":\"zrh\",\"age\":20}";
        // 传入json字符串和需要转成的对象类型
        Student student = gson.fromJson(str, Student.class);
        System.out.println(student);
    }

    /**
     * json字符串和List互转
     */
    @Test
    public void test3() {
        List<Student> list = new ArrayList<>();
        Student stu1 = new Student("张三", 18);
        Student stu2 = new Student("李四", 18);
        list.add(stu1);
        list.add(stu2);
        Gson gson = new Gson();
        // 将list转成json字符串
        String str = gson.toJson(list);
        // [{"name":"张三","age":18},{"name":"李四","age":18}]
        System.out.println(str);
        System.out.println("--------------------");
        // TypeToken无public构造器，因此此处需要继承它，为了方便直接写成匿名内部类的方式
        // TypeToken中的泛型表示需要转化的类型
        List<Student> list1 = gson.fromJson(str, new TypeToken<List<Student>>() {
        }.getType());
        // java.util.ArrayList
        System.out.println(list1.getClass().getTypeName());
        // [Student{name='张三', age=18}, Student{name='李四', age=18}]
        System.out.println(list1);
    }

    /**
     * json与map互转
     */
    @Test
    public void test4() {
        Student stu1 = new Student("张三", 18);
        Student stu2 = new Student("李四", 18);
        Map<String, Student> map = new HashMap<>();
        map.put("k1", stu1);
        map.put("k2", stu2);
        Gson gson = new Gson();
        // 将map转成json字符串
        String jsonStr = gson.toJson(map);
        // {"k1":{"name":"张三","age":18},"k2":{"name":"李四","age":18}}
        System.out.println(jsonStr);
        // 将json字符串转成map
        Map<String, Student> map1 = gson.fromJson(jsonStr, new TypeToken<Map<String, Student>>() {
        }.getType());
        System.out.println("------------------");
        // com.google.gson.internal.LinkedTreeMap
        System.out.println(map1.getClass().getTypeName());
        // {k1=Student{name='张三', age=18}, k2=Student{name='李四', age=18}}
        System.out.println(map1);
    }
}
