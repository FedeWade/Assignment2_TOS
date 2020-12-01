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
        double total = 0.0;
        int nrGelati = 0;
        double gelatoLessExpensive = Double.MAX_VALUE;
        
        for (MenuItem menuItem : itemsOrdered) {
            total += menuItem.getPrice();

            if(menuItem.getType() == MenuItem.items.Gelato) {
                nrGelati++;
                if(gelatoLessExpensive > menuItem.getPrice()) {
                    gelatoLessExpensive = menuItem.getPrice();
                }
            }
        }

        if(nrGelati > 5){
            total -= (gelatoLessExpensive/2); 
        }
        return total;
    }
}