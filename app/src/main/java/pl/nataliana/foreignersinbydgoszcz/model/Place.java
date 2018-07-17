package pl.nataliana.foreignersinbydgoszcz.model;

public class Place {

    public String name;
    public String description;
    public int id_;
    public int image;
    public String address;

    public Place(String name, String description, int id_, int image, String address) {
        this.name = name;
        this.description = description;
        this.id_ = id_;
        this.image = image;
        this.address = address;
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

    public String getAddress() {
        return address;
    }
}
