package it.unipd.tos.business;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import org.junit.rules.ExpectedException;
import org.junit.Rule;
import org.junit.Test;

public class TakeAwayManagerTest{
    @Test
    public void SimpleTotalSumOfItems_Test(){
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        User user = new User(1,"Luciano Firi","via Pollo",LocalDate.of(1996,06,06));
        LocalTime time = LocalTime.of(12,15);
        TakeAwayManager testBill = new TakeAwayManager();

        itemsOrdered.add(new MenuItem("Panino kebab", MenuItem.items.Panino, 4.00));
        itemsOrdered.add(new MenuItem("Fanta", MenuItem.items.Bevanda, 1.50));
        itemsOrdered.add(new MenuItem("Coppa alaska", MenuItem.items.Gelato, 2.50));

        try{
            assertEquals(8.5, testBill.getOrderPrice(itemsOrdered, user, time), 0.0);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }
    }

    @Test
    public void Discount50PerCentLessExpensiveGelatoWith5PlusGelatoOrder_Test() throws TakeAwayBillException{
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        User user = new User(1,"Luciano Firi","via Pollo",LocalDate.of(1996,06,06));
        LocalTime time = LocalTime.of(12,15);
        TakeAwayManager testBill = new TakeAwayManager();

        itemsOrdered.add(new MenuItem("Panino primavera", MenuItem.items.Panino, 4.00));
        itemsOrdered.add(new MenuItem("Coca cola" , MenuItem.items.Bevanda, 1.50));
        itemsOrdered.add(new MenuItem("Panino vegetariano", MenuItem.items.Panino, 3.50));
        itemsOrdered.add(new MenuItem("Hot dog" , MenuItem.items.Panino, 4.00));
        itemsOrdered.add(new MenuItem("Coppa pioppo" , MenuItem.items.Gelato, 3.00));
        itemsOrdered.add(new MenuItem("Coppa pioppo" , MenuItem.items.Gelato, 3.00));
        itemsOrdered.add(new MenuItem("Coppa pioppo" , MenuItem.items.Gelato, 3.00));
        itemsOrdered.add(new MenuItem("Panino primavera", MenuItem.items.Panino, 4.00));
        itemsOrdered.add(new MenuItem("Coca cola" , MenuItem.items.Bevanda, 1.50));
        itemsOrdered.add(new MenuItem("Coppa pioppo" , MenuItem.items.Gelato, 3.00));
        itemsOrdered.add(new MenuItem("Hot dog" , MenuItem.items.Panino, 4.00));
        itemsOrdered.add(new MenuItem("Coppa pioppo" , MenuItem.items.Gelato, 3.00));
        itemsOrdered.add(new MenuItem("Coppa pioppo" , MenuItem.items.Gelato, 3.00));

        try {
            assertEquals(39.00, testBill.getOrderPrice(itemsOrdered, user, time), 0.0);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }
    }

    @Test
    public void Discount10PercentWith50PlusEuroOrder_Test() throws TakeAwayBillException {
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        User user = new User(1,"Luciano Firi","via Pollo",LocalDate.of(1996,06,06));
        LocalTime time = LocalTime.of(12,15);
        TakeAwayManager testBill = new TakeAwayManager();

        for(int i = 1; i <= 10; i++)
            itemsOrdered.add(new MenuItem("Panino primavera", MenuItem.items.Panino, 4.00));

        itemsOrdered.add(new MenuItem("Coca cola" , MenuItem.items.Bevanda, 1.50));
        itemsOrdered.add(new MenuItem("Panino vegetariano", MenuItem.items.Panino, 3.50));
        itemsOrdered.add(new MenuItem("Hot dog" , MenuItem.items.Panino, 4.00));
        itemsOrdered.add(new MenuItem("Coppa pioppo" , MenuItem.items.Gelato, 3.00));
        try {
            assertEquals(46.95, testBill.getOrderPrice(itemsOrdered, user, time), 0.0);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void NumberElementsExceed30_Test() throws TakeAwayBillException {
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        User user = new User(1,"Luciano Firi","via Pollo",LocalDate.of(1996,06,06));
        LocalTime time = LocalTime.of(12,15);
        TakeAwayManager testBill = new TakeAwayManager();

        thrown.expect(TakeAwayBillException.class);
        thrown.expectMessage("Non ci possono essere più di 30 elementi nell'ordine");

        for(int i = 1; i <= 40; i++)
            itemsOrdered.add(new MenuItem("Panino primavera", MenuItem.items.Panino, 4.00));

        testBill.getOrderPrice(itemsOrdered, user, time);
    } 

    @Test
    public void TotalPriceLessThan10Euros_Test() throws TakeAwayBillException {
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        User user = new User(1,"Luciano Firi","via Pollo",LocalDate.of(1996,06,06));
        LocalTime time = LocalTime.of(12,15);
        TakeAwayManager testBill = new TakeAwayManager();

        itemsOrdered.add(new MenuItem("Coppa pioppo" , MenuItem.items.Gelato, 3.00));

        try {
            assertEquals(3.5, testBill.getOrderPrice(itemsOrdered, user, time), 0.0);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }
    } 
    
    @Test
    public void UserIsUnder18_TimeBetween18And19_Test(){
        List<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        User user = new User(1,"Luciano Firi","via Pollo",LocalDate.of(2005,06,06));
        LocalTime time = LocalTime.of(18,35);
        TakeAwayManager testBill = new TakeAwayManager();

        itemsOrdered.add(new MenuItem("Panino kebab", MenuItem.items.Panino, 4.00));
        itemsOrdered.add(new MenuItem("Fanta", MenuItem.items.Bevanda, 1.50));
        itemsOrdered.add(new MenuItem("Coppa alaska", MenuItem.items.Gelato, 2.50));

        try{
            assertEquals(0.0, testBill.getOrderPrice(itemsOrdered, user, time), 0.0);
        } 
        catch (TakeAwayBillException exc){
            exc.getMessage();
        }
    }
}