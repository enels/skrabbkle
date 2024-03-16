package skrabbkle.main;

public class Skrabbkle {

    public void startGame() throws Exception{

        printMenu();
    }

    private void printMenu() throws Exception{

        for (int i = 0; i < 3; i++) {
            printDash();
            // when on second line
            if (i == 1) {
                System.out.print(" S k r a B B K l e ");

            }
            else {
                printSpace();
            }
            printDash();
            System.out.println();
        } // end of for loop
    }

    /**
     * print the dash lines
     */
    private void printDash() {
        for (int i = 0; i < 12; i++) {
            System.out.print("=");
        }
    }

    /**
     * print the spaces between the dash lines
     */
    private void printSpace() {

    }
}
