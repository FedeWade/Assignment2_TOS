////////////////////////////////////////////////////////////////////
// Federico Ballarin 1123718
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;
import java.time.LocalTime;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class TakeAwayManager implements TakeAwayBill {
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user, LocalTime time) throws TakeAwayBillException {
        double totalFood = 0.0;
        double totalDrink = 0.0;
        int nrGelati = 0;
        double gelatoLessExpensive = Double.MAX_VALUE;
        double commisione = 0.5;

        for (MenuItem menuItem : itemsOrdered) {
            if(menuItem.getType() == MenuItem.items.Bevanda) {
                totalDrink += menuItem.getPrice();
            }
            else {
                totalFood += menuItem.getPrice();
            }

            if(menuItem.getType() == MenuItem.items.Gelato) {
                nrGelati++;
                if(gelatoLessExpensive > menuItem.getPrice()) {
                    gelatoLessExpensive = menuItem.getPrice();
                }
            }
        }

        if(itemsOrdered.size() > 30){
            throw new TakeAwayBillException("Non ci possono essere più di 30 elementi nell'ordine");
        }

        if(nrGelati > 5){
            totalFood -= (gelatoLessExpensive/2); 
        }

        if(totalFood > 50.0){
            totalFood -= (totalFood*0.1);
        }
        
        if(totalDrink + totalFood < 10.0) {
            return totalDrink + totalFood + commisione;    
        }
        else {
            return totalDrink + totalFood;
        }   
    }
}