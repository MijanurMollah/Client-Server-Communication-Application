import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    
    int port;
    String hostAddr;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void iPAddress() throws UnknownHostException {
        hostAddr = InetAddress.getLocalHost().getHostAddress();
    }

    public void assignPortNumber(int portNo) {
        port = portNo;
    }

    public void startConnection() throws UnknownHostException, IOException {
        clientSocket = new Socket(hostAddr, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void sendMessage() throws IOException {
        Scanner obj = new Scanner(System.in);
        System.out.print("Client: ");
        String msg = obj.nextLine();
        out.println(msg);
        if (msg.equals("quit"))
        System.exit(0);
        else
        receiveMessage();
    }

    public void receiveMessage() throws IOException {
        String rec = in.readLine();
        System.out.println("Server: " + rec);
        if (rec.equals("quit"))
        System.exit(0);
        else
        sendMessage();
    }

    public static void main(String args[]) throws UnknownHostException, IOException {
        Client client = new Client();
        Scanner obj = new Scanner(System.in);
        System.out.print("What is the server port number: ");
        int port = Integer.parseInt(obj.nextLine());
        client.assignPortNumber(port);
        client.iPAddress();
        client.startConnection();
        client.sendMessage();
    }
}