import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class Server {

    static void preProcess(DensityTree densityTree){
        /** crimes should be load from file and add to densityTrr here **/
    }

    static void mapDataConnection(Formatter formatter, Scanner scanner){

    }

    public static void main(String[] args) {
        Square tehran = new Square(new Point(51.210794, 35.824546),
                new Point(51.601581, 35.824546),
                new Point(51.210794, 35.570983),
                new Point(51.601581, 35.570983));
        DensityTree crimeTree = new DensityTree(tehran, 2);
        preProcess(crimeTree);


        ServerSocket serverSocket = null;
        while (true) {
            try {
                serverSocket = new ServerSocket(7800);
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                Formatter formatter = new Formatter(outputStream);
                Scanner read = new Scanner(inputStream);

                String s = read.nextLine();

                /** s is for service type that client ask server **/

                switch (s){
                    case "map":
                        mapDataConnection(formatter, read);
                }

                System.out.println("client successfully connected!");
            } catch (IOException e) {

            }
        }
    }
}
