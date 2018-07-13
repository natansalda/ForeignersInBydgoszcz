package pl.nataliana.foreignersinbydgoszcz.model;

public class Place {

    public String name;
    public String description;
    public int id_;
    public int image;

    public Place(String name, String description, int id_, int image) {
        this.name = name;
        this.description = description;
        this.id_ = id_;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public int getId() {
        return id_;
    }
}
