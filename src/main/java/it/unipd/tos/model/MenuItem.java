////////////////////////////////////////////////////////////////////
// Federico Ballarin 1123718
////////////////////////////////////////////////////////////////////


package it.unipd.tos.model;

public class MenuItem {
    public enum items { Gelato, Panino, Bevanda }
    private items itemType;
    private String name;
    private double price;

    public MenuItem(String n, items t, double p){
        this.itemType = t;
        this.name = n;
        this.price = p;
    }

    public String getName(){
        return name;
    }
    
    public items getType(){
        return itemType;
    }

    public double getPrice(){
        return price;
    }
}