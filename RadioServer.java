import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RadioServer extends Remote {
    void addSong(Song song) throws RemoteException;

    byte[] getBinarySong(String title) throws RemoteException;

    void addBinarySong(String title, byte[] songData) throws RemoteException;
}
