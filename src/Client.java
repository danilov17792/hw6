import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket;
        DataInputStream in;
        DataOutputStream out;
        final String IP_ADRESS = "localhost";
        final int PORT = 14089;

        try {
            socket = new Socket(IP_ADRESS, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            System.out.println("Сервер закрыт");
                            break;
                        }
                        System.out.println("Сервер:" + str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            server.ran(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
