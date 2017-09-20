package com.domain.java.jvm.chapter03;

/**
 * -verbose:gc -Xms40M -Xmx40M -Xmn20M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
 * -XX:+UseSerialGC
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/9/15
 */
public class TestGCThreshold {

    private static final int _1MB = 1024 * 1024;

    // MaxTenuringThreshold=1
    public static void testTenuringThreshold() {

        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 2];
        allocation2 = new byte[8 * _1MB];
        // 此处触发第一次MinorGC, allocation2被移到老年代, allocation1移到Survivor
        allocation3 = new byte[8 * _1MB];
        allocation3 = null;
        // 此处触发第二次MinorGC, MaxTenuringThreshold=1, allocation1被移到老年代
        allocation3 = new byte[8 * _1MB];

        /*
        [GC (Allocation Failure) [DefNew
        Desired survivor size 1048576 bytes, new threshold 1 (max 1)
        - age   1:    1676880 bytes,    1676880 total
        : 12327K->1637K(18432K), 0.0040971 secs] 12327K->9829K(38912K), 0.0041278 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        [GC (Allocation Failure) [DefNew
        Desired survivor size 1048576 bytes, new threshold 1 (max 1)
        - age   1:         72 bytes,         72 total
        : 9993K->0K(18432K), 0.0012009 secs] 18185K->9652K(38912K), 0.0012168 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        Heap
         def new generation   total 18432K, used 8356K [0x00000000fd800000, 0x00000000fec00000, 0x00000000fec00000)
          eden space 16384K,  51% used [0x00000000fd800000, 0x00000000fe0290e0, 0x00000000fe800000)
          from space 2048K,   0% used [0x00000000fe800000, 0x00000000fe800048, 0x00000000fea00000)
          to   space 2048K,   0% used [0x00000000fea00000, 0x00000000fea00000, 0x00000000fec00000)
         tenured generation   total 20480K, used 9652K [0x00000000fec00000, 0x0000000100000000, 0x0000000100000000)
           the space 20480K,  47% used [0x00000000fec00000, 0x00000000ff56d388, 0x00000000ff56d400, 0x0000000100000000)
         Metaspace       used 3318K, capacity 4496K, committed 4864K, reserved 1056768K
          class space    used 361K, capacity 388K, committed 512K, reserved 1048576K
         */
    }

    // MaxTenuringThreshold=15
    public static void testTenuringThreshold02() {

        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation2 = new byte[_1MB / 2];
        allocation3 = new byte[12 * _1MB];
        // 此处触发第一次MinorGC, allocation2被移到老年代, allocation1移到Survivor
        allocation4 = new byte[12 * _1MB];
        allocation4 = null;
        // 此处触发第二次MinorGC, MaxTenuringThreshold=15, 但是allocation1还是被移到老年代, WHY???
        // Survivor空间中相同年龄所有对象大小的总和大于Survivor空间的一半, 年龄大于等于该年龄的对象就可以直接进入老年区
        // -Xms60M -Xmx60M -Xmn30M
        allocation4 = new byte[12 * _1MB];

        /*
        [GC (Allocation Failure) [DefNew
        Desired survivor size 1572864 bytes, new threshold 15 (max 15)
        - age   1:    1283736 bytes,    1283736 total
        : 16234K->1253K(27648K), 0.0075730 secs] 16234K->13541K(58368K), 0.0076020 secs] [Times: user=0.00 sys=0.02, real=0.01 secs]
        [GC (Allocation Failure) [DefNew
        Desired survivor size 1572864 bytes, new threshold 15 (max 15)
        - age   2:    1102784 bytes,    1102784 total
        : 13541K->1076K(27648K), 0.0012171 secs] 25829K->13364K(58368K), 0.0012285 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        Heap
         def new generation   total 27648K, used 14102K [0x00000000fc400000, 0x00000000fe200000, 0x00000000fe200000)
          eden space 24576K,  53% used [0x00000000fc400000, 0x00000000fd0b86b8, 0x00000000fdc00000)
          from space 3072K,  35% used [0x00000000fdc00000, 0x00000000fdd0d3c0, 0x00000000fdf00000)
          to   space 3072K,   0% used [0x00000000fdf00000, 0x00000000fdf00000, 0x00000000fe200000)
         tenured generation   total 30720K, used 12288K [0x00000000fe200000, 0x0000000100000000, 0x0000000100000000)
           the space 30720K,  40% used [0x00000000fe200000, 0x00000000fee00010, 0x00000000fee00200, 0x0000000100000000)
         Metaspace       used 3321K, capacity 4496K, committed 4864K, reserved 1056768K
          class space    used 361K, capacity 388K, committed 512K, reserved 1048576K
         */

        /*
        [GC (Allocation Failure) [DefNew
        Desired survivor size 1048576 bytes, new threshold 1 (max 15)
        - age   1:    1649976 bytes,    1649976 total
        : 12327K->1611K(18432K), 0.0042288 secs] 12327K->9803K(38912K), 0.0042621 secs] [Times: user=0.01 sys=0.00, real=0.00 secs]
        [GC (Allocation Failure) [DefNew
        Desired survivor size 1048576 bytes, new threshold 15 (max 15)
        - age   1:        976 bytes,        976 total
        : 9967K->0K(18432K), 0.0010072 secs] 18159K->9616K(38912K), 0.0010192 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
        Heap
         def new generation   total 18432K, used 8465K [0x00000000fd800000, 0x00000000fec00000, 0x00000000fec00000)
          eden space 16384K,  51% used [0x00000000fd800000, 0x00000000fe0442c0, 0x00000000fe800000)
          from space 2048K,   0% used [0x00000000fe800000, 0x00000000fe8003d0, 0x00000000fea00000)
          to   space 2048K,   0% used [0x00000000fea00000, 0x00000000fea00000, 0x00000000fec00000)
         tenured generation   total 20480K, used 9615K [0x00000000fec00000, 0x0000000100000000, 0x0000000100000000)
           the space 20480K,  46% used [0x00000000fec00000, 0x00000000ff563f90, 0x00000000ff564000, 0x0000000100000000)
         Metaspace       used 3192K, capacity 4496K, committed 4864K, reserved 1056768K
          class space    used 349K, capacity 388K, committed 512K, reserved 1048576K
         */
    }

    public static void main(String[] args) {
        testTenuringThreshold02();
    }
}
