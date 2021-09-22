package it.plansoft.codekombat;

public class Input {
    private final int id;
    private final int numPersons;
    private final int numHandBaggage;
    private final int numExtraBaggage;
    private final String departureAirport;
    private final String arrivalAirport;

    public Input(int id, int numPersons, int numHandBaggage, int numExtraBaggage, String departureAirport, String arrivalAirport) {
        this.id = id;
        this.numPersons = numPersons;
        this.numHandBaggage = numHandBaggage;
        this.numExtraBaggage = numExtraBaggage;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
    }

    public int getId() {
        return id;
    }

    public int getNumPersons() {
        return numPersons;
    }

    public int getNumHandBaggage() {
        return numHandBaggage;
    }

    public int getNumExtraBaggage() {
        return numExtraBaggage;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    @Override
    public String toString() {
        return "Input{" +
                "id=" + id +
                ", numPersons=" + numPersons +
                ", numHandBaggage=" + numHandBaggage +
                ", numExtraBaggage=" + numExtraBaggage +
                ", departureAirport='" + departureAirport + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                '}';
    }
}
