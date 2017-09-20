package com.domain.java.jvm.chapter02;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/9/14
 */
public class HeapOOM {

    static class OOMObject {}

    public static void main(String[] args) {

        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
