import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ConsumerClient {
    public static void main(String[] args) {
        if (login()) {
            try {
                Registry registry = LocateRegistry.getRegistry("localhost", 1098);
                RadioServer server = (RadioServer) registry.lookup("RadioServer");
                consumeSong(server);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Exiting the consumer client.");
        }
    }

    public static boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        String validUsername = "consumer";
        String validPassword = "password";

        if (username.equals(validUsername) && password.equals(validPassword)) {
            System.out.println("Authentication successful!");
            return true;
        } else {
            System.out.println("Authentication failed!");
            return false;
        }
    }

    public static void consumeSong(RadioServer server) {
        try {
            // Prompt the user to enter the song title
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter song title to consume: ");
            String title = scanner.nextLine();

            // Call the server method to retrieve the song binary data
            byte[] binaryData = server.getBinarySong(title);

            if (binaryData != null) {
                // Convert the binary data to base64 for display (Optional)
                String base64Data = Base64.getEncoder().encodeToString(binaryData);
                System.out.println("Song found. Base64 Encoded Data: " + base64Data);
            } else {
                System.out.println("Song not found.");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void downloadSong(RadioServer server) {
        try {
            // Prompt the user to enter the song title
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter song title to download: ");
            String title = scanner.nextLine();
            
            // Get the binary song data from the server
            byte[] songData = server.getBinarySong(title);

            if (songData != null) {
                // Save the binary data to a file
                try (FileOutputStream fos = new FileOutputStream(title + ".mp3")) {
                    fos.write(songData);
                }
                System.out.println("Song downloaded successfully!");
            } else {
                System.out.println("Song not found.");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
             e.printStackTrace();
        }
    }
}
