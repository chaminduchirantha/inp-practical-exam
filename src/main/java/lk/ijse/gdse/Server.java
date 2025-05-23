package lk.ijse.gdse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {

        Socket socket;
        ServerSocket serverSocket;
        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;
        List<ClientHandler> clientHandlers = new ArrayList<>();


        try {
            System.out.println("Server started");
            serverSocket = new ServerSocket(5000);
            socket = serverSocket.accept();
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            String clientMassage = "";
            do {
                clientMassage = dataInputStream.readUTF();
                System.out.println("client Massage : " + clientMassage);

                Scanner scanner = new Scanner(System.in);
                System.out.print("server massage-> : ");
                String serverMassage = scanner.nextLine();

                dataOutputStream.writeUTF(serverMassage);
                dataOutputStream.flush();

            } while (!clientMassage.equals("bye"));
            System.out.println("Ended To Server");
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
