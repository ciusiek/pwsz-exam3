package pl.labno.bernard;

public class Terminal {

    private String errorMessage;

    private Connection connection;

    public Terminal(Connection connection)
    {
        this.connection = connection;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public String sendLine(String line)
    {
        if (null == line) {
            throw new IllegalArgumentException("line param must not be null");
        }
        if (connection.isConnected()) {
            try {
                return connection.sendLine(line);
            } catch (UnknownCommandException exception) {
                this.errorMessage = "This command is unknown";
                throw new IllegalStateException("Unknown command", exception);
            }
        } else {
            this.errorMessage = "Terminal is not connected";
            throw new IllegalStateException("Not connected");
        }
    }
}
