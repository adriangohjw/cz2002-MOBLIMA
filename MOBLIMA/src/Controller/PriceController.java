package Controller;

import Model.*;

import java.util.HashMap;
import java.util.Map;

public class PriceController {

    private HolidaysController holidaysCtrl;
    private Map<PriceChanger,Double> priceMap = new HashMap<>();

    public PriceController(){
        this.holidaysCtrl = new HolidaysController();
        populateDefaultPrices(priceMap);
    }

    private void populateDefaultPrices(Map<PriceChanger,Double> priceMap) {
        priceMap.put(MovieType.TWO_D, 0.0);
        priceMap.put(MovieType.THREE_D, 3.0);
        priceMap.put(MovieType.BLOCKBUSTER, 3.0);
        priceMap.put(CinemaType.PREMIUM, 1.0);
        priceMap.put(CinemaType.STANDARD, 0.0);
        priceMap.put(PriceType.STUDENT, 8.0);
        priceMap.put(PriceType.HOLIDAY, 12.0);
        priceMap.put(PriceType.NORMAL, 10.0);
        priceMap.put(PriceType.SENIOR_CITIZEN, 6.0);
        priceMap.put(PriceType.WEEKEND, 3.0);
    }

    public void addPriceChanger(PriceChanger priceChanger, double value){
        priceMap.put(priceChanger, value);
    }

    public void changePriceChanger(PriceChanger priceChanger, double newPrice){
        if(priceMap.containsKey(priceChanger)){
            priceMap.replace(priceChanger, newPrice);
        }
    }

    public void removePriceChanger(PriceChanger priceChanger){
        priceMap.remove(priceChanger);
    }

    public Map<PriceChanger,Double> getAllPriceChangers(){
        return priceMap;
    }

    public String getAllPriceChangersToString(){
        String result = "";
        for(PriceChanger priceChanger : priceMap.keySet()){
            result += priceChanger.toString() + " " + priceMap.get(priceChanger) + "\n";
        }
        return result;
    }

    private double getPrice(PriceChanger priceChanger){
        return priceMap.getOrDefault(priceChanger, 0.0);
    }

    public double computePrice(Session session, Cinema cinema, PriceType priceType){
        double addToPrice   = getPrice(session.getMovie().getType()) 
                            + getPrice(cinema.getCinemaType());
        if (session.isWeekend())
            addToPrice += 2.0;
        if(holidaysCtrl.isHoliday(session.getSessionDateTime().toLocalDate())){
            return getPrice(PriceType.HOLIDAY) + addToPrice;
        }
        else{
            return getPrice(priceType) + addToPrice;
        }
    }

}
