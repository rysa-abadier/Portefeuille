#include <iostream>
#include <fstream>
#include <windows.h>
#include <vector>

using namespace std;

struct user { //manage user objects
    string username, password, fName, lName, type;
    string lines[60];
};

struct product { // manage product objects
    string productName, amt, price;
    string lines[200];
};

// basic functions
string questionUser(string question);
int questionUserInput(string question);
bool confirm(string answer);
bool login(string username, string password, char type);
void exit(char type);
void invalid(char type);
int countFile();
void storeCart(string productName, int amt, int calPrice);
void readCart();

// manage user functions
user getUFile(int j);
user loadUserData(user load, string type, string username);
void readUserData(user display);
void addUser(user add);
void modifyUser(user mod, string type, string username);
void deleteUser(user del, string type, string username);

// manage product functions
product getPFile(int j);
product loadProductData (string productName);
void readProductData(product display);
void addProduct(product add);
void modifyProduct(product mod, string productName);
void deleteProduct(product del, string productName);

int main()
{
    user admin, cashier;
    user uAdd, uMod, uDel;
    product item;
    product pAdd, pMod, pDel;

    int sel;
    int amt, checkAmt, price, calPrice, calAmt, cash;

    bool tryAgain, loginSuccess, retry, addMore,  modifyMore, deleteMore, checkMore, buyMore, shortMoney;
    bool correct, rewrite, backToMenu, backToMain, backToLogin, found, retryInput;

    string username, password;
    string productName, line;
    string type, lName;

    system("color e");

    cout << "\t\t\t\t\t\t     ----------" << endl;
    cout << "\t\t\t\t\t\t    |   Note   |" << endl;
    cout << "\t\t\t\t\t\t     ----------" << endl;
    cout << "\n    Make sure you have a list of users and products, along with their respective properties, before starting." << endl;
    cout << "\n    There should be two separate txt files: 'users.txt' and 'products.txt'." << endl;
    cout << "\n    'users.txt' should contain a list of admins and cashiers with the format:" << endl;
    cout << "         [Type | 'Admin' or 'Cashier']\n         [Username]\n         [Password]\n         [First name]\n         [Last name]" << endl;
    cout << "\n    Note: Always create a space between users. There is only a maximum of 10 users for the file." << endl;
    cout << "\n    'products.txt' should contain a list of products with the format:" << endl;
    cout << "         [Product name]\n         [Amount]\n         [Price]" << endl;
    cout << "\n    Note: Always create a space between products. There is only a maximum of 50 products for the file." << endl;
    cout << "\n    If you have the lists, press 'ENTER'. If not, close the program and try again once you have lists.";
    cin.ignore();

    do {
        do {
            system("cls");
            system("color 7");

            cout << "\t\t\t\t\t      --------------------------" << endl;
            cout << "\t\t\t\t\t     |   Point of Sale System   |" << endl;
            cout << "\t\t\t\t\t      --------------------------" << endl;

            cout << "\n   Welcome to the Sales System. Please enter your selection below.\n      (1) Administrator\n      (2) Cashier\n      (3) Exit\n   You have selected: ";
            cin >> sel;

            switch (sel){
                case 1:
                    tryAgain = false;

                    system("cls");

                    do {
                        system("color 7");

                        cout << "\t\t\t\t\t      -------------------------" << endl;
                        cout << "\t\t\t\t\t     |   Administrator Login   |" << endl;
                        cout << "\t\t\t\t\t      -------------------------" << endl;

                        cout << "\n   Welcome Administrator! Please provide your username and password below." << endl;

                        admin.username = questionUser("\n        Username: ");
                        admin.password = questionUser("        Password: ");

                        username = admin.username;
                        password = admin.password;

                        loginSuccess = login(username, password, 'a');

                        if (loginSuccess){
                            retry = false;

                            admin = loadUserData(admin, "Admin", username);

                            do {
                                system("cls");
                                system("color 7");

                                cout << "\t\t\t\t\t\t -------------------" << endl;
                                cout << "\t\t\t\t\t\t|   Administrator   |" << endl;
                                cout << "\t\t\t\t\t\t -------------------" << endl;

                                cout << "\n   Welcome " << admin.fName << " " << admin.lName << "! What would you like to do?\n      (1) Product\n      (2) User\n      (3) Logout\n   You have selected: ";
                                cin >> sel;

                                switch (sel){
                                    case 1:
                                        do {
                                            system("cls");
                                            system("color 7");

                                            cout << "\t\t\t\t\t\t    -------------" << endl;
                                            cout << "\t\t\t\t\t\t   |   Product   |" << endl;
                                            cout << "\t\t\t\t\t\t    -------------" << endl;

                                            cout << "\n   What would you like to do?\n      (1) Add\n      (2) Modify\n      (3) Remove\n      (4) Back\n   You have selected: ";
                                            cin >> sel;

                                            switch (sel){
                                                case 1:
                                                    do {
                                                        do{
                                                            system("cls");
                                                            system("color 7");


                                                            cout << "\t\t\t\t\t         ----------------------" << endl;
                                                            cout << "\t\t\t\t\t        |   Adding a Product   |" << endl;
                                                            cout << "\t\t\t\t\t         ----------------------" << endl;

                                                            pAdd.productName = questionUser("\n   Product name: ");
                                                            pAdd.amt = questionUser("   Amount of product: ");
                                                            pAdd.price = questionUser("   Price: ");

                                                            system("cls");

                                                            readProductData(pAdd);

                                                            correct = confirm(questionUser("\n   Are the details correct? [Enter 'yes' or 'no']: "));

                                                            if (correct){
                                                                addProduct(pAdd);
                                                                rewrite = false;

                                                                system("color 7");

                                                            } else {
                                                                rewrite = confirm(questionUser("\n   Would you like to rewrite them? [Enter 'yes' or 'no']: "));
                                                            }
                                                        } while (rewrite);

                                                        addMore = confirm(questionUser("\n   Would you like to add another product? [Enter 'yes' or 'no']: "));

                                                        if (!addMore){
                                                            backToMenu = true;

                                                            break;
                                                        }
                                                    } while (addMore);
                                                break;

                                                case 2:
                                                    do {
                                                        do {
                                                            system("cls");
                                                            system("color 7");

                                                            retryInput = false;

                                                            cout << "\t\t\t\t\t      -------------------------" << endl;
                                                            cout << "\t\t\t\t\t     |   Modifying a Product   |" << endl;
                                                            cout << "\t\t\t\t\t      -------------------------" << endl;

                                                            productName = questionUser("\n   What is the name of the product?: ");
                                                            pMod.productName = productName;

                                                            ifstream file("products.txt");

                                                            while (!file.eof()){
                                                                for (int i = 1; i > 0; i++){
                                                                    getline(file, line);

                                                                    if (line == productName){
                                                                        found = true;
                                                                    }
                                                                }
                                                            }

                                                            if (found){
                                                                retryInput = false;
                                                            } else {
                                                                retryInput = true;

                                                                invalid('p');
                                                            }
                                                        } while (retryInput);

                                                        do {
                                                            system("cls");

                                                            readProductData(loadProductData(productName));

                                                            pMod.amt = questionUser("\n   Change the amount of product to: ");
                                                            pMod.price = questionUser("\   Change the price of product to: ");

                                                            system("cls");

                                                            readProductData(pMod);

                                                            correct = confirm(questionUser("\n   Are the details correct? [Enter 'yes' or 'no']: "));

                                                            if (correct){
                                                                modifyProduct(pMod, productName);

                                                                modifyMore = confirm(questionUser("\n   Would you like to modify another product? [Enter 'yes' or 'no']: "));

                                                                if (!modifyMore){
                                                                    backToMenu = true;

                                                                    break;
                                                                }
                                                            } else {
                                                                rewrite = confirm(questionUser("\n   Would you like to rewrite them? [Enter 'yes' or 'no']: "));

                                                                if (!rewrite){
                                                                    backToMenu = true;

                                                                    break;
                                                                }
                                                            }
                                                        } while (rewrite);
                                                    } while (modifyMore);

                                                break;

                                                case 3:
                                                    do {
                                                        do{
                                                            do {

                                                                system("cls");
                                                                system("color 7");

                                                                retryInput = false;

                                                                cout << "\t\t\t\t\t       ------------------------" << endl;
                                                                cout << "\t\t\t\t\t      |   Removing a Product   |" << endl;
                                                                cout << "\t\t\t\t\t       ------------------------" << endl;

                                                                productName = questionUser("\n   What is the name of the product?: ");
                                                                pDel.productName = productName;

                                                                ifstream file("products.txt");

                                                                while (!file.eof()){
                                                                    for (int i = 1; i > 0; i++){
                                                                        getline(file, line);

                                                                        if (line == productName){
                                                                            found = true;
                                                                        }
                                                                    }
                                                                }

                                                                if (found){
                                                                    retryInput = false;
                                                                } else {
                                                                    retryInput = true;

                                                                    invalid('p');
                                                                }
                                                            } while (retryInput);


                                                            system("cls");

                                                            readProductData(loadProductData(productName));

                                                            correct = confirm(questionUser("\n   Delete this product? [Enter 'yes' or 'no']: "));

                                                            if (correct){
                                                                deleteProduct(pDel, productName);

                                                                deleteMore = confirm(questionUser("\n   Would you like to delete another product? [Enter 'yes' or 'no']: "));

                                                                if (!deleteMore){
                                                                    backToMenu = true;

                                                                    break;
                                                                }
                                                            } else {
                                                                rewrite = confirm(questionUser("\n   Would you like to delete another product? [Enter 'yes' or 'no']: "));
                                                            }
                                                        } while (rewrite);
                                                    } while (deleteMore);

                                                break;

                                                case 4:
                                                    backToMenu = false;
                                                    backToMain = true;

                                                    exit('b');
                                                break;

                                                default:
                                                    backToMenu = true;

                                                    invalid('s');
                                                break;
                                            }
                                        } while (backToMenu);
                                    break;

                                    case 2:
                                        do{
                                            system("cls");
                                            system("color 7");

                                            cout << "\t\t\t\t\t\t       ----------" << endl;
                                            cout << "\t\t\t\t\t\t      |   User   |" << endl;
                                            cout << "\t\t\t\t\t\t       ----------" << endl;

                                            cout << "\n   What would you like to do?\n      (1) Add\n      (2) Modify\n      (3) Remove\n      (4) Back\n   You have selected: ";
                                            cin >> sel;

                                            switch (sel){
                                                case 1:
                                                    do {
                                                        do{
                                                            system("cls");
                                                            system("color 7");


                                                            cout << "\t\t\t\t\t            -------------------" << endl;
                                                            cout << "\t\t\t\t\t           |   Adding a User   |" << endl;
                                                            cout << "\t\t\t\t\t            -------------------" << endl;

                                                            uAdd.type = questionUser("\n   Type ['Admin' or 'Cashier']: ");
                                                            uAdd.username = questionUser("   Username: ");
                                                            uAdd.password = questionUser("   Password: ");
                                                            uAdd.fName = questionUser("   First Name: ");
                                                            uAdd.lName = questionUser("   Last Name: ");

                                                            system("cls");

                                                            readUserData(uAdd);

                                                            correct = confirm(questionUser("\n   Are the details correct? [Enter 'yes' or 'no']: "));

                                                            if (correct){
                                                                addUser(uAdd);

                                                                system("color 7");

                                                            } else {
                                                                rewrite = confirm(questionUser("\n   Would you like to rewrite them? [Enter 'yes' or 'no']: "));
                                                            }
                                                        } while (rewrite);

                                                        addMore = confirm(questionUser("\n   Would you like to add another user? [Enter 'yes' or 'no']: "));

                                                        if (!addMore){
                                                            backToMenu = true;

                                                            break;
                                                        }
                                                    } while (addMore);
                                                break;

                                                case 2:
                                                    do {
                                                        do {
                                                            system("cls");
                                                            system("color 7");

                                                            retryInput = false;

                                                            cout << "\t\t\t\t\t         ----------------------" << endl;
                                                            cout << "\t\t\t\t\t        |   Modifying a User   |" << endl;
                                                            cout << "\t\t\t\t\t         ----------------------" << endl;

                                                            type = questionUser("\n   Is the user an 'Admin' or a 'Cashier'?: ");
                                                            uMod.type = type;
                                                            username = questionUser("   What is the user's username?: ");
                                                            uMod.username = username;

                                                            ifstream file("users.txt");

                                                            while (!file.eof()){
                                                                for (int i = 1; i > 0; i++){
                                                                    getline(file, line);

                                                                    if (line == type){
                                                                        for (int i = 1; i > 0; i++){
                                                                            getline(file, line);

                                                                            if (line == username){
                                                                                found = true;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }

                                                            if (found){
                                                                retryInput = false;
                                                            } else {
                                                                retryInput = true;

                                                                invalid('p');
                                                            }
                                                        } while (retryInput);

                                                        do {
                                                            system("cls");

                                                            readUserData(loadUserData(uMod, type, username));

                                                            uMod.type = questionUser("\n   Change the user's type to: ");
                                                            uMod.fName = questionUser("\   Change the user's first name to: ");
                                                            uMod.lName = questionUser("\   Change the user's last name to: ");
                                                            uMod.username = questionUser("\   Change the user's username to: ");
                                                            uMod.password = questionUser("\   Change the user's password to: ");

                                                            system("cls");

                                                            readUserData(uMod);

                                                            correct = confirm(questionUser("\n   Are the details correct? [Enter 'yes' or 'no']: "));

                                                            if (correct){
                                                                modifyUser(uMod, type, username);

                                                                modifyMore = confirm(questionUser("\n   Would you like to modify another user? [Enter 'yes' or 'no']: "));

                                                                if (!modifyMore){
                                                                    backToMenu = true;

                                                                    break;
                                                                }
                                                            } else {
                                                                rewrite = confirm(questionUser("\n   Would you like to rewrite them? [Enter 'yes' or 'no']: "));

                                                                if (!rewrite){
                                                                    backToMenu = true;

                                                                    break;
                                                                }
                                                            }
                                                        } while (rewrite);
                                                    } while (modifyMore);

                                                break;

                                                case 3:
                                                    do {
                                                        do{
                                                            do {

                                                                system("cls");
                                                                system("color 7");

                                                                retryInput = false;

                                                                cout << "\t\t\t\t\t          ---------------------" << endl;
                                                                cout << "\t\t\t\t\t         |   Removing a User   |" << endl;
                                                                cout << "\t\t\t\t\t          ---------------------" << endl;

                                                                type = questionUser("\n   Is the user an 'Admin' or a 'Cashier'?: ");
                                                                uDel.type = type;
                                                                username = questionUser("   What is the user's username?: ");
                                                                uDel.username = username;

                                                                ifstream file("users.txt");

                                                                while (!file.eof()){
                                                                    for (int i = 1; i > 0; i++){
                                                                        getline(file, line);

                                                                        if (line == type){
                                                                            for (int i = 1; i > 0; i++){
                                                                                getline(file, line);

                                                                                if (line == username){
                                                                                    found = true;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }

                                                                if (found){
                                                                    retryInput = false;
                                                                } else {
                                                                    retryInput = true;

                                                                    invalid('p');
                                                                }
                                                            } while (retryInput);


                                                            system("cls");

                                                            readUserData(loadUserData(uDel, type, username));

                                                            correct = confirm(questionUser("\n   Delete this user? [Enter 'yes' or 'no']: "));

                                                            if (correct){
                                                                deleteUser(uDel, type, username);

                                                                deleteMore = confirm(questionUser("\n   Would you like to delete another user? [Enter 'yes' or 'no']: "));

                                                                if (!deleteMore){
                                                                    backToMenu = true;

                                                                    break;
                                                                }
                                                            } else {
                                                                rewrite = confirm(questionUser("\n   Would you like to delete another user? [Enter 'yes' or 'no']: "));
                                                            }
                                                        } while (rewrite);
                                                    } while (deleteMore);

                                                break;

                                                case 4:
                                                    backToMenu = false;
                                                    backToMain = true;

                                                    exit('b');
                                                break;

                                                default:
                                                    backToMenu = true;

                                                    invalid('s');
                                                break;
                                            }
                                        } while (backToMenu);
                                    break;

                                    case 3:
                                        backToMain = false;
                                        backToLogin = true;

                                        exit('l');
                                    break;

                                    default:
                                        backToMain = true;

                                        invalid('s');
                                    break;
                                }
                            } while (backToMain);

                        } else {
                            retry = true;

                            invalid('l');
                        }
                    } while (retry);

                break;

                case 2:
                    tryAgain = false;

                    system("cls");

                    do{
                        cout << "\t\t\t\t\t\t   -------------------" << endl;
                        cout << "\t\t\t\t\t\t  |   Cashier Login   |" << endl;
                        cout << "\t\t\t\t\t\t   -------------------" << endl;

                        cout << "\n   Welcome Cashier! Please provide your username and password below." << endl;

                        cashier.username = questionUser("\n        Username: ");
                        cashier.password = questionUser("        Password: ");

                        username = cashier.username;
                        password = cashier.password;

                        loginSuccess = login(username, password, 'c');

                        if (loginSuccess){
                            retry = false;

                            cashier = loadUserData(cashier, "Cashier", username);

                            do {
                                system("cls");
                                system("color 7");

                                cout << "\t\t\t\t\t\t      -------------" << endl;
                                cout << "\t\t\t\t\t\t     |   Cashier   |" << endl;
                                cout << "\t\t\t\t\t\t      -------------" << endl;

                                cout << "\n   Welcome " << cashier.fName << " " << cashier.lName << "! What would you like to do?\n      (1) Start sale\n      (2) Check Item\n      (3) Logout\n   You have selected: ";
                                cin >> sel;

                                switch (sel){
                                    case 1:
                                        do{
                                            do {
                                                system("cls");
                                                system("color 7");

                                                cout << "\t\t\t\t\t\t        ----------" << endl;
                                                cout << "\t\t\t\t\t\t       |   Sale   |" << endl;
                                                cout << "\t\t\t\t\t\t        ----------" << endl;

                                                cout << "\n   Welcome customer!" << endl;

                                                productName = questionUser("\n   What item would you like to buy?: ");

                                                ifstream file;
                                                file.open("products.txt");

                                                while (!file.eof()){
                                                    for (int i = 1; i > 0; i++){
                                                        getline(file, line);

                                                        if (productName == line){
                                                            found = true;
                                                        }
                                                    }
                                                }

                                                if (found){
                                                    do {
                                                        system("cls");
                                                        system("color 7");

                                                        amt = questionUserInput("\n   How many would you like to buy?: ");

                                                        item = loadProductData(productName);
                                                        checkAmt = stoi(item.amt);
                                                        price = stoi(item.price);

                                                        if (amt > checkAmt){
                                                            retryInput = true;

                                                            system("cls");
                                                            system("color c");

                                                            cout << "\n   Sorry, there aren't enough in stock. Please try to input a lesser amount." << endl;

                                                            system("cls");
                                                            system("color 7");

                                                        } else {
                                                            calPrice = amt * price;
                                                            calAmt += amt;

                                                            cout << "\n        | Product\t\tQuantity\t\tTotal" << endl;
                                                            storeCart(productName, amt, calPrice);
                                                            retryInput = false;
                                                        }
                                                    } while (retryInput);
                                                } else {
                                                    retryInput = true;

                                                    invalid('p');
                                                }
                                            } while (retryInput);

                                            correct = confirm(questionUser("\n   Is there anything more you'd like to buy? [Enter 'yes' or 'no']: "));

                                            if (correct){
                                                buyMore = true;

                                            } else {
                                                cout << "\n        | Product\t\tQuantity\t\tTotal" << endl;
                                                readCart();

                                                cout << "\n   Total number of items: " << calAmt << endl;
                                                cout << "   Total amount of cash " << calPrice << " Jewels" << endl;

                                                do{
                                                    system("cls");

                                                    cout << "\n   Enter amount of cash: ";
                                                    cin >> cash;

                                                    if (cash < calPrice){
                                                        shortMoney = true;

                                                        system("cls");
                                                        system("color c");

                                                        cout << "\n   Insufficient amount of cash. Please pay the right amount.";
                                                        Sleep(2000);

                                                        system("cls");
                                                        system("color c");
                                                    } else {
                                                        shortMoney = false;

                                                        cout << "Change: " << cash - calPrice << " Jewels" << endl;
                                                        cout << "\n   Thank you for purchasing!" << endl;
                                                        Sleep(2000);

                                                        system("cls");
                                                    }

                                                } while(shortMoney);

                                                buyMore = false;
                                                backToMain = true;
                                            }
                                        } while (buyMore);
                                    break;

                                    case 2:
                                        do {
                                            do {
                                                system("cls");
                                                system("color 7");

                                                cout << "\t\t\t\t\t\t     ----------------" << endl;
                                                cout << "\t\t\t\t\t\t    |   Check Item   |" << endl;
                                                cout << "\t\t\t\t\t\t     ----------------" << endl;

                                                productName = questionUser("\n   What item would you like to check?: ");

                                                ifstream file;
                                                file.open("products.txt");

                                                while (!file.eof()){
                                                    for (int i = 1; i > 0; i++){
                                                        getline(file, line);

                                                        if (line == productName){
                                                            found = true;
                                                        }
                                                    }
                                                }

                                                if (found){
                                                    retryInput = false;

                                                    system("cls");

                                                    readProductData(loadProductData(productName));
                                                } else {
                                                    retryInput = true;

                                                    invalid('p');
                                                }
                                            } while (retryInput);

                                            correct = confirm(questionUser("\n   Is there anything more you'd like to check? [Enter 'yes' or 'no']: "));

                                            if (correct){
                                                checkMore = true;
                                            } else {
                                                checkMore = false;
                                                backToMain = true;
                                            }
                                        } while (checkMore);

                                    break;

                                    case 3:
                                        backToMain = false;
                                        backToLogin = true;

                                        exit('l');
                                    break;

                                    default:
                                        backToMain = true;

                                        invalid('s');
                                    break;
                                }
                            } while (backToMain);

                        } else {
                            retry = true;

                            invalid('l');
                        }
                    } while (retry);

                break;

                case 3:
                    tryAgain = false;
                    backToLogin = false;

                    exit('e');

                    break;
                break;

                default:
                    tryAgain = true;
                    backToLogin = true;

                    invalid('s');
                break;
            }
        } while (backToLogin);
    } while (tryAgain);

    return 0;
}

// basic functions
string questionUser(string question){
    string answer;

    cout << question;
    getline(cin >> ws, answer);

    return answer;
}

int questionUserInput(string question){
    int input;

    cout << question;
    cin >> input;

    return input;
}

bool confirm(string answer){
    string yes[] = {"yes", "YES", "Yes", "yEs", "yeS", "YEs", "yES", "YeS", "y", "Y"};
    string no[] = {"no", "NO", "No", "nO", "n", "N"};

    for (int i = 0; i < answer.length(); i++){
        answer[i] = toupper(answer[i]);
    }

    for (string y : yes){
        if (answer == y){
            return true;
        }
    }

    return false;
}

bool login(string username, string password, char type){
    string line, checkUsername, checkPassword;

    ifstream file("users.txt");

    switch (type){
        case 'a':
            while (!file.eof()){
                for (int i = 1; i > 0; i++){
                    getline(file, line);

                    if (line == "Admin"){
                        getline(file, checkUsername);
                        getline(file, checkPassword);
                    }

                    if (username == checkUsername && password == checkPassword){
                        return true;
                    }
                }
            }
        break;

        case 'c':
            while (!file.eof()){
                for (int i = 1; i > 0; i++){
                    getline(file, line);

                    if (line == "Cashier"){
                        getline(file, checkUsername);
                        getline(file, checkPassword);
                    }

                    if (username == checkUsername && password == checkPassword){
                        return true;
                    }
                }
            }
        break;
    }

    return false;
}

void exit(char type){
    switch (type){
        case 'e':
            system("cls");
            system("color e");

            cout << "\n   Exiting the program...";
            Sleep(2000);

            system("cls");
            system("color a");

            cout << "\n   You have successfully exited the program!" << endl;
            Sleep(2000);

            system("cls");
            system("color 7");

            cout << "\n   Thank you! Goodbye." << endl;
        break;

        case 'l':
            system("cls");
            system("color e");

            cout << "\n   Logging out of account...";
            Sleep(2000);

            system("cls");
            system("color a");

            cout << "\n   You have successfully logged out." << endl;
            Sleep(2000);

            system("cls");
            system("color 7");
        break;

        case 'b':
            system("cls");
            system("color e");

            cout << "\n   Going back to the main menu..." << endl;
            Sleep(2000);

            system("cls");
            system("color 7");
        break;
    }
}

void invalid(char type){
    switch (type){
        case 's':
            system("cls");
            system("color c");

            cout << "\n   You have inputed an invalid selection. Please try again.";
            Sleep(2000);

            system("cls");
        break;

        case 'l':
            system("cls");
            system("color c");

            cout << "\n   Invalid login credentials. Please try again.";
            Sleep(2000);

            system("cls");
        break;

        case 'p':
            system("cls");
            system("color c");

            cout << "\n   Product not found. Please try again.";
            Sleep(2000);

            system("cls");
        break;
    }
}

int countFile(string fileName){
    string line;
    int j = 0;

    ifstream countFile(fileName);

    if (countFile.is_open()){
        while(!countFile.eof()){
            getline(countFile, line);
            j++;
        }
        countFile.close();
    }
    return j;
}

void storeCart(string productName, int amt, int calPrice){
    string amtS, calPriceS;

    amtS = to_string(amt);
    calPriceS = to_string(calPrice);

    ofstream file;
    file.open("cart.txt", ios::app);

    file << "\n        | " + productName + "\t\t\t" + amtS + "\t\t\t" + calPriceS + " Jewels\n";

    file.close();

    cout << "\n        | " << productName << "\t\t\t" << amt << "\t\t\t" << calPrice << " Jewels" << endl;

}

void readCart(){
    string line;

    ifstream file("cart.txt");

    if (file.is_open()){
        while (!file.eof()){
            getline(file, line);

            cout << line << endl;
        }
        file.close();
    }
}

// user functions
user getUFile(int j){
    user listed;

    ifstream getFile("users.txt");

    for (int i = 0; i < j + 1; i++){
        getline(getFile, listed.lines[i]);

        if (getFile.eof()){
            break;
        }
    }

    getFile.close();

    return listed;
}

user loadUserData(user load, string type, string username){
    string line;

    ifstream file("users.txt");

    for (int i = 1; i > 0; i++){
        getline(file, line);

        if (line == type){
            load.type = line;

            for (int i = 1; i > 0; i++){
                getline(file, line);

                if (line == username){
                    load.username = username;
                    getline(file, load.password);
                    getline(file, load.fName);
                    getline(file, load.lName);

                    break;
                }
            }
        }
    }

    return load;
}

void readUserData(user display){
    cout << "\n        | Type: " << display.type << endl;
    cout << "        | Name: " << display.lName << ", " << display.fName  << endl;
    cout << "        | Username: " << display.username << endl;
    cout << "        | Password: " << display.password << endl;
}

void addUser(user add){
    system("cls");

    ofstream file;
    file.open("users.txt", ios::app);

    file << "\n\n";
    file << add.type + "\n";
    file << add.username + "\n";
    file << add.password + "\n";
    file << add.fName + "\n";
    file << add.lName;

    file.close();

    system("color e");

    cout << "\n   Adding user..." << endl;
    Sleep(2000);

    system("cls");
    system("color a");

    cout << "\n   User successfully added and saved!" << endl;
    Sleep(2000);

    system("cls");
}

void modifyUser(user mod, string type, string username){
    user listed;

    int j = countFile("users.txt");

    listed = getUFile(j);

    ofstream writeData;
    writeData.open("users.txt", ios::trunc);

    for (int i = 0; i < j + 1; i++){
        writeData << listed.lines[i] + "\n";

        if (type == listed.lines[i]){
            listed.lines[i] = mod.type;

            i++;
            writeData << listed.lines[i] + "\n";

            if (username == listed.lines[i]){
                listed.lines[i] = mod.username;

                i++;
                writeData << mod.password + "\n";

                i++;
                writeData << mod.fName + "\n";

                i++;
                writeData << mod.lName + "\n";

                continue;
            }
        }

        if (writeData.eof()){
            writeData << "\b\b";

            break;
        }
    }

    writeData.close();

    system("cls");
    system("color e");

    cout << "\n   Modifying user..." << endl;
    Sleep(2000);

    system("cls");
    system("color a");

    cout << "\n   User successfully modified and saved!" << endl;
    Sleep(2000);

    system("cls");
    system("color 7");
}

void deleteUser(user del, string type, string username){
    user listed;

    int j = countFile("users.txt");

    listed = getUFile(j);

    ofstream writeData;
    writeData.open("users.txt", ios::trunc);

    for (int i = 0; i < j + 1; i++){
        writeData << listed.lines[i] + "\n";

        if (type == listed.lines[i]){
            i++;
            writeData << listed.lines[i] + "\n";

            if (username == listed.lines[i]){
                i++;
                i++;
                i++;

                continue;
            }
        }

        if (writeData.eof()){
            writeData << "\b\b";

            break;
        }
    }

    writeData.close();

    system("cls");
    system("color e");

    cout << "\n   Deleting user..." << endl;
    Sleep(2000);

    system("cls");
    system("color a");

    cout << "\n   User successfully deleted!" << endl;
    Sleep(2000);

    system("cls");
    system("color 7");
}

// product functions
product getPFile(int j){
    product listed;

    ifstream getFile("products.txt");

    for (int i = 0; i < j + 1; i++){
        getline(getFile, listed.lines[i]);

        if (getFile.eof()){
            break;
        }
    }

    getFile.close();

    return listed;
}

product loadProductData (string productName){
    product load;

    string line;

    ifstream file("products.txt");

    for (int i = 1; i > 0; i++){
        getline(file, line);

        if (line == productName){
            load.productName = productName;
            getline(file, load.amt);
            getline(file, load.price);

            break;
        }
    }

    return load;
}

void readProductData (product display){
    cout << "\n        | Product Name: " << display.productName << endl;
    cout << "        | Amount: " << display.amt << " items available" << endl;
    cout << "        | Price: " << display.price << " Jewels" << endl;
}

void addProduct(product add){
    system("cls");

    ofstream file;
    file.open("products.txt", ios::app);

    file << "\n\n";
    file << add.productName + "\n";
    file << add.amt + "\n";
    file << add.price;

    file.close();

    system("color e");

    cout << "\n   Adding product..." << endl;
    Sleep(2000);

    system("cls");
    system("color a");

    cout << "\n   Product successfully added and saved!" << endl;
    Sleep(2000);

    system("cls");
}

void modifyProduct(product mod, string productName){
    product listed;

    int j = countFile("products.txt");

    listed = getPFile(j);

    ofstream writeData;
    writeData.open("products.txt", ios::trunc);

    for (int i = 0; i < j + 1; i++){
        writeData << listed.lines[i] + "\n";

        if (productName == listed.lines[i]){
            i++;
            writeData << mod.amt + "\n";

            i++;
            writeData << mod.price + "\n";

            continue;
        }

        if (writeData.eof()){
            writeData << "\b\b";

            break;
        }
    }

    writeData.close();

    system("cls");
    system("color e");

    cout << "\n   Modifying product..." << endl;
    Sleep(2000);

    system("cls");
    system("color a");

    cout << "\n   Product successfully modified and saved!" << endl;
    Sleep(2000);

    system("cls");
    system("color 7");
}

void deleteProduct(product del, string productName){
    product listed;

    int j = countFile("products.txt");

    listed = getPFile(j);

    ofstream writeData;
    writeData.open("products.txt", ios::trunc);

    for (int i = 0; i < j + 1; i++){
        writeData << listed.lines[i] + "\n";

        if (productName == listed.lines[i]){
            i++;
            i++;
            i++;

            continue;
        }

        if (writeData.eof()){
            writeData << "\b\b";

            break;
        }
    }

    writeData.close();

    system("cls");
    system("color e");

    cout << "\n   Deleting product..." << endl;
    Sleep(2000);

    system("cls");
    system("color a");

    cout << "\n   Product successfully deleted!" << endl;
    Sleep(2000);

    system("cls");
    system("color 7");
}
