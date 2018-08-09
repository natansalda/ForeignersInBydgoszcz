package pl.nataliana.foreignersinbydgoszcz.model;

public class Formal {

    private long id;
    public int name;
    public int description;
    public int address;

    public Formal(long id, int name, int description, int address) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public int getAddress() {
        return address;
    }
}