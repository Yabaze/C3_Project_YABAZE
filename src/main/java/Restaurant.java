import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    private List<Item> selectedMenus = new ArrayList<Item>();
    private int totalOrderCost = 0;

    public Restaurant() {
    }

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {

        LocalTime currentTime = this.getCurrentTime();

        return (currentTime.equals(this.openingTime) || currentTime.isAfter(this.openingTime)) && (currentTime.equals(this.closingTime) || currentTime.isBefore(this.closingTime));

    }

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public int getTotalOrderCost() {
        return totalOrderCost;
    }

    public List<Item> getMenu() {
        return menu;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
    }

    public List<Item> getSelectedMenus() {
        return selectedMenus;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
    }

    private Item findItemByName(String itemName) throws ItemNotFoundException {
        for (Item item : menu) {
            if (item.getName().equals(itemName))
                return item;
        }
        throw new ItemNotFoundException(itemName + " Not Found");
    }

    private Item findSelectionItemByName(String itemName) {
        for (Item item : selectedMenus) {
            if (item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {

        Item newItem = new Item(name, price);

        menu.add(newItem);

    }

    public void removeFromMenu(String itemName) throws ItemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);

        menu.remove(itemToBeRemoved);

    }

    public void selectFromMenu(String selectedItemName) throws ItemNotFoundException {

        Item itemToBeSelected = findItemByName(selectedItemName);

        Item isItemSelectedAlready = findSelectionItemByName(selectedItemName);

        if (isItemSelectedAlready == null) {
            selectedMenus.add(itemToBeSelected);
            totalOrderCost = itemToBeSelected.getPrice() + getTotalOrderCost();
        }

    }

    public void deSelectFromMenu(String itemNameToDeSelect) throws ItemNotFoundException {

        Item itemToBeDeSelected = findSelectionItemByName(itemNameToDeSelect);

        if (itemToBeDeSelected == null)
            throw new ItemNotFoundException(itemNameToDeSelect + " Not Found");

        selectedMenus.remove(itemToBeDeSelected);

        totalOrderCost = getTotalOrderCost() - itemToBeDeSelected.getPrice();

    }

    public void displayDetails() {
        System.out.println("Restaurant:" + name + "\n"
                + "Location:" + location + "\n"
                + "Opening time:" + openingTime + "\n"
                + "Closing time:" + closingTime + "\n"
                + "Menu:" + "\n" + getMenu());

    }

    public String getName() {
        return name;
    }

}
