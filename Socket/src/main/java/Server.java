import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    public static LinkedList<ServerService> serverServices = new LinkedList<>();

    private static final int PORT = 4004;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Сервер открыт");

        try {

            while (true) {
                Socket socket = server.accept();
                try {
                    serverServices.add(new ServerService(socket));
                } catch (IOException e) {
                    socket.close();
                    System.err.println("Ошибка на сервере");
                }
            }
        } finally {
            server.close();
            System.out.println("Сервер закрыт");
        }
    }
}
