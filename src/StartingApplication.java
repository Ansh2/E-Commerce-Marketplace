import java.io.*;
import java.util.*;
import java.util.Scanner;

/** Starting Application Class
 * Serves as the Login/Sign up system for a user
 * Verifies that they use a @purdue.edu email and
 * whether they are a buyer or seller
 * The class redirects them to the corresponding menu
 *
 * @author Lalitha Chandolu, Ansh Tandon, Justin Ho-Yuk
 * @version November 12, 2023
 *
 */

public class StartingApplication {

    // public static AccountManager am = new AccountManager();

    public static void main(String[] args) {
        // g2g
        System.out.println("Welcome to the Boilermaker Bazaar! ");
        Scanner s = new Scanner(System.in);
        // welcomes the user to the application
        // redirects them to sign up or sign in page
        boolean redirected = false;
        boolean exitOnFirst = false;
        String username = "";
        String password = "";
        String userRole = "";
        do {
            try {
                int value = 0;
                System.out.println("Please select one of the three options: " +
                        "\n1. Login to Application" +
                        "\n2. Sign Up with a New Account" +
                        "\n3. Exit");
                value = Integer.parseInt(s.nextLine());
                if (value == 1) {
                    //  the user has an existing account and would like to login to the marketplace
                    System.out.println("Enter username");
                    username = s.nextLine();
                    System.out.println("Enter password");
                    password = s.nextLine();
                    // verifies that the existing account exists
                    userRole = accountExists(username, password);
                    if (userRole == null) {
                        // user inputted wrong information or user doesn't currently exist
                        System.out.println("The username or password is incorrect");
                        // should go back again to main menu due to error
                    } else {
                        // signed in successfully
                        System.out.println("Success, logging you in now");
                        redirected = true;
                    }
                } else if (value == 2) {
                    // would like to make a new account in the marketplace
                    System.out.println("Enter your name");
                    String name = s.nextLine();
                    // allows each user to have a unique identifier
                    System.out.println("Enter your Purdue username (the part before your @purdue.edu)");
                    username = s.nextLine();
                    System.out.println("Enter new password");
                    password = s.nextLine();
                    System.out.println("Are you signing up as a \n [1] Seller \n [2] Buyer");
                    String roleChoice = s.nextLine();
                    // checks if they have an existing account or not
                    userRole = accountExists(username, password);
                    // they don't have an existing account - so they can make an account
                    if (userRole == null) {
                        // they want to have the role of a seller
                        if (roleChoice.equals("1")) {
                            File f = new File("/data/Sellers.txt");
                            try {
                                FileWriter fw = new FileWriter(f, true);
                                BufferedWriter bfw = new BufferedWriter(fw);
                                // populates a list of sellers with the username (purdue email)
                                bfw.write(username + ";");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else if (roleChoice.equals("2")) {
                            // they want to have a Customer role
                            File f = new File("/data/shoppingCart.txt");
                            try {
                                FileWriter fw = new FileWriter(f, true);
                                BufferedWriter bfw = new BufferedWriter(fw);
                                // populates a new file of Customers (based on given info)
                                bfw.write(username + ";" + password + ";" + name.toLowerCase() + ";");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("Please try again!");
                        }
                    } else {
                        // account exists - go back to main menu
                        System.out.println("Account already exists");
                    }
                } else if (value == 3) {
                    // would like to exit the application
                    System.out.println("Thank you for using Purdue Bazaar!");
                    redirected = true;
                    exitOnFirst = true;
                } else {
                    // invalid choice was enetered
                    System.out.println("Please try again. Make sure you enter a valid choice.");
                }
            } catch (NumberFormatException e) {
                // user didn't enter a number
                System.out.println("There was an error in your input, please try again");
            }
        } while (!redirected);


        boolean loggedOut = exitOnFirst;
        Marketplace.initializeMarketplace();
        while (!loggedOut) {
            if (userRole.equalsIgnoreCase("seller")) {
                System.out.println("Seller Main Menu:\n1. View Marketplace\n2. View all sales by store\n3. Add a Product\n" +
                        "4. Edit a Product\n5. Delete a Product\n6. View Store Statistics\n7. Log Out");
                String MMChoice = s.nextLine();
                if (MMChoice.equals("1")) {
                    Marketplace.printMarketplace();
                } else if (MMChoice.equals("2")) {
                    System.out.println();
                } else if (MMChoice.equals("3")) {

                } else if (MMChoice.equals("4")) {

                } else if (MMChoice.equals("5")) {

                } else if (MMChoice.equals("6")) {

                } else if (MMChoice.equals("7")) {
                    loggedOut = true;
                } else {
                    System.out.println("Please try again with valid input!");
                }
            } else {
                // the user is a Customer type
                System.out.println("Customer Main Menu:\n1. View Marketplace\n2. View Shopping Cart\n3. Search for Product\n" +
                        "4. View Shopping History\n5. Log Out");
                String MMChoice = s.nextLine();
                if (MMChoice.equals("1")) {
                    Marketplace.printMarketplace();
                    System.out.println("1. Sort by Ascending Price\n2. Sort by Descending price\n3. Sort by ascending quantity\n4. Sort by Descending Quantity\n(Anything else.) exit");
                    String sortChoice = s.nextLine();
                    if (sortChoice.equals("1")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket(null, "price", "asc");
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else if (sortChoice.equals("2")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket(null, "price", "dsc");
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else if (sortChoice.equals("3")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket(null, "quantity", "asc");
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else if (sortChoice.equals("4")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket(null, "quantity", "dsc");
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else {
                        System.out.println("try again");
                    }
                } else if (MMChoice.equals("2")) {
                    ArrayList<String> shoppingCart = Marketplace.displayShoppingCart(username);
                    for (String str : shoppingCart) {
                        System.out.println(str + "\n");
                    }
                } else if (MMChoice.equals("3")) {
                    System.out.println("Enter your search term: ");
                    String keyword = s.nextLine();
                    ArrayList<String> preReq = new ArrayList<>();
                    for (String str : Marketplace.searchProduct(keyword)) {
                        System.out.println(str);
                        preReq.add(str.split(";")[0]);
                    }
                    System.out.println("1. Sort by Ascending Price\n2. Sort by Descending price\n3. Sort by ascending quantity\n4. Sort by Descending Quantity\n(Anything else.) exit");
                    String sortChoice = s.nextLine();



                    if (sortChoice.equals("1")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("price", "asc");
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else if (sortChoice.equals("2")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("price", "dsc");
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else if (sortChoice.equals("3")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("quantity", "asc");
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else if (sortChoice.equals("4")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("quantity", "dsc");
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else {
                        System.out.println("try again");
                    }
                } else if (MMChoice.equals("4")) {

                } else if (MMChoice.equals("5")) {
                    loggedOut = true;
                } else {
                    System.out.println("Please try again with valid input!");
                }

            }
        }
    }

    /***************************************************************************************************************/
    public static String viewMarket() {
        File f = new File("/data/Sellers.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                String[] temp = line.split(";");
                String products = temp[3].split(",")
                line = bfr.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String accountExists(String email, String password) {
        File f = new File("/data/shoppingCart.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                String[] temp = line.split(";");
                if (temp[0].equals(email) && temp[1].equals(password)) {
                    return "Customer";
                }
                line = bfr.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        f = new File("/data/Sellers.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                String[] temp = line.split(";");
                if (temp[0].equals(email) && temp[1].equals(password)) {
                    return "Seller";
                }
                line = bfr.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean signIn(Scanner s) {
        // g2g
        readUsersFile();
        System.out.println("Please enter your Email: ");
        String email = s.nextLine();
        System.out.println("Please enter your Password: ");
        String password = s.nextLine();
        // verifies that the information matches
        String accountExists = accountExists(email, password);
        if (accountExists.equals("Seller") || accountExists.equals("Customer")) {
            System.out.println("You're logged in. ");
            return true;
            // get the user object
        } else {
            System.out.println("Error. You don't have an existing account.");
            return false;
        }
    }

    public static boolean signUp(Scanner s) {
        System.out.println();
        System.out.println("Please create a new user account");
        System.out.println("Please enter your name: ");
        String name = s.nextLine();
        System.out.println("Please enter your unique email (this will serve as your id): ");
        String email = s.nextLine();
        boolean valid = checkEmail(email);
        // email is valid - continue
        if (valid) {
            System.out.println("Please enter your designated password");
            String password = s.nextLine();
            boolean isValidUser = false;
            if (!isValidUser) {
                System.out.println("Are you a buyer or seller? (type b for buyer; s for seller");
                String userType = s.nextLine();
                if (userType.equalsIgnoreCase("s")) {
                    Seller seller = new Seller(name, email, password);
                    isValidUser = true;
                } else if (userType.equalsIgnoreCase("b")) {
                    Customer customer = new Customer(name, email, password);
                    isValidUser = true;
                } else {
                    System.out.println("You didn't enter something right, please try again");
                }
            }
            boolean canSignIn = false;
            boolean signedIn = false;
            while (!canSignIn) {
                // allows the new user to choose if  they want to sign
                // in to the marketplace application
                System.out.println("Would you like to sign in to the marketplace now? (type y or n) ");
                String choice = s.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    // redirects them to signIn method
                    canSignIn = true;
                    signedIn = signIn(s);
                } else if (choice.equalsIgnoreCase("n")) {
                    // ends the program here
                    canSignIn = true;
                    System.out.println("Goodbye");
                } else {
                    // repeats the loop for user input
                    canSignIn = false;
                    System.out.println("Sorry, wrong input was given. Please try again.");
                }
            }
        } else {
            System.out.println("Your email was invalid. Make sure you are a Purdue Student.");
            return false;
        }
        return true;
    }

    public static void viewSellerMainMenu(Scanner s, Seller seller) {
        System.out.println("Welcome Seller.");
        boolean choiceIsValid = false;
        while (!choiceIsValid) {
            System.out.println(" Enter a number between 1 to 4: \n " +
                    "1. Manage Stores\n" +
                    "2. View Store Statistics\n" +
                    "3. View Store Selling History\n" +
                    "4. Log Out");
            String choice = s.nextLine();

            if (choice.equals("1")) {
                // Submenu: Manage Stores Options
                choiceIsValid = true;
                System.out.println("Enter a number between 1 - 4: \n");
                System.out.println("1. Add a product");
                System.out.println("2. Delete a product");
                System.out.println("3. Modify a product");
                System.out.println("4. Delete a store");
                choice = s.nextLine();

                if (choice.equals("1")) {
                    // seller.addProduct(); // make in seller
                } else if (choice.equals("2")) {
                    // seller.deleteProduct();
                } else if (choice.equals("3")) {
                    // seller.modifyProduct();
                } else if (choice.equals("4")) {
                    // seller.deleteAStore();
                }

            } else if (choice.equals("2")) {
                // View Store Statistics Option
                //placeholder for seller stats method);
                choiceIsValid = true;

            } else if (choice.equals("3")) {
                // View Store Selling History
                choiceIsValid = true;

            } else if (choice.equals("4")) {
                choiceIsValid = true;
                System.out.println("Goodbye! We hope you visit again.");
                logOut();

            } else {
                System.out.println("Invalid input was given. Please try again with valid input.");
                choiceIsValid = false;
            }

        }
    }

    public static void viewCustomerMainMenu(Scanner s, Customer customer) {
        System.out.println("Welcome Customer.");
        boolean choiceIsValid = false;
        while (!choiceIsValid) {
            System.out.println(" Enter a number between 1 to 4: \n " +
                    "1. View Overall Marketplace\n" +
                    "2. Search for a Store\n" +
                    "3. Search for a product\n" +
                    "4. View Shopping Cart\n" +
                    "5. Sort Marketplace\n" +
                    "6. Export Purchase History\n" +
                    "7. Log Out");
            String choice = s.nextLine();
            if (choice.equals("1")) {
                // use findProductByName("");

            } else if (choice.equals("2")) {
                // use find getStores();
                System.out.println(" Enter a number between 1 to 3: \n " +
                        "1. Sort stores by products sold (ascending) \n" +
                        "2. Sort stores by products sold (descending) \n" +
                        "3. Search for a product\n");

            } else if (choice.equals("3")) {
                System.out.println(" Enter a number between 1 to 4: \n " +
                        "1. Find product by name\n" +
                        "2. Find product by description\n" +
                        "3. Find product by Store\n");
                choice = s.nextLine();

            } else if (choice.equals("4")) {

            } else if (choice.equals("5")) {

            } else if (choice.equals("6")) {

            } else if (choice.equals("7")) {
                choiceIsValid = true;
                System.out.println("Goodbye! We hope you visit again.");
                logOut();

            } else {
                System.out.println("Invalid input was given. Please try again with valid input.");
                choiceIsValid = false;
            }
        }
    }

    public static void readUsersFile() {
        File file = new File("users.txt");
        try {
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                // 3rd element is token for Seller or Customer
                String[] tokens = line.split(",");
                if(tokens[3].equals("c")) {
                    Customer c = new Customer(tokens[1], tokens[0], tokens[2]);
                    User.addUser(c);
                } else if (tokens[3].equals("s")) {
                    Seller s = new Seller(tokens[1], tokens[0], tokens[2]);
                    User.addUser(s);
                }
            } // end of while loop
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void logOut() {
        File file = new File("users.txt");
        file.delete();
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bfw = new BufferedWriter(fw);
            for (User user : User.getExistingUsers()) {
                String userType;
                if (user instanceof Seller) {
                    userType= "s";
                } else {
                    userType = "c";
                }
                String formatLine = String.format("%s,%s,%s,%s\n", user.getEmail(), user.getName(),
                        user.getPassword(), userType);
                bfw.append(formatLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkEmail(String email) {
        // verifies that the email is a valid Purdue email
        if(email.contains("@")) {
            String[] tokens = email.split("@");
            return tokens[1].equals("purdue.edu");
        } else {
            return false;
        }
    }

}
