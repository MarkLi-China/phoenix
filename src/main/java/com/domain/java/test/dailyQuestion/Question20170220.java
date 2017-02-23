package com.domain.java.test.dailyQuestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * com.domain.java.test.dailyQuestion
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/2/20
 */
public class Question20170220 {

    public static void main(String[] args) {

        List<Student> list = new ArrayList<>();
        list.add(new Student("001"));

        Map<Student, Object> map = new HashMap<>();
        map.put(new Student("001"), new Object());

        System.out.println("list是否包含001：" + list.contains(new Student("001"))); // list是否包含001：true
        System.out.println("map是否包含001：" + map.containsKey(new Student("001"))); // map是否包含001：false
    }
}

class Student {

    private String stuId;

    public Student(String stuId) {

        super();
        this.stuId = stuId;
    }

    public String getStuId() {

        return stuId;
    }

    public void setStuId(String stuId) {

        this.stuId = stuId;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Student) {
            Student stu = (Student) obj;
            if (stu.getStuId() == null || stuId == null) {
                return false;
            } else {
                return stuId.equalsIgnoreCase(stu.getStuId());
            }
        }
        return false;
    }
}
