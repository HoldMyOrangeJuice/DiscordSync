package HoldMyAppleJuice;

import ProtocolPackage.Protocol;
import ProtocolPackage.communication.ClientMessage;
import org.bukkit.ChatColor;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

public class ClientThread extends Thread
{

    public  InputStream dis;
    public OutputStream dos;
    public  Socket s;

    String in = null;

    // Constructor
    public ClientThread()
    {
        this.s = new Socket();
        try {
            s.connect(new InetSocketAddress("127.0.0.1", 1337));
            this.dis = s.getInputStream();
            this.dos = s.getOutputStream();
        }catch (Exception e){e.printStackTrace();}

    }



    @Override
    public void run()
    {
        while (true)
        {
            System.out.println(ChatColor.DARK_BLUE + "READING SOCKET");
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(dis));
                System.out.println("in stream " + in);
                String s = in.readLine();
                System.out.println("in line " + s);
                DiscordSync.handle(s);
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    s.close();
                    s = new Socket();
                    try {
                        sleep(1000);
                        s.connect(new InetSocketAddress("127.0.0.1", 1337));
                        this.dis = s.getInputStream();
                        this.dos = s.getOutputStream();
                        sleep(1000);
                        DiscordSync.send(new ClientMessage(Protocol.HANDSHAKE, "pl").format());

                    }catch (Exception exe){exe.printStackTrace();}
                }catch (Exception ex){ex.printStackTrace();}
            }
        }

    }

}
