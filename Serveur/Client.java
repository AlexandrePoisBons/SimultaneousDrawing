package Serveur;

import metier.Controleur;
import metier.Forme;

public class Client
{
    
    private ClientToServerSocket clientToServerSocket;

    public Client(Controleur ctrl)
    {
        this.clientToServerSocket = new ClientToServerSocket(ctrl);
    }

    public void sendForme(Forme form)
    {
        this.clientToServerSocket.sendForme(form);
    }

    public void majForme(Forme form)
    {
        this.clientToServerSocket.majForme(form);
    }

    public void removeFrome(Forme form)
    {
        this.clientToServerSocket.removeForme(form);
    }

    public void unRemoveForme(Forme form)
    {
        this.clientToServerSocket.unRemvoeForme(form);
    }

    public void sendClear()
    {
        this.clientToServerSocket.sendClear();
    }

    public Boolean Connect(String ip, int port)
    {

        if (this.clientToServerSocket.isAlive())
        {
            this.clientToServerSocket.Disconnect();
        }

        Boolean success = this.clientToServerSocket.Connect(ip, port);


        if (success)
        {
            this.clientToServerSocket.start();
            this.clientToServerSocket.requestDrawing();

        }

        return success;

    }




}