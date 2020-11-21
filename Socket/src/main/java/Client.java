import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client{
    private static ClientService client;
    private static final int PORT = 4004;
    private static final String address = "localhost";

    public static void main(String[] args) {
            client = new ClientService(address, PORT);
    }
}
