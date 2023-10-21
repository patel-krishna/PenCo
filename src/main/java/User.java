public class User {
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public static Product getProduct(String SKU){
        return new Product(null,null, null, SKU, 0, null);
    }
    public static Product getProductBySlug(String URL){
        return new Product(null,null, URL, null, 0, null);
    }
}
