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
        
        for (MenuItem menuItem : itemsOrdered) {
            total += menuItem.getPrice();
        }
        return total;
    }
}