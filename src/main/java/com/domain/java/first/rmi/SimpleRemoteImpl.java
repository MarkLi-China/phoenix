package com.domain.java.first.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-8-10
 */
public class SimpleRemoteImpl extends UnicastRemoteObject implements SimpleRemote {

    protected SimpleRemoteImpl() throws RemoteException {

    }

    @Override
    public String sayHello() throws RemoteException {

        return "Server says, 'hey'";
    }

    public static void main(String[] args) {

        try {
            SimpleRemote service = new SimpleRemoteImpl();
            Naming.rebind("RemoteHello", service);
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
