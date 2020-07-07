package HoldMyAppleJuice;

import java.io.*;
import java.net.*;


public class Client {

    public static String GET_URL = "http://" + DiscordSync.ip + ":" + DiscordSync.port + "/test/";
    public static String USER_AGENT = "Mozilla/5.0";

    public static WrappedClientSocket client = new WrappedClientSocket();

    public Client()
    {
        client.openSocket(DiscordSync.ip, DiscordSync.port);
    }

    public static String send(String v1, String v2)
    {
        try
        {
            URL obj = new URL(GET_URL + "?" + v1 + "=" + v2);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            InputStream inputStream = con.getInputStream();
            String encoding = con.getContentEncoding();

            InputStreamReader isReader = new InputStreamReader(inputStream);
            //Creating a BufferedReader object
            BufferedReader reader = new BufferedReader(isReader);
            StringBuilder sb = new StringBuilder();
            String str;
            while((str = reader.readLine())!= null){
                sb.append(str);
            }

            return sb.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
