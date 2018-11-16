package cinema;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    private String englishName;
    private String thaiName;
    private String director;
    private String cast;
    private String synopsis;
    private String genre;
    private String releaseDate;
    private String time;
    private String poster;
    private String trailer;
   
    public Movie(String englishName, String thaiName, String director, String cast, String synopsis, String genre, String time, String releaseDate, String poster, String trailer) {
        this.englishName = englishName;
        this.thaiName = thaiName;
        this.director = director;
        this.cast = cast;
        this.synopsis = synopsis;
        this.genre = genre;
        this.time = time;
        this.releaseDate = releaseDate;
        this.poster = poster;
        this.trailer = trailer;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEnglishName() {
        return englishName;
    }
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }
    public String getThaiName() {
        return thaiName;
    }
    public void setThaiName(String thaiName) {
        this.thaiName = thaiName;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getCast() {
        return cast;
    }
    public void setCast(String cast) {
        this.cast = cast;
    }
    public String getSynopsis() {
        return synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getPoster() {
        return poster;
    }
    public void setPoster(String poster) {
        this.poster = poster;
    }
    public String getTrailer() {
        return trailer;
    }
    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
    
    //---------------------------------------------------------- Function Method
    
    @Override
    public String toString() 
    { 
        return ("Movie ID : "+ this.getId()+ 
              "\nEnglish Name : " + this.getEnglishName() + 
              "\nThai Name : "+ this.getThaiName() + 
              "\nDirector : " + this.getDirector() +
              "\nCast : " + this.getCast() + 
              "\nSynopsis : " + this.getSynopsis() + 
              "\nGenre : " + this.getGenre() +
              "\nTime : " + this.getTime() +
              "\nRelease Date : " + this.getReleaseDate() +
              "\nPoster : " + this.getPoster() +
              "\nTrailer : " + this.getTrailer());
    } 

    
}
