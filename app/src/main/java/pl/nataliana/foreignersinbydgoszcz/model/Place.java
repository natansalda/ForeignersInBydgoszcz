package pl.nataliana.foreignersinbydgoszcz.model;

public class Place {

    private long id;
    public int name;
    public int description;
    public int image;
    public int address;

    public Place(long id, int name, int description, int image, int address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public int getName() {
        return name;
    }

    public int getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public int getAddress() {
        return address;
    }
}
