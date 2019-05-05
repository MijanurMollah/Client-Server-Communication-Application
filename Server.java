import java.net.*;
import java.io.*;
import java.util.Scanner;

class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    int port;
    private PrintWriter out;
    private BufferedReader in;

    public void AssignPortNumber(int portNo) {
        port = portNo;
    }

    public void start() throws Exception{
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));       
    }

    public void receiveMessage() throws IOException {
        String resp = in.readLine();
        System.out.println("Client: " + resp);
        if (resp.equals("quit"))
        System.exit(0);
        else
        sendMessage();
    }

    public void sendMessage() throws IOException {
        System.out.print("Server: ");
        Scanner obj = new Scanner(System.in);
        String msg = obj.nextLine();
        out.println(msg);
        if (msg.equals("quit"))
        System.exit(0);
        else
        receiveMessage();
    }

    public static void main(String args[]) throws SocketException, IOException, Exception {
        Server server = new Server();
        Scanner obj = new Scanner(System.in);
        System.out.print("What is your desired port number: ");
        int port = Integer.parseInt(obj.nextLine());
        server.AssignPortNumber(port);
        server.start();
        server.receiveMessage();
    }
}