import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MovieClient {
    static final String SERVER_HOST = "localhost";
    static final int SERVER_PORT = 5000;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.println("===== MOVIE SEARCH CLIENT =====");
            
            while (true) {
                System.out.print("\nEnter search term (or 'exit' to quit): ");
                String searchTerm = scanner.nextLine();
                
                if (searchTerm.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting program...");
                    break;
                }
                
                String result = sendSearchRequest(searchTerm);
                System.out.println("\nSearch Results: " + result);
            }
            
        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    static String sendSearchRequest(String searchTerm) {
        try {
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            out.println(searchTerm);
            System.out.println("Search request sent to server");
            
            String response = in.readLine();
            
            socket.close();
            return response;
            
        } catch (UnknownHostException e) {
            return "Error: Cannot find host " + SERVER_HOST;
        } catch (IOException e) {
            return "Error communicating with server: " + e.getMessage();
        }
    }
}