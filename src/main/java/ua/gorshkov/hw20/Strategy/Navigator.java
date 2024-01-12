package ua.gorshkov.hw20.Strategy;

public class Navigator {
    public void calculateRouteByGivenStrategy(String destination, RouteStrategy routeStrategy) {
        System.out.println(routeStrategy.calculateRoute(destination));
    }
}
