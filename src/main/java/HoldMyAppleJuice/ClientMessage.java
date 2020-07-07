package HoldMyAppleJuice;

import ProtocolPackage.Splitter;

public class ClientMessage
{
    String header;
    String[] values;

    public ClientMessage(String header, String... values)
    {
        this.header = header;
        this.values = values;
    }

    public String format()
    {
        String out = header + Splitter.HEADER.toString();
        for (String s : values)
        {
            out += s + Splitter.VALUE_SPLITTER.toString();
        }
        return out;
    }
}
