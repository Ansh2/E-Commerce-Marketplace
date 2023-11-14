import java.io.IOException;

public class Test {

    /**
     * Test Cases File
     * Uses extensive test cases to test the functionality of all of our methods in the project
     * And to verify that the user workflow is as expected for a Seller vs for a Buyer
     *
     * @author Justin
     * @version November 14, 2023
     */

    public static void main(String[] args) {

        Marketplace.initializeMarketplace();

        // call each test case method from here
        /* Seller Class Testing */
        //String output = Seller.removeProduct("Purdue laptop bag", "dylanStore"); //Should return that the product was removed sucessfully
        //String output = Seller.removeProduct("Purdue XD", "dylanStore"); //Should return that the product doesn't exist
        //boolean output = Seller.productExists("Purdue nonexistent thing", "dylanStore"); //Should return false
        //boolean output = Seller.productExists("Purdue hoodie", "notAStore");//Should return false
        //boolean output = Seller.productExists("Purdue Half Zip", "dylanStore"); //should return true
        //String output = Seller.addProduct("newproduct!", 50.00, "newStore!", 7, "A new Test product", "newTestSeller"); //should add properly
        //String output = Seller.editProduct("newproduct!", "newStore!", "quantity", "999"); //should update
        //TODO Editproducts still not working
        //Seller.editProduct("nonoexistentProduct", "nonexistentstore", "description", "Hello World"); //should return prouduct not found
        //boolean output = Seller.exportStoreInformation("dylank", "dylanStore"); //should export file containing dylanStore products
        //format:
        //Store,item,price

        //boolean output = Seller.exportStoreInformation("noneexistentSeller", "dylanStore"); //should not export anything
        //boolean output = Seller.importStoreInformation("dylank","testImportFile.txt");
        //String output = Seller.viewStoreStatistics("davidkj", 1);
        //String output = Seller.viewStoreStatistics("davidkj", 2);
        String output = Seller.viewStoreStatistics("dylank", 1);
        System.out.println(output);
        /* End of Seller Class Testing */

        /* Customer Class Testing */

        //Customer.exportPurchaseHistory("tandon39");
        //should create a csv file in root dir containing the purchases of this person 
        //format:
        //productName;price;storeName;quantity;customer-userame;seller-username
        //Customer.viewShoppingHistory("tandon39", "price", false);
        //Customer.viewShoppingHistory("tandon39", "price", true);
        //Customer.viewShoppingHistory("tandon39", "quantity", false);
        //Customer.viewShoppingHistory("tandon39", "quantity", true);
        //Customer.addToCart("Purdue Hoodie", "sandyStore", 4, "tandon39"); //should be sucessful
        //Customer.addToCart("Purdue Hat", "sandyStore", 4, "tandon39"); //should say out of stock
        //Customer.buyShoppingCartItems("tandon39"); // should buy things and update purchases.txt to reflect the things brought 
        //as well as if brought properly, should decrease the stock by number of things brought 
        //Customer.removeFromCart("tandon39", "Purdue Overalls"); //should remove from shoppingcart
        //Customer.removeFromCart("tandon39", "Noneexistentthing"); //should display error
        //should delete the entire line from shoppingCart.txt


        /* End customer class testing */

        /* MarketPlace Class Testing */

        //Marketplace.initializeMarketplace();
        //Marketplace.updateMarketplace();
        //Marketplace.printMarketplace();
        //Marketplace.getProductInfo(7);
        //Marketplace.searchProduct("badge");
        //Marketplace.searchProduct("nonexistentThings");
        //Marketplace.getProductPrice("Purdue Hat", "sandyruk");
        //Marketplace.productDetail(4);
        //Marketplace.sortMarket("price", false);
        //Marketplace.sortMarket("price", true);
        //Marketplace.sortMarket("quantity", false);
        //Marketplace.sortMarket("quantity", true);
        //Marketplace.removeFromCart(null, null); // this shouldn't even be here


        /* End MarketPlace Class testing */

        /* StartingApplication testing */
        //StartingApplication.main(args);
        /* End StartingApplication */
    }
}
