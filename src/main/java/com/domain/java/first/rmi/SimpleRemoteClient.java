package com.domain.java.first.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-8-10
 */
public class SimpleRemoteClient {

    public static void main(String[] args) {

        new SimpleRemoteClient().go();
    }

    private void go() {

        try {
            SimpleRemote service = (SimpleRemote) Naming.lookup("rmi://127.0.0.1/RemoteHello");
            String s = service.sayHello();
            System.out.println(s);
        } catch (NotBoundException | RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
