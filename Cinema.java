import java.util.Scanner;
public class Cinema {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int columns = sc.nextInt();
        CinemaHall cinema = new CinemaHall(rows, columns);
        char[][] seats = cinema.seatingArrangement();
        cinema.openCinema(seats);
    }
}
class CinemaHall {
    private int seatsTaken;
    private int totalSales;
    private int maxSale;
    private float percentage;
    private int rows;
    private int columns;
    public CinemaHall (int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seatsTaken = 0;
        this.totalSales = 0;
        this.maxSale = 0;
        this.percentage = 0;
    }
    public char[][] seatingArrangement() {
        char[][] seats = new char[this.rows][this.columns];
        for (int i = 0; i < rows; i++) {
            for (int p = 0; p < columns; p++) {
                seats[i][p] = 'S';
            }
        }
        if (this.rows * this.columns <= 60) {
            this.maxSale = 10 * this.rows * this.columns;
        } else {
            int front = this.rows / 2;
            int back = this.rows - front;
            this.maxSale = 10 * front * this.columns + 8 * back * this.columns;
        }
        return seats;
    }

    public char[][] getSeat(char[][] seats) {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter a row number:");
        int row = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int column = sc.nextInt();
        this.seatsTaken += 1;
        if (seats[row - 1][column - 1] != 'B') {
            seats[row - 1][column - 1] = 'B';
            this.getPrice(seats, row, column);
            return seats;
        } else {
            System.out.println();
            System.out.println("That ticket has already been purchased");
            return getSeat(seats);
        }
    }
    public void printCinema(char[][] seats) {
        System.out.println();
        System.out.println("Cinema:");
        System.out.print("  ");
        int rows = seats.length;
        int columns = seats[0].length;
        for (int i = 1; i <= columns; i++) {
            System.out.print(i + " ");
        }
        System.out.println("");
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1 + " ");
            for (int p = 0; p < columns; p++) {
                System.out.print(seats[i][p] + " ");
            }
            System.out.println("");
        }
    }
    public int getPrice(char[][] seats, int row, int column) {
        System.out.println();
        System.out.print("Ticket price: ");
        int rows = seats.length;
        int columns = seats[0].length;
        if (rows * columns <= 60) {
            System.out.println("$10");
            this.totalSales += 10;
            return 10;
        } else {
            int front = rows / 2;
            int back = rows - front;
            if(row <= front) {
                System.out.println("$10");
                this.totalSales += 10;
                return 10;
            } else {
                System.out.println("$8");
                this.totalSales += 8;
                return 8;
            }
        }
    }
    public void printMenu() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    public void getStats() {
        System.out.println();
        System.out.printf("Number of purchased tickets: %d \n", this.seatsTaken);
        System.out.printf("Percentage: %.2f%% \n", this.seatsTaken * 100 / (float)((this.rows * this.columns)));
        System.out.printf("Current income: $%d \n", this.totalSales);
        System.out.printf("Total income: $%d \n", this.maxSale);
    }

    public void openCinema(char[][] seats) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {

            this.printMenu();
            int menuChoice = sc.nextInt();
            if (menuChoice > 3) {
                System.out.println("Please enter correct choice!");
            }
            switch(menuChoice) {
                case 1 :
                    this.printCinema(seats);
                    break;
                case 2 :
                    try {
                        seats = this.getSeat(seats);
                        break;
                    } catch (Exception e) {
                        System.out.println("Wrong input!");
                        break;
                    }
                case 3 :
                    this.getStats();
                    break;
                case 0 :
                    flag = false;
                    break;
            }
        }
    }

}
