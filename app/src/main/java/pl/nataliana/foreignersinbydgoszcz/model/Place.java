package pl.nataliana.foreignersinbydgoszcz.model;

public class Place {

    private long id;
    public String name;
    public String description;
    public int image;
    public String address;

    public Place(long id, String name, String description, int image, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.address = address;
    }

    public long getId() {
        return id;
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

    public String getAddress() {
        return address;
    }
}
