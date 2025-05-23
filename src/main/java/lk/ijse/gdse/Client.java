package lk.ijse.gdse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Thread thread = new Thread(() -> {
            Socket socket;
            DataInputStream dataInputStream;
            DataOutputStream dataOutputStream;

            try {
                socket = new Socket("localhost", 5000);

                String serverMassage = "";
                do {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Client Massage : ");
                    String clientMassage = scanner.nextLine();

                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataOutputStream.writeUTF(clientMassage);
                    dataOutputStream.flush();

                    dataInputStream = new DataInputStream(socket.getInputStream());
                    serverMassage = dataInputStream.readUTF();
                    System.out.println("Server Massage: " + serverMassage);


                    Thread.sleep(5000);

                } while (!serverMassage.equals("bye"));
                System.out.println("Ended to client");

                socket.close();
            } catch (Exception e) {
                e.printStackTrace();

            }

        });
        thread.start();
    }
}
