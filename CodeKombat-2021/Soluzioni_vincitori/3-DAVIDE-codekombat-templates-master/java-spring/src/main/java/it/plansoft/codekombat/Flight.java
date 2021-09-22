package it.plansoft.codekombat;

public class Flight {
    private final int id;
    private final float price;
    private final float handBaggagePrice;
    private final float extraBaggagePrice;
    private final String departureAirport;
    private final String arrivalAirport;

    public Flight(int id, float price, float handBaggagePrice, float extraBaggagePrice, String departureAirport, String arrivalAirport) {
        this.id = id;
        this.price = price;
        this.handBaggagePrice = handBaggagePrice;
        this.extraBaggagePrice = extraBaggagePrice;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public float getHandBaggagePrice() {
        return handBaggagePrice;
    }

    public float getExtraBaggagePrice() {
        return extraBaggagePrice;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", price=" + price +
                ", handBaggagePrice=" + handBaggagePrice +
                ", extraBaggagePrice=" + extraBaggagePrice +
                ", departureAirport='" + departureAirport + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                '}';
    }
}
