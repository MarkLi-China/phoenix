package com.domain.java.first.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-8-10
 */
public interface SimpleRemote extends Remote {

    public String sayHello() throws RemoteException;
}
