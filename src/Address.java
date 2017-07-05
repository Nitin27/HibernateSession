import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String streetNo;
    private String location;
    private String state;

    public Address() {
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return " Street No: "+streetNo+"    Location: "+location+"  State: "+ state ;
    }
}
