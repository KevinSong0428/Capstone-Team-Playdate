package learn.lostandfound.models;

import java.util.Objects;

public class Location {
    private int locationId;
    private String address;
    private String city;
    private String state;
    private String zipCode;

    public Location(int locationId, String address, String city, String state, String zipCode) {
        this.locationId = locationId;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    public Location() {}

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return locationId == location.locationId && Objects.equals(address, location.address) && Objects.equals(city, location.city) && Objects.equals(state, location.state) && Objects.equals(zipCode, location.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, address, city, state, zipCode);
    }
}
