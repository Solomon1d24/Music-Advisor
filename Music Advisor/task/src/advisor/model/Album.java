package advisor.model;

import java.util.List;

public class Album {

    private String name;

    private String link;

    private List<String> artist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<String> getArtist() {
        return artist;
    }

    public void setArtist(List<String> artist) {
        this.artist = artist;
    }

    public Album(String name, String link, List<String> artist) {
        this.name = name;
        this.link = link;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", artist=" + artist +
                '}';
    }
}
