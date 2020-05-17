package kz.iitu.CovidTracker.model;

public class Country {
    private String state;
    private String country;
    private int numberOfCases;
    private int difference;

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumberOfCases() {
        return numberOfCases;
    }

    public void setNumberOfCases(int numberOfCases) {
        this.numberOfCases = numberOfCases;
    }

    @Override
    public String toString() {
        return "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", numberOfCases=" + numberOfCases;
    }
}
