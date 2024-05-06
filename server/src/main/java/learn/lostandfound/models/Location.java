package learn.lostandfound.models;

public class Location {
    private int location_id;
    private String address;
    private String city;
    private String state;
    private String zip_code;

    public Location(int location_id, String address, String city, String state, String zip_code) {
        this.location_id = location_id;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip_code = zip_code;
    }
    public Location() {}

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }
}
