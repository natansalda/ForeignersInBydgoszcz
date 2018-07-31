package pl.nataliana.foreignersinbydgoszcz.model;

public class Formal {

    private long id;
    public String name;
    public String description;
    public String address;

    public Formal(long id, String name, String description, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getAddress() {
        return address;
    }
}