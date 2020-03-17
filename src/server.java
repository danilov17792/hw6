import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {
    private static Object String;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        DataInputStream in;
        DataOutputStream out;

        try {
            serverSocket = new ServerSocket(14089);
            System.out.println("Сервер работает");

            socket = serverSocket.accept();
            System.out.println("Клиент подключился");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            System.out.println("Клиент вышел");
                            break;
                        }
                        System.out.println("Клиент:" + str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            ran(out);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    static void ran(DataOutputStream out) throws IOException {
        while (true){
            String str;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
            str=br.readLine();
            out.writeUTF(str);
        }
    }
}
