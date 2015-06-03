package pl.labno.bernard;

public interface Connection {

    boolean isConnected();

    String sendLine(String line);
}
