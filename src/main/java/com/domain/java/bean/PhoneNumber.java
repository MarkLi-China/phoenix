package com.domain.java.bean;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-8-20
 */
public final class PhoneNumber implements Comparable<PhoneNumber> {

    private final short areaCode;

    private final short prefix;

    private final short lineNumber;

    private volatile int hashCode;

    public PhoneNumber(int areaCode, int prefix, int lineNumber) {

        rangeCheck(areaCode, 999, "area code");
        rangeCheck(prefix, 999, "prefix");
        rangeCheck(lineNumber, 9999, "line number");
        this.areaCode = (short) areaCode;
        this.prefix = (short) prefix;
        this.lineNumber = (short) lineNumber;
    }

    private static void rangeCheck(int arg, int max, String name) {

        if (arg < 0 || arg > max) {
            throw new IllegalArgumentException(name + "" + arg);
        }
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof PhoneNumber)) {
            return false;
        }

        PhoneNumber pn = (PhoneNumber) o;

        return pn.areaCode == areaCode && pn.prefix == prefix && pn.lineNumber == lineNumber;
    }

    @Override
    public int hashCode() {

        int result = hashCode;

        if (result == 0) {
            result = 31 * result + areaCode;
            result = 31 * result + prefix;
            result = 31 * result + lineNumber;
            hashCode = result;
        }

        return result;
    }

    @Override
    public String toString() {

        return String.format("(%03d) %03d-%04d", areaCode, prefix, lineNumber);
    }

    @Override
    public int compareTo(PhoneNumber pn) {

        int areaCodeDiff = areaCode - pn.areaCode;
        if (areaCodeDiff != 0) {
            return areaCodeDiff;
        }

        int prefixDiff = prefix - pn.prefix;
        if (prefixDiff != 0) {
            return prefixDiff;
        }

        return lineNumber - pn.lineNumber;
    }
}
