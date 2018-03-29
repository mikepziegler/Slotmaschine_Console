import javax.swing.*;
import java.util.*;

public class CGUI extends JFrame {

    private CCapital capital;
    private CWheel wheel1, wheel2, wheel3;
    private Scanner s;

    public CGUI() {
        capital = new CCapital();

        wheel1 = new CWheel();
        wheel2 = new CWheel();
        wheel3 = new CWheel();

        String input = "";
        boolean bool = true;

        System.out.println("Hello and welcome to Slotmaschine_Console, probably the only slotmaschine you can play in the console or terminal. \nPlease type help if you have not played it ever before.\n\n");

        while (bool) {

            s = new Scanner(System.in);
            input = s.next();

            switch(input) {

                case "payin":
                    boolean b1 = true;
                    System.out.println("Please choose an amount for playing. Minimum amount: 0.50");
                    while (b1) {
                        b1 = !inputMoney();
                    }
                    System.out.println("Your capital now: " + capital.getCapital() + "\n");
                    break;
                case "makebet":
                    if (capital.getCapital() > 0) {
                        boolean b2 = true;
                        System.out.println("Please choose an amount to bet as long it is equal or lesser than your capital");
                        while (b2) {
                            b2 = !makeBet();
                        }
                        System.out.println("Your bet now: " + capital.getBet() + "\n");
                    } else {
                        System.out.println("Please pay in first, then you can make bets.");
                    }
                    break;
                case "roll":
                    pulltheLever();
                    break;
                case "payout":
                    payout();
                    break;

                case "capital":
                    System.out.println("\n---------------------\n");
                    System.out.println("Your capital: " + capital.getCapital() + "\n");
                    System.out.println("---------------------");
                    break;
                case "bet":
                    System.out.println("\n---------------------\n ");
                    System.out.println("Your bet: " + capital.getBet() + "\n");
                    System.out.println("---------------------\n");

                    break;
                case "clear":
                    for (int i = 0; i < 10; i++) {
                        System.out.println("\n \n \n \n \n \n \n \n \n");
                    }
                    break;

                case "help":

                    System.out.println("----------- Help -----------");
                    System.out.println("");
                    System.out.println("All the commands you can write in the console. They are all sorted in actions, infos and additional commands");
                    System.out.println("");

                    System.out.println("payin \t\t: You are paying in the slotmaschine, capital increases by the amount you want to pay.");
                    System.out.println("payout \t\t: You are paying out your capital from the slotmaschine.");
                    System.out.println("makebet \t: You bet an amount from your capital. ");
                    System.out.println(
                            "roll \t\t: You are starting the maschine. You should bet at least 0.50 for rolling the wheels \n " +
                            "\t\t\t  Each wheel randomly outputs one of ten symbols.  \n\n" +
                                    "\t\t\t\t - You win twice the bet, if all three wheels are showing the same symbol except the symbol 7\n" +
                                    "\t\t\t\t - You win the bet, if one wheel shows the symbol 7\n" +
                                    "\t\t\t\t - You win twice the bet, if two wheels are showing the symbol 7\n" +
                                    "\t\t\t\t - You win four times the bet, if all wheels are showing the symbol 7\n\n" +
                                    "\t\t\t  All of you win will be added in your capital directly"
                    );

                    System.out.println("");

                    System.out.println("capital \t: Gives you the current amount of your capital");
                    System.out.println("bet \t\t: Gives you the current amount of the bet you have set");
                    System.out.println("help \t\t: Gives you all commands you can execute. Actually you are looking at it right now. :)");
                    System.out.println("");

                    System.out.println("clear \t\t: Cleans up your console by making lots of empty lines");

                    System.out.println("");
                    System.out.println("exit \t\t: Exit the game, but it asks you if you want to pay your capital out, if there are any money left");

                    System.out.println("");
                    System.out.println("----------------------------\n");

                    break;

                case "exit":

                    boolean b3 = true;

                    System.out.println("You still have money in your capital. Do you want to cancel your exit? \nType Y for yes or N for no");

                    while (b3) {
                        if (capital.getCapital() > 0) {

                            String n = s.next();

                            if (n.indexOf("N") == 0) {
                                System.out.println("Ok, thanks for playing Slotmaschine_Console. See ya next time. :)");
                                bool = false;
                                b3 = false;
                            } else if (n.indexOf("Y") == 0) {
                                System.out.println("Good. I was shocked, when you wanted to exit without paying your money out.");
                                b3 = false;
                            } else {
                                System.out.println("Sry, you have to choose between Y for yes or N for no");
                            }

                        } else {
                            bool = false;
                            b3 = false;
                            System.out.println("Thanks for playing Slotmaschine_Console. See ya next time. :)");
                        }

                    }



                    break;

                default:

                    System.out.println("");
                    System.out.println("Unable to find command, please correct your command or write help");
                    System.out.println("");

                    break;
            }

        }

        System.out.println("Thanks for playing");
    }

    private void pulltheLever() {
        if (capital.checkCapital()) {
            if (capital.Betisset()) {
                int w1 = wheel1.rollWheel();
                int w2 = wheel2.rollWheel();
                int w3 = wheel3.rollWheel();

                String state = capital.checkSymbols(wheel1.rollWheel(), wheel1.rollWheel(), wheel3.rollWheel());

                switch(state) {
                    case "all":
                        System.out.println("Sie haben dreimal das gleiche Symbol gezogen");
                        break;
                    case "all7":
                        System.out.println("JACKPOT!!! Sie haben dreimal 7 gezogen");
                        break;
                    case "double7":
                        System.out.println("Sie haben zweimal 7 gezogen");
                        break;
                    case "one7":
                        System.out.println("Sie haben einmal 7 gezogen");
                        break;
                    case "null":
                        System.out.println("Sorry, you have lost " + capital.getBet());
                        break;
                    default:
                        break;
                }

                capital.removeBet();

                System.out.println("Your capital now: " + capital.getCapital() + "\n");

            } else {
                System.out.println("Sie haben noch keine Wette gemacht.");
            }
        } else {
            System.out.println("Sie müssen mindestens CHF 0.50 einzahlen um spielen zu können.");
        }
    }

    private boolean inputMoney() {

        String i = s.next();
        double value = 0;

        if (isdouble(i)) {
            value = Double.parseDouble(i);
        } else {
            System.out.println("Please do not input any letters, only numbers");
            return false;
        }
        if (value < 0.5) {
            System.out.println("The amount is below the minimum. Please change the amount, if you want to play");
            return false;
        }

        capital.inputMoney(value);

        return true;
    }

    private boolean makeBet() {
        String i = s.next();

        if (!isdouble(i)) {
            System.out.println("Sie haben keine Zahl eingegeben.");
            return false;
        }
        if (!capital.setBet(Double.parseDouble(i))) {
            System.out.println("Sie können keinen Betrag wetten, der grösser ist als ihr Kapital.");
            return false;
        }

        return true;
    }

    private static boolean isdouble(String i) {
        try {
            Double.parseDouble(i);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    private void payout() {
        capital.payout();
        System.out.println("Thanks for playing, here is your money.");
    }
}
