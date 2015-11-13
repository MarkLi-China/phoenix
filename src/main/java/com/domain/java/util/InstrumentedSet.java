package com.domain.java.util;

import java.util.Collection;
import java.util.Set;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-8-24
 */
public class InstrumentedSet<E> extends ForwardingSet<E> {

    private int addCount = 0;

    public InstrumentedSet(Set<E> s) {

        super(s);
    }

    @Override
    public boolean add(E e) {

        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {

        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {

        return addCount;
    }
}
