import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class RadioServerImpl extends UnicastRemoteObject implements RadioServer {
    private final SongStore songStore;

    public RadioServerImpl() throws RemoteException {
        songStore = new SongStore();
    }

    @Override
    public void addSong(Song song) throws RemoteException {
        songStore.addSong(song);
    }

    @Override
    public byte[] getBinarySong(String title) throws RemoteException {
        Song song = songStore.getSongByTitle(title);
        if (song != null) {
            return song.getBinaryData();
        } else {
            return null;
        }
    }

    @Override
    public void addBinarySong(String title, byte[] songData) throws RemoteException {
        Song song = new Song();
        song.setTitle(title);
        song.setBinaryData(songData);

        songStore.addSong(song);
    }
}

// SongStore class for managing songs
class SongStore {
    private final Map<String, Song> songMap;

    public SongStore() {
        songMap = new HashMap<>();
    }

    public void addSong(Song song) {
        songMap.put(song.getTitle(), song);
    }

    public Song getSongByTitle(String title) {
        return songMap.get(title);
    }
}
