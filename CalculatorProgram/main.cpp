#include <iostream>
#include <windows.h>

using namespace std;

bool tryAgain();

int main()
{
    char op;
    double num1, num2;
    bool again = true;

    do {
        system("color 7");
        cout << "\t\t\t\t\t\t    ----------------" << endl;
        cout << "\t\t\t\t\t\t   |   CALCULATOR   |" << endl;
        cout << "\t\t\t\t\t\t    ----------------" << endl;
        cout << endl;

        cout << "   Solve for: ";
        cin >> num1 >> op >> num2;

        if (num1 && num2) {
            switch(op){
                case '+':
                    cout << "\n   Result: " << num1 + num2 << endl;
                    again = tryAgain();
                break;

                case '-':
                    cout << "\n   Result: " << num1 - num2 << endl;
                    again = tryAgain();
                break;

                case '*':
                    cout << "\n   Result: " << num1 * num2 << endl;
                    again = tryAgain();
                break;

                case '/':
                    cout << "\n   Result: " << num1 / num2 << endl;
                    again = tryAgain();
                break;

                case '%':
                    cout << "\n   Result: " << int (num1) % int (num2) << endl;
                    again = tryAgain();
                break;

                default:
                    system("cls");
                    system("color c");
                    cout << "\n   Invalid operator! Try again." << endl;
                    Sleep(2000);
                    system("cls");
                break;
            }
        } else {
            system("cls");
            system("color c");
            cout << "\n   Invalid input! Please input a numerical value. Try again." << endl;
            Sleep(2000);
            system("cls");
            system("color 7");
            break;
        }
    } while(again);

    return 0;
}

bool tryAgain() {
    char ans;
    bool invalidInput;
    bool again;

    do {
        invalidInput = false;
        cout << "\n   Would you like to go again? [Enter 'y' or 'n']: ";
        cin >> ans;

        ans = toupper(ans);

        switch(ans){
            case 'Y':
                again = true;
                system("cls");
            break;

            case 'N':
                again = false;
                cout << "\n   Thank you for using Calculator!" << endl;
            break;

            default:
                invalidInput = true;
                cout << "\n   Invalid input! Try again.";
                Sleep(2000);
                for (int i = 0; i < 3 ; i++){
                    cout << "\x1b[2K" << "\x1b[1A";
                }
            break;
        }
    } while (invalidInput);

    return again;
}
