package Serveur;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import metier.*;

public class ServerThread extends Thread
{

    private ServerSocket serverSocket;
    private ArrayList<ServerToClientSocket> serverToClientSockets;
    private Controleur ctrl;

    public ServerThread(Controleur ctrl,int port )
    {
        this.ctrl = ctrl;
        try
        {
            this.serverSocket = new ServerSocket(port);
            this.serverToClientSockets = new ArrayList<ServerToClientSocket>();
        }
        catch (Exception e)
        {
            System.err.println("Impossible de créer le serveur");
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        while (true)
        {
            // Accept a new client
            try
            {
                System.out.println("En attente d'un nouveau client");
                Socket socket = this.serverSocket.accept();
                ServerToClientSocket serverToClientSocket = new ServerToClientSocket(this, socket);
                this.serverToClientSockets.add(serverToClientSocket);
                serverToClientSocket.start();
            }
            catch (Exception e)
            {
                System.err.println("Impossible d'accepter un nouveau client");
                e.printStackTrace();
            }
        }
    }

    public void broadcastForme(Forme form)
    {
        for (ServerToClientSocket serverToClientSocket : this.serverToClientSockets)
        {
            serverToClientSocket.sendForme(form);
        }
    }

    public void broadcastRemoveForme(Forme forme)
    {
        for (ServerToClientSocket serverToClientSocket : this.serverToClientSockets)
        {
            serverToClientSocket.removeForme(forme);
        }
    }


    public Controleur getCtrl()
    {
        return this.ctrl;
    }

}