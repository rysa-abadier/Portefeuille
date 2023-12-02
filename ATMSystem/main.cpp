#include <iostream>
using namespace std;

int main()

{
    int sel, opt;
    string userID, pw, checkUserID, checkPw;
    char confirm;
    bool again, retry, acc, repeat;
    float bal = 1000.00, cash;

    cout << "--- Welcome to CIIT SHS | ARC Bankings! ---" << endl;
    cout << "--- 'The rise of a new era of banking.' ---" << endl;
    cout << "[Press 'ENTER' to continue]" << endl;
    cin.ignore();

    cout << "Welcome, dear customer!\n" << endl;

    do {
        cout << "Please enter the number of your selection." << endl;
        cout << "     (1) Register an account\n     (2) Login to your account\n     (3) Exit program" << endl;
        cout << "You have selected: ";
        cin >> sel;

            switch (sel){
                case 1: // Register an account
                    again = false;
                    cout << "\n--- Register an account ---" << endl;

                    cout << "\nGreetings to our new customer! Thank you for choosing our bank." << endl;

                    do{
                        cout << "\nPlease enter your details below." << endl;
                        cout << "     User ID: ";
                        getline(cin >> ws, userID);
                        cout << "     Password: ";
                        getline(cin >> ws, pw);

                        cout << "\nAre the details below correct?" << endl;
                        cout << "     User ID: " << userID <<endl;
                        cout << "     Password: " << pw << endl;

                        cout << "\nConfirm User ID and Password?" <<endl;
                        cout << "[Enter 'Y' to confirm | Enter 'N' to input another User ID] -> ";
                        cin >> confirm;

                        if (confirm == 'y' || confirm == 'Y'){
                            cout << "\nYou have successfully made an account! Proceed to login.\n" << endl;
                            acc = true;

                            again = true;
                        }

                    } while (confirm == 'n' || confirm == 'N');

                break;

                case 2:
                    again = true;
                    if (acc){
                    cout << "\n--- Login ---" << endl;

                    do {
                        cout << "\nPlease input your details below." << endl;
                        cout << "     User ID: ";
                        getline(cin >> ws, checkUserID);
                        cout << "     Password: ";
                        getline(cin >> ws, checkPw);

                        if (checkUserID == userID && checkPw == pw){
                            retry = false;
                            cout << "\nYou have successfully logged in!" << endl;

                            do {
                                cout << "\nWhat would you like to do? Please enter the number of your selection." << endl;
                                cout << "     (1) Check balance\n     (2) Deposit money\n     (3) Withdraw money\n     (4) Logout account" << endl;
                                cout << "You have selected: ";
                                cin >> opt;

                                switch (opt){
                                    case 1: // Check balance
                                        cout << "\n--- Balance ---" << endl;

                                        cout << "\nYou currently have " << bal << " Jewels." << endl;

                                        repeat = true;

                                    break;

                                    case 2: // Deposit money
                                        cout << "\n--- Deposit ---" << endl;

                                        cout << "\nHow much Jewels do you wish to deposit?" << endl;

                                        cout << "\nDeposited money: ";
                                        cin >> cash;

                                        bal = bal + cash;

                                        cout << "Current balance: " << bal << " Jewels." << endl;

                                        repeat = true;

                                    break;

                                    case 3: //Withdraw money
                                        cout << "\n--- Withdrawal ---" << endl;

                                        cout << "\nHow much Jewels do you wish to withdraw?" << endl;

                                        cout << "Withdrawn money: ";
                                        cin >> cash;

                                        bal = bal - cash;

                                        if (bal < 0){
                                            cout << "\nI'm sorry but you don't have enough Jewels to withdraw." << endl;
                                            bal = bal + cash;

                                            cout << "You only have " << bal << " Jewels in your account." << endl;

                                        } else {
                                            cout << "\nCurrent balance: " << bal << " Jewels." << endl;
                                        }

                                        repeat = true;

                                    break;

                                    case 4: // Logout account
                                        cout << "\n--- Logout ---" << endl;

                                        cout << "\nYou have successfully logged out of your account. Thank you for using CIIT SHS | ARC Bankings!" << endl;

                                        again = false;
                                        repeat = false;

                                    break;

                                    default:
                                        cout << "\nPlease choose among the given numbers only. Thank you!" << endl;

                                        repeat = true;

                                    break;
                                }
                            } while (repeat);

                        } else {
                            cout << "\nInvalid User ID or Password. Please try again." << endl;
                            retry = true;
                        }
                    } while (retry);
                    } else {
                        cout << "\nPlease create an account first.\n" << endl;
                        again = true;

                        break;
                    }

                break;

                case 3:
                    cout << "\n--- Exit ---" << endl;

                    cout << "\nExiting the program. Thank you for choosing CIIT SHS | ARC Bankings!" << endl;

                    again = false;

                break;

                default:
                    cout << "\nPlease choose among the given numbers only. Thank you!" << endl;
                    cout << endl;

                    again = true;
                break;
            }
    } while (again);

    return 0;
}
