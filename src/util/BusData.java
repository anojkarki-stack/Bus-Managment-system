package util;

import java.util.HashMap;
import java.util.Map;

public class BusData {

    public static class BusInfo {
        public int price;
        public String time;
        public String type;
        public String operator;

        public BusInfo(int price, String time, String type, String operator) {
            this.price = price;
            this.time = time;
            this.type = type;
            this.operator = operator;
        }
    }

    public static Map<String, BusInfo> getRoutes() {

        Map<String, BusInfo> routes = new HashMap<>();

        routes.put("Kathmandu - Pokhara", new BusInfo(1600, "7:00 AM", "AC Deluxe", "Swift Travels"));
        routes.put("Kathmandu - Chitwan", new BusInfo(1000, "8:30 AM", "Non-AC", "Nepal Bus Service"));
        routes.put("Kathmandu - Butwal", new BusInfo(1200, "6:30 AM", "AC", "Mountain Express"));
        routes.put("Pokhara - Butwal", new BusInfo(900, "9:00 AM", "Non-AC", "Lake View Travels"));
        routes.put("Butwal - Chitwan", new BusInfo(800, "10:00 AM", "AC", "Terai Transport"));

        return routes;
    }
}