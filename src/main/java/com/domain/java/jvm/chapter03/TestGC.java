package com.domain.java.jvm.chapter03;

/**
 * VM Args: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * -XX:+UseSerialGC -XX:PretenureSizeThreshold=3145728
 * @author Mark Li
 * @version 1.0.0
 * @since 2017/9/15
 */
public class TestGC {

    private static final int _1MB = 1024 * 1024;

    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
//        allocation2 = new byte[2 * _1MB];
//        allocation3 = new byte[2 * _1MB];
//        allocation4 = new byte[1 * _1MB];
    }

    public static void main(String[] args) {
        testAllocation();
    }

    /*
    [GC (Allocation Failure) [PSYoungGen: 7458K->1016K(9216K)] 7458K->5332K(19456K), 0.0028825 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    Heap
     PSYoungGen      total 9216K, used 7398K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
      eden space 8192K, 77% used [0x00000000ff600000,0x00000000ffc3bb68,0x00000000ffe00000)
      from space 1024K, 99% used [0x00000000ffe00000,0x00000000ffefe010,0x00000000fff00000)
      to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
     ParOldGen       total 10240K, used 4316K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
      object space 10240K, 42% used [0x00000000fec00000,0x00000000ff037120,0x00000000ff600000)
     Metaspace       used 3325K, capacity 4496K, committed 4864K, reserved 1056768K
      class space    used 361K, capacity 388K, committed 512K, reserved 1048576K
    */

    /*
    [GC (Allocation Failure) [DefNew: 7458K->1024K(9216K), 0.0036955 secs] 7458K->5221K(19456K), 0.0037276 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
    Heap
     def new generation   total 9216K, used 7488K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
      eden space 8192K,  78% used [0x00000000fec00000, 0x00000000ff250328, 0x00000000ff400000)
      from space 1024K, 100% used [0x00000000ff500000, 0x00000000ff600000, 0x00000000ff600000)
      to   space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
     tenured generation   total 10240K, used 4197K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
       the space 10240K,  40% used [0x00000000ff600000, 0x00000000ffa19548, 0x00000000ffa19600, 0x0000000100000000)
     Metaspace       used 3319K, capacity 4496K, committed 4864K, reserved 1056768K
      class space    used 361K, capacity 388K, committed 512K, reserved 1048576K
    */
}
