import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Story {
    private LinkedList<String> story;

    public void add(String msg) {
        if (story.size() >= 10) {
            story.removeFirst();
            story.add(msg);
        } else {
            story.add(msg);
        }
    }

    public void print(BufferedWriter writer) {
        if (story.size() > 0) {
            try {
                writer.write("Последние 10 сообщений:" + "\n");
                for (String str : story) {
                    writer.write(str + "\n");
                }
                writer.flush();
            } catch (IOException e) {
                System.err.println("Ошибка при выводе истории переписки");
            }
        }
    }
}
