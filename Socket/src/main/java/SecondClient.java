import java.io.*;
import java.net.Socket;

public class SecondClient {
    private static Socket client;
    private static BufferedReader console;
    private static BufferedWriter out;
    private static BufferedReader in;

    private static final int PORT = 4004;

    public static void main(String[] args) {
        try {
            try {
                System.out.println("Клиент запускается");

                client = new Socket("localhost", PORT);
                console = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

                System.out.println("Клиент открыт");

                System.out.println("Введите ваше сообщение:");
                String message = console.readLine();

                System.out.println("Вы ввели: " + message);
                out.write(message + "\n");
                out.flush();

                String serverAnswer = in.readLine();
                System.out.println("Ответ от сервера: " + serverAnswer);
            } finally {
                client.close();
                in.close();
                out.close();
                console.close();
                System.out.println("Клиент закрыт!");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
