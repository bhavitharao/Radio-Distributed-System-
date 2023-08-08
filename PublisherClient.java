import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class PublisherClient {
    public static void main(String[] args) {
        if (login()) {
            RadioServer server = connectToServer();
            addSong(server);
        } else {
            System.out.println("Exiting the publisher client.");
        }
    }

    public static boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        
        String validUsername = "publisher";
        String validPassword = "password";

        if (username.equals(validUsername) && password.equals(validPassword)) {
            System.out.println("Authentication successful!");
            return true;
        } else {
            System.out.println("Authentication failed!");
            return false;
        }
    }
    

    public static RadioServer connectToServer() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1098);
            RadioServer server = (RadioServer) registry.lookup("RadioServer");
            System.out.println("Connected to server successfully!");
            return server;
        } catch (Exception e) {
            System.out.println("Failed to connect to the server!");
            e.printStackTrace();
        }
        return null;
    }

    public static void addSong(RadioServer server) {
        try {
            Song song = new Song();
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter song title: ");
            String title = scanner.nextLine();
            song.setTitle(title);

            System.out.print("Enter album name: ");
            String album = scanner.nextLine();
            song.setAlbum(album);

            System.out.print("Enter artist name: ");
            String artist = scanner.nextLine();
            song.setArtist(artist);

            System.out.print("Enter song duration: ");
            int duration = scanner.nextInt();
            song.setDuration(duration);

            System.out.print("Enter release date (YYYY-MM-DD): ");
            String releaseDateStr = scanner.next();
            Date releaseDate = parseDate(releaseDateStr);
            song.setReleaseDate(releaseDate);

            System.out.print("Enter credits: ");
            String credits = scanner.next();
            song.setCredits(credits);

            System.out.print("Enter file location: ");
            String fileLocation = scanner.next();
            song.setFileLocation(fileLocation);

            server.addSong(song);

            System.out.println("New song added: " + title);
            System.out.print("Enter base64 encoded song data: ");
            String base64Data = scanner.next();
            byte[] binaryData = Base64.getDecoder().decode(base64Data);
            song.setBinaryData(binaryData);

            System.out.println("New song added: " + title);
        } catch (RemoteException e) {
           e.printStackTrace();
        } catch (IOException e) {
         e.printStackTrace();
        }
    }

    public static Date parseDate(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Failed to parse date!");
            e.printStackTrace();
        }
        return null;
    }
}
