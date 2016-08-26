package com.domain.java.test;

import java.io.IOException;
import java.net.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

/**
 * com.domain.java.test
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/8/26
 */
public class NtpClient2 {

    private static final String NTP_SERVER_ADDRESS = "time.nist.gov";

    private static final int NTP_PORT = 123;

    private static DatagramSocket client = null;

    private static DatagramSocket connect() throws SocketException {

        if (client != null && client.isConnected()) return client;
        client = new DatagramSocket();
        SocketAddress address = new InetSocketAddress(NTP_SERVER_ADDRESS, NTP_PORT);
        client.connect(address);
        return client;
    }

    private static void releaseConnection() {

        if (client != null && client.isConnected()) client.close();
    }

    public static void main(String[] args) {

        try {
            connect();
            Frame frame = new Frame();
            frame.setVN(3);//ntp版本号
            frame.setMode(3);//客户端模式
            frame.setStratum(16);
            frame.setPoll(1);
            frame.setPrecision(1);

            LocalDateTime localTime = LocalDateTime.now();
            LocalDateTime startTime = LocalDateTime.of(1900, 01, 01, 0, 0, 0, 0);
            long start = startTime.toInstant(ZoneOffset.UTC).toEpochMilli();
            long end = localTime.toInstant(ZoneOffset.UTC).toEpochMilli();

            //frame.setOriginTimeStamp(((end - start) / 1000l) * (1l << 32l));
            DatagramPacket data = new DatagramPacket(frame.data, frame.data.length);
            client.send(data);
            Frame response = new Frame();
            DatagramPacket rec = new DatagramPacket(response.data, response.data.length);
            client.receive(rec);
            System.out.println(response.getRecvTimeStamp());
            System.out.println(response.getMode());
            System.out.println(response.getRootDelay());
            System.out.println(response.getTransTimeStamp());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            releaseConnection();
        }
    }

    static class Frame {

        public byte[] data = new byte[60];

        public void setLi(int li) {

            data[0] = (byte) ((data[0] & 0x3F) | (((byte) li) << 6));//高两位至0
        }

        public int getLi() {

            return Integer.valueOf((data[0] >>> 6) & 0x03);//高两位至0
        }

        public void setVN(int vn) {

            data[0] = (byte) ((data[0] & 0xC7) | (((byte) vn) << 3));
        }

        public int getVN() {

            return Integer.valueOf((data[0] >>> 3) & 0x07);
        }

        public void setMode(int mode) {

            data[0] = (byte) ((data[0] & 0xF8) | ((byte) mode));
        }

        public int getMode() {

            return Integer.valueOf((byte) ((data[0] & 0x07)));
        }

        public void setStratum(int stratum) {

            data[1] = (byte) stratum;
        }

        public int getStratum() {

            return Integer.valueOf(data[1]);
        }

        public void setPoll(int poll) {

            data[2] = (byte) poll;
        }

        public int getPoll() {

            return Integer.valueOf(data[2]);
        }

        public void setPrecision(int Precision) {

            data[3] = (byte) Precision;
        }

        public int getPrecision() {

            return Integer.valueOf(data[3]);
        }

        public void setRootDelay(int rootDelay) {

            data[4] = (byte) (rootDelay >>> 24);
            data[5] = (byte) ((rootDelay & 0x00FF0000) >>> 16);
            data[6] = (byte) ((rootDelay & 0x0000FF00) >>> 8);
            data[7] = (byte) ((rootDelay & 0x000000FF));
        }

        public int getRootDelay() {

            return Byte.toUnsignedInt(data[4]) * (1 << 24)
                    + Byte.toUnsignedInt(data[5]) * (1 << 16)
                    + Byte.toUnsignedInt(data[6]) * (1 << 8)
                    + Byte.toUnsignedInt(data[7]) * (1 << 0);
        }

        public void setRootDesc(int rootDesc) {

            data[8] = (byte) (rootDesc >>> 24);
            data[9] = (byte) ((rootDesc & 0x00FF0000) >>> 16);
            data[10] = (byte) ((rootDesc & 0x0000FF00) >>> 8);
            data[11] = (byte) ((rootDesc & 0x000000FF));
        }

        public int getRootDesc() {

            return Byte.toUnsignedInt(data[8]) * (1 << 24)
                    + Byte.toUnsignedInt(data[9]) * (1 << 16)
                    + Byte.toUnsignedInt(data[10]) * (1 << 8)
                    + Byte.toUnsignedInt(data[11]) * (1 << 0);
        }

        public void setRefTimeStamp(long timeStamp) {

            data[12] = (byte) (timeStamp >>> 56);
            data[13] = (byte) ((timeStamp & 0x00FF000000000000l) >>> 48);
            data[14] = (byte) ((timeStamp & 0x0000FF0000000000l) >>> 40);
            data[15] = (byte) ((timeStamp & 0x000000FF00000000l) >>> 32);
            data[16] = (byte) ((timeStamp & 0x00000000FF000000l) >>> 24);
            data[17] = (byte) ((timeStamp & 0x0000000000FF0000l) >>> 16);
            data[18] = (byte) ((timeStamp & 0x000000000000FF00l) >>> 8);
            data[19] = (byte) ((timeStamp & 0x00000000000000FFl) >>> 0);
        }

        public long getRefTimeStamp() {

            return Byte.toUnsignedLong(data[12]) * (1 << 56)
                    + Byte.toUnsignedLong(data[13]) * (1 << 48)
                    + Byte.toUnsignedLong(data[14]) * (1 << 40)
                    + Byte.toUnsignedLong(data[15]) * (1 << 32)
                    + Byte.toUnsignedLong(data[16]) * (1 << 24)
                    + Byte.toUnsignedLong(data[17]) * (1 << 16)
                    + Byte.toUnsignedLong(data[18]) * (1 << 8)
                    + Byte.toUnsignedLong(data[19]) * (1 << 0);
        }

        public void setOriginTimeStamp(long timeStamp) {

            data[20] = (byte) (timeStamp >>> 56);
            data[21] = (byte) ((timeStamp & 0x00FF000000000000l) >>> 48);
            data[22] = (byte) ((timeStamp & 0x0000FF0000000000l) >>> 40);
            data[23] = (byte) ((timeStamp & 0x000000FF00000000l) >>> 32);
            data[24] = (byte) ((timeStamp & 0x00000000FF000000l) >>> 24);
            data[25] = (byte) ((timeStamp & 0x0000000000FF0000l) >>> 16);
            data[26] = (byte) ((timeStamp & 0x000000000000FF00l) >>> 8);
            data[27] = (byte) ((timeStamp & 0x00000000000000FFl) >>> 0);
        }

        public long getOriginTimeStamp() {

            return Byte.toUnsignedLong(data[20]) * (1 << 56)
                    + Byte.toUnsignedLong(data[21]) * (1 << 48)
                    + Byte.toUnsignedLong(data[22]) * (1 << 40)
                    + Byte.toUnsignedLong(data[23]) * (1 << 32)
                    + Byte.toUnsignedLong(data[24]) * (1 << 24)
                    + Byte.toUnsignedLong(data[25]) * (1 << 16)
                    + Byte.toUnsignedLong(data[26]) * (1 << 8)
                    + Byte.toUnsignedLong(data[27]) * (1 << 0);
        }

        public void setRecvTimeStamp(long timeStamp) {

            data[28] = (byte) (timeStamp >>> 56);
            data[29] = (byte) ((timeStamp & 0x00FF000000000000l) >>> 48);
            data[30] = (byte) ((timeStamp & 0x0000FF0000000000l) >>> 40);
            data[31] = (byte) ((timeStamp & 0x000000FF00000000l) >>> 32);
            data[32] = (byte) ((timeStamp & 0x00000000FF000000l) >>> 24);
            data[33] = (byte) ((timeStamp & 0x0000000000FF0000l) >>> 16);
            data[34] = (byte) ((timeStamp & 0x000000000000FF00l) >>> 8);
            data[35] = (byte) ((timeStamp & 0x00000000000000FFl) >>> 0);
        }

        public long getRecvTimeStamp() {

            return Byte.toUnsignedLong(data[28]) * (1 << 56)
                    + Byte.toUnsignedLong(data[29]) * (1 << 48)
                    + Byte.toUnsignedLong(data[30]) * (1 << 40)
                    + Byte.toUnsignedLong(data[31]) * (1 << 32)
                    + Byte.toUnsignedLong(data[32]) * (1 << 24)
                    + Byte.toUnsignedLong(data[33]) * (1 << 16)
                    + Byte.toUnsignedLong(data[34]) * (1 << 8)
                    + Byte.toUnsignedLong(data[35]) * (1 << 0);
        }

        public void setTransTimeStamp(long timeStamp) {

            data[36] = (byte) (timeStamp >>> 56);
            data[37] = (byte) ((timeStamp & 0x00FF000000000000l) >>> 48);
            data[38] = (byte) ((timeStamp & 0x0000FF0000000000l) >>> 40);
            data[39] = (byte) ((timeStamp & 0x000000FF00000000l) >>> 32);
            data[40] = (byte) ((timeStamp & 0x00000000FF000000l) >>> 24);
            data[41] = (byte) ((timeStamp & 0x0000000000FF0000l) >>> 16);
            data[42] = (byte) ((timeStamp & 0x000000000000FF00l) >>> 8);
            data[43] = (byte) ((timeStamp & 0x00000000000000FFl) >>> 0);
        }

        public long getTransTimeStamp() {

            return Byte.toUnsignedLong(data[36]) * (1 << 56)
                    + Byte.toUnsignedLong(data[37]) * (1 << 48)
                    + Byte.toUnsignedLong(data[38]) * (1 << 40)
                    + Byte.toUnsignedLong(data[39]) * (1 << 32)
                    + Byte.toUnsignedLong(data[40]) * (1 << 24)
                    + Byte.toUnsignedLong(data[41]) * (1 << 16)
                    + Byte.toUnsignedLong(data[42]) * (1 << 8)
                    + Byte.toUnsignedLong(data[43]) * (1 << 0);
        }

        @Override
        public String toString() {

            return "Frame{" +
                    "data=" + Arrays.toString(data) +
                    '}';
        }
    }
}

