import java.io.*;
import java.net.Socket;

public class ServerService extends Thread {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private Story story;

    public ServerService(Socket socket) throws IOException {
        this.socket = socket;

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        story = new Story();
        story.print(out);

        start();
    }

    @Override
    public void run() {
        String message;
        try {
            try {
                while(true) {
                    message = in.readLine();
                    if (message.equals("stop")) break;
                    System.out.println(message);
                    for (ServerService ss: Server.serverServices) {
                        send(message);
                    }
                    story.add(message);
                }

            } finally {
                in.close();
                out.close();
            }
        } catch (IOException ignore) {
            System.err.println("Сервис не робит ");
        }
    }

    private void send(String message) {
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException ignore) {}
    }
}
