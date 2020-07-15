import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        while (true) {
            try {
                serverSocket = new ServerSocket(7800);
                Socket socket = serverSocket.accept();
                System.out.println("client successfully connected!");
            } catch (IOException e) {

            }
        }
    }
}
