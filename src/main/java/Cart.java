import java.util.Set;
import com.example.*;


public class Cart {
    private Set<Product> shoppingList;

    public Set<Product> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(Set<Product> shoppingList) {
        this.shoppingList = shoppingList;
    }
}
