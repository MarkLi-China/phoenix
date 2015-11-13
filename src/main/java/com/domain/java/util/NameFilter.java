package com.domain.java.util;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created with Intellij IDEA
 *
 * @author MarkLee
 * @since 2014/12/7
 */
public class NameFilter implements FilenameFilter {

    private String suffix;

    public NameFilter(String suffix) {

        this.suffix = suffix;
    }

    @Override
    public boolean accept(File dir, String name) {

//        System.out.println("dir = [" + dir + "], name = [" + name + "]");
        return name.endsWith(suffix);
    }
}
