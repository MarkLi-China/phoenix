package com.domain.java.test.lambda;

import com.domain.common.utils.JsonHelper2;

import java.util.*;

/**
 * com.domain.java.test
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/10/30
 */
public class TestMethodReference {

    public int compare01(TestMethodReference obj) {

        return 1;
    }

    public static int compare02(String str1, String str2) {

        return -1;
    }

    public int compare03(String str1, TestMethodReference str2) {

        return -1;
    }

    public static void main(String[] args) {

        String[] arr = new String[]{"A", "B", "C", null};
        // case 1 : Reference to a Static Method
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                return TestMethodReference.compare02(o1, o2);
            }
        });
        Arrays.sort(arr, (a, b) -> TestMethodReference.compare02(a, b));
        Arrays.sort(arr, TestMethodReference::compare02);
        System.out.println(Arrays.toString(arr));
        // case 2 : Reference to an Instance Method of a Particular Object
        class ComparisionProvider {

            public int compare(String str1, String str2) {

                return str1.compareTo(str2);
            }
        }
        ComparisionProvider provider = new ComparisionProvider();
        Arrays.sort(arr, provider::compare);
        // case 3 : Reference to an Instance Method of an Arbitrary Object of a Particular Type
        /*Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                return o1.compareToIgnoreCase(o2);
            }
        });
        Arrays.sort(arr, (a, b) -> a.compareToIgnoreCase(b));
        Arrays.sort(arr, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(arr));*/
        TestMethodReference[] arr1 = new TestMethodReference[]{new TestMethodReference(), new TestMethodReference()};
        Arrays.sort(arr1, new Comparator<TestMethodReference>() {
            @Override
            public int compare(TestMethodReference o1, TestMethodReference o2) {

                return o1.compare01(o2);
            }
        });
        Arrays.sort(arr1, (a, b) -> a.compare01(b));
        Arrays.sort(arr1, TestMethodReference::compare01);
        // case 4 : Reference to a Constructor
        List<String> notNullList = Arrays.stream(arr).filter(Objects::nonNull).
                collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(JsonHelper2.toJson(notNullList));
    }
}
