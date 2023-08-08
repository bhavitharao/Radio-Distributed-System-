import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;
import java.util.Date;

public class Song implements Serializable {
    private String title;
    private String album;
    private String artist;
    private int duration;
    private Date releaseDate;
    private String credits;
    private String fileLocation;
    private byte[] binaryData;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAlbum() {
        return album;
    }
    
    public void setAlbum(String album) {
        this.album = album;
    }
    
    public String getArtist() {
        return artist;
    }
    
    public void setArtist(String artist) {
        this.artist = artist;
    }
    
    public int getDuration() {
        return duration;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    public Date getReleaseDate() {
        return releaseDate;
    }
    
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    public String getCredits() {
        return credits;
    }
    
    public void setCredits(String credits) {
        this.credits = credits;
    }
    
    public String getFileLocation() {
        return fileLocation;
    }
    
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    

    public void setBinaryData(byte[] binaryData) {
        this.binaryData = binaryData;
    }

    public byte[] getBinaryData() {
        return binaryData;
    
}
}   
