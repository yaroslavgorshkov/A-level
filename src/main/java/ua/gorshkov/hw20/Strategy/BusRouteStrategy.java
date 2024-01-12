package ua.gorshkov.hw20.Strategy;
import java.util.concurrent.ThreadLocalRandom;

public class BusRouteStrategy implements RouteStrategy {
    @Override
    public String calculateRoute(String destination) {
        double randomDistance = ThreadLocalRandom.current().nextDouble(0.1, 50);
        double roundedDistance = Math.round(randomDistance * 100.0) / 100.0;

        return "The length of the path from the given point to "
                + destination + " using Bus is "
                + roundedDistance + " kilometers";
    }
}
