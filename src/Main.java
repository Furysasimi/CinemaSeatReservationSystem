import java.util.Scanner;
import view.CinemaView;
import view.View;

/**
 * Lop Main la diem vao cua chuong trinh Cinema Seat Reservation & Waiting List System (Topic 11).
 */
public class Main {

    /**
     * Khoi tao Scanner, View va CinemaView roi chay vong lap menu chinh.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        View view = new View(scanner);
        CinemaView cinemaView = new CinemaView(view);
        cinemaView.run();
    }
}
