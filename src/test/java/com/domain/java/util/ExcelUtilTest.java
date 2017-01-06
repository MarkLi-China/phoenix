package com.domain.java.util;

import com.domain.common.ExcelUtil;
import com.domain.java.bean.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * com.domain.java.util
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/12/23
 */
public class ExcelUtilTest {

    @Test
    public void test001() {

        String fileName = "D:\\Temp\\temp01.xls";
        String[] heads = new String[]{"姓名", "年龄", "性别"};
        String[] properties = new String[]{"name", "age", "sex"};
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
