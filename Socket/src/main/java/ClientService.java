import java.io.*;
import java.net.Socket;

public class ClientService  {
    private Socket socket;
    private ReadMsg read;
    private WriteMsg write;
    private String address;
    private int port;
    private String name;

    public ClientService(String address, int port) {
        this.address = address;
        this.port = port;
        try {
            socket = new Socket(address, port);
            System.out.println("Клиент запущен!");
            login();
        } catch (IOException e) {
            System.err.println("Не удалось создать клиента");
        }
        try {
            read = new ReadMsg(socket);
            write = new WriteMsg(socket);
        } catch (IOException e) {
            this.close();
        }
    }

    private void login() throws IOException {
        System.out.println("Введите свое имя");
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        name = console.readLine();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("Добро пожаловать, " + name + "!");
        out.flush();
        console.close();
        out.close();
    }

    private void close() {
        try {
            socket.close();
            read.close();
            write.close();
        } catch (IOException ignore) {}
    }
}