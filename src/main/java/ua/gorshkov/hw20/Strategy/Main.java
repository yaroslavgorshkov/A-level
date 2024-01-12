package ua.gorshkov.hw20.Strategy;

public class Main {
    public static void main(String[] args) {
        Navigator navigator = new Navigator();
        RouteStrategy walkRouteStrategy = new WalkRouteStrategy();
        RouteStrategy busRouteStrategy = new BusRouteStrategy();
        RouteStrategy carRouteStrategy = new CarRouteStrategy();
        navigator.calculateRouteByGivenStrategy("New York", walkRouteStrategy);
        navigator.calculateRouteByGivenStrategy("New York", busRouteStrategy);
        navigator.calculateRouteByGivenStrategy("New York", carRouteStrategy);
    }
}
