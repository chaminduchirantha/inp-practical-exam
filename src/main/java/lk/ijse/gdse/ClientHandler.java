package lk.ijse.gdse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public ClientHandler(Socket socket) {
            String Massage = "";
            try {
                this.dataInputStream = new DataInputStream(socket.getInputStream());
                this.dataOutputStream = new DataOutputStream(socket.getOutputStream());

                dataOutputStream.writeUTF("Enter your Massage: ");
                String massage = dataInputStream.readUTF();
                System.out.println("Massage: " + massage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            String massage ;
            while (true) {
                try {
                    massage = dataInputStream.readUTF();
                    if (massage.equals("bye")) {
                        break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
}

