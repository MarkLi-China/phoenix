package com.domain.java.util;

import com.domain.common.ExcelUtil;
import com.domain.java.bean.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.domain.java.util
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/12/23
 */
public class ExcelUtilTest {

    @Test
    public void test001() {

        String fileName = "D:\\Temp\\temp.xls";
        String[] heads = new String[]{"姓名", "年龄", "性别"};
        String[] properties = new String[]{"name", "age", "sex"};
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", "测试" + i);
            map.put("age", 20 + i);
            map.put("sex", i % 2 == 1);
            dataList.add(map);
        }
        ExcelUtil.writeExcel(fileName, heads, properties, dataList);
        fileName = "D:\\Temp\\temp01.xls";
        List<Student> dataList001 = new ArrayList<>();
        for (int j = 10; j < 20; j++) {
            Student student = new Student();
            student.setName("测试" + j);
            student.setAge(j);
            student.setSex(j % 2 == 1);
            dataList001.add(student);
        }
        ExcelUtil.writeExcel(fileName, heads, properties, dataList001);
    }
}
