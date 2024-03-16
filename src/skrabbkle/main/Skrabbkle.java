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

    private void printDash() {

    }

    private void printSpace() {

    }
}
