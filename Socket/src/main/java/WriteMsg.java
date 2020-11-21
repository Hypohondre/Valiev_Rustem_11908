import java.io.*;
import java.net.Socket;

public class WriteMsg extends Thread implements Closeable {
    private BufferedWriter out;
    private BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    public WriteMsg(Socket socket) throws IOException {
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        String message;
        while (true) {
            try {
                message = console.readLine();

                if (message.equals("stop")) {
                    send(message);
                    break;
                }

                send(message);
            } catch (IOException ignore) {
                System.err.println("сообщения не записываются");
            }
        }
    }

    @Override
    public void close() throws IOException {
        out.close();
        console.close();
    }

    private void send(String msg) throws IOException {
        out.write(msg + "\n");
        out.flush();
    }
}
