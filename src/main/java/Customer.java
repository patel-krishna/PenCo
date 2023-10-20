import com.example.*;
public class Customer extends User{

    private Cart shoppingCart;

    Customer(String username, String password){
        super(username, password);
        shoppingCart = new Cart();
    }
}

    public static Product getCart(){

    }
    public Product addProductToCart(Product newProd){
        shoppingCart.getShoppingList().add(newProd);
    }
    public  Product removeProductFromCart(){

    }