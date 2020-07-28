import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Server {


    static void addToTreeFromDataBase(DensityTree densityTree, DataBaseConnectionHandler dbch) {
        ArrayList<Crime> crimes = new ArrayList<>();
        crimes = dbch.createConnection();
        for (int i = 0; i < crimes.size(); i++) {
            densityTree.addCrime(crimes.get(i));
//            System.out.println(crimes.get(i));
        }

    }

    static void preProcess(DensityTree densityTree, DataBaseConnectionHandler dbch) {
        /** crimes should be load from file and add to densityTrr here **/

        addToTreeFromDataBase(densityTree, dbch);
    }


    static void mapDataConnection(DensityTree densityTree, Formatter formatter, Scanner scanner, String[] subs) {
        Point center = new Point(Double.parseDouble(subs[2]), Double.parseDouble(subs[3]));
        switch (subs[1]) {
            case "1":
                formatter.format("1=" + densityTree.toClientStringOuter() + "\n");
//                System.out.println("1=" +densityTree.toClientStringOuter() + "\n");
                break;
            case "2":
                formatter.format("2=" + densityTree.toClientStringInner());
//                System.out.println("2=" + densityTree.toClientStringInner());
                break;
            case "3":
                DensityTree temp = densityTree.findExactChild(center);
                if (temp == null)
                    formatter.format("2=" + densityTree.toClientStringInner());
                else
                    formatter.format("3=" + temp.toClientStringInner());
                break;
            case "4":
                DensityTree temp1 = densityTree.findExactChild(center);
                DensityTree temp2;
                if (temp1 != null) {
                    temp2 = temp1.findExactChild(center);
                    if (temp2 == null)
                        formatter.format("3=" + temp1.toClientStringInner());
                    else
                        formatter.format("4=" + densityTree.findExactChild(center).findExactChild(center).toClientStringInner());
                } else {
                    formatter.format("2=" + densityTree.toClientStringInner());
                }

//                formatter.format("4=" + densityTree.findExactChild(center).findExactChild(center).toClientStringInner());
//                System.out.println("4=" + densityTree.findExactChild(center).findExactChild(center).toClientStringInner());
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
        DataBaseConnectionHandler dbch = new DataBaseConnectionHandler();
        preProcess(crimeTree, dbch);

//        Point center = new Point(51.405168771743774 ,35.702853049009626);
//        crimeTree.findExactChild(center);

//        System.out.println("4=" + crimeTree.findExactChild(center).findExactChild(center).toClientStringInner());


        System.out.println(crimeTree);
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

                switch (subs[0]) {
                    case "map":
                        mapDataConnection(crimeTree, formatter, read, subs);
                }
                addToTreeFromDataBase(crimeTree, dbch);
            } catch (IOException e) {

            }
        }
    }
}
