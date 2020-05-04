package ca.donaldmartin.netutils.model;

public class GeoInfoDTO {

    private final String ipAddress;
    private final String city;
    private final String country;

    public GeoInfoDTO(String ipAddress, String city, String country) {
        this.ipAddress = ipAddress;
        this.city = city;
        this.country = country;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

}
