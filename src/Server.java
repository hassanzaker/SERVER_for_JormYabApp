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

    static void mapDataConnection(DensityTree densityTree, Formatter formatter, Scanner scanner, String[] subs){
        Point center = new Point(Double.parseDouble(subs[2]), Double.parseDouble(subs[3]));
        switch (subs[1]){
            case "1":
                formatter.format("1=" +densityTree.toClientStringOuter() + "\n");
                System.out.println("1=" +densityTree.toClientStringOuter() + "\n");
                break;
            case "2":
                formatter.format("2=" + densityTree.toClientStringInner());
                System.out.println("2=" + densityTree.toClientStringInner());
                break;
            case "3":
                formatter.format("3=" + densityTree.findExactChild(center).toClientStringInner());
                break;
            case "4":
                formatter.format("4=" + densityTree.findExactChild(center).findExactChild(center).toClientStringInner());
                break;

            default:
        }
        formatter.flush();
    }

    public static void main(String[] args) {
        Square tehran = new Square(new Point(51.210794, 35.824546),
                new Point(51.601581, 35.824546),
                new Point(51.210794, 35.570983),
                new Point(51.601581, 35.570983));
        DensityTree crimeTree = new DensityTree(tehran, 2);
        preProcess(crimeTree);


        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(7800);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("connected!");
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                Formatter formatter = new Formatter(outputStream);
                Scanner read = new Scanner(inputStream);

                String s = read.nextLine();
                String[] subs = s.split(" ");
                System.out.println(s);
                /** s is for service type that client ask server **/

                switch (subs[0]){
                    case "map":
                        mapDataConnection(crimeTree, formatter, read, subs);
                }

            } catch (IOException e) {

            }
        }
    }
}
