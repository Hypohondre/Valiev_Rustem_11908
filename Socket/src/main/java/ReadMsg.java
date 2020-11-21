import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadMsg extends Thread implements Closeable {

    private BufferedReader in;

    public ReadMsg(Socket socket) throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        start();
    }

    @Override
    public void run() {
        String message;

        try {
            while(true) {
                message = in.readLine();
                if (message.equals("stop")) break;
                System.out.println(message);
            }
        } catch (IOException e) {
            System.err.println("сообщения не читаются");
        }
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}
