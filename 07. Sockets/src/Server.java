import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    int port;
    ServerSocket server=null;
    Socket client=null;
    ExecutorService pool = null;
    int clientcount=0;
    private static ArrayList<ServerThread> clients = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        Server serverobj=new Server(5000);
        serverobj.startServer();
    }

    Server(int port){
        this.port=port;
        pool = Executors.newFixedThreadPool(5);
    }

    public void startServer() throws IOException {

        server=new ServerSocket(5000);
        System.out.println("Server Booted");
        System.out.println("Any client can stop the server by sending -1");
        while(true)
        {
            client=server.accept();
            clientcount++;
            ServerThread runnable= new ServerThread(client,clientcount,this,clients);
            clients.add(runnable);
            pool.execute(runnable);
        }

    }

    private static class ServerThread implements Runnable {

        Server server=null;
        Socket client=null;
        BufferedReader cin;
        PrintStream cout;
        Scanner sc=new Scanner(System.in);
        public int id;
        String s;
        String clientMessage;
        private static ArrayList<ServerThread> clients;

        private void outToAll(String clientMessage) {

            for(ServerThread client : clients){
                client.cout.println(clientMessage);
            }

        }

        ServerThread(Socket client, int count, Server server, ArrayList<ServerThread> clients) throws IOException {
            ServerThread.clients = clients;
            this.client=client;
            this.server=server;
            this.id=count;
            System.out.println("Connection "+id+"established with client "+client);

            cin=new BufferedReader(new InputStreamReader(client.getInputStream()));
            cout=new PrintStream(client.getOutputStream());

        }

        @Override
        public void run() {
            int x=1;
            try{
                while(true){
                    clientMessage=cin.readLine();
                    String d = "Client("+id+") :"+clientMessage+"\n";
                    System.out.print(d);
                    System.out.print("Server : ");
                    //s=stdin.readLine();
                    s=sc.nextLine();
                    if (s.equalsIgnoreCase("bye"))
                    {
                        cout.println("BYE");
                        x=0;
                        System.out.println("Connection ended by server");
                        break;
                    }
                    cout.println(s);
                    if(clientMessage != null){
                        outToAll(clientMessage);
                    }


                }

                cin.close();
                client.close();
                cout.close();
                if(x==0) {
                    System.out.println( "Server cleaning up." );
                    System.exit(0);
                }

            }
            catch(IOException ex){
                System.out.println("Error : "+ex);
            }


        }

    }

}