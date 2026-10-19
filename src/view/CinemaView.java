package view;

import java.util.List;
import java.util.NoSuchElementException;
import manager.CinemaController;
import manager.CinemaHall;
import model.Booking;
import model.Customer;
import model.Seat;
import utils.Constants;

/**
 * Lop CinemaView dieu huong menu va hien thi du lieu dat ve (so do ghe, booking, hang cho, bao cao).
 * Lop nay chi noi chuyen voi CinemaController, khong tu thay doi trang thai ghe.
 */
public class CinemaView {

    private final CinemaController controller;
    private final View view;
    private boolean running;

    /**
     * Khoi tao CinemaView voi View dung chung.
     */
    public CinemaView(View view) {
        this.view = view;
        this.controller = new CinemaController();
        this.running = true;
    }

    /**
     * Chay vong lap menu chinh cua chuong trinh.
     */
    public void run() {
        try {
            view.showMessage(controller.loadFromFile());
        } catch (Exception e) {
            view.showMessage("Load data failed: " + e.getMessage());
        }
        while (running) {
            try {
                int choice = view.showMainMenu();
                switch (choice) {
                    case 1:
                        loadData();
                        break;
                    case 2:
                        showSeatMap();
                        break;
                    case 3:
                        bookSeat();
                        break;
                    case 4:
                        cancelBooking();
                        break;
                    case 5:
                        joinWaitingList();
                        break;
                    case 6:
                        showWaitingList();
                        break;
                    case 7:
                        searchBooking();
                        break;
                    case 8:
                        showReport();
                        break;
                    case 9:
                        saveData();
                        break;
                    case 10:
                        running = !quitProgram();
                        break;
                    default:
                        view.showMessage("This function is not available.");
                }
            } catch (NoSuchElementException e) {
                view.showMessage("Input stream closed. Program will exit.");
                running = false;
            } catch (Exception e) {
                view.showMessage("Unexpected error: " + e.getMessage());
            }
        }
    }

    /**
     * Chuc nang 1: load du lieu tu file bookings.txt va waitinglist.txt.
     */
    private void loadData() {
        if (controller.isChanged()) {
            boolean confirm = view.confirm("Current data has not been saved. Load file anyway?");
            if (!confirm) {
                return;
            }
        }
        view.showMessage(controller.loadFromFile());
    }

    /**
     * Chuc nang 2: hien thi so do ghe cua rap.
     */
    private void showSeatMap() {
        view.showMessage("---------------- Seat Map ----------------");
        printSeatMap();
    }

    /**
     * Chuc nang 3: dat ghe. Neu rap het ghe thi goi y nguoi dung vao hang cho.
     */
    private void bookSeat() {
        view.showMessage("---------------- Book A Seat ----------------");
        if (controller.isHallFull()) {
            view.showMessage("The hall is full. No seat is available.");
            if (view.confirm("Do you want to join the waiting list?")) {
                joinWaitingList();
            }
            return;
        }

        printSeatMap();
        String seatCode;
        while (true) {
            seatCode = view.readSeatCode("Seat code (" + view.getSeatCodeRangeText() + ", e.g. C4): ");
            if (controller.isSeatAvailable(seatCode)) {
                break;
            }
            view.showMessage("Seat " + seatCode + " is already booked.");
            if (!view.confirm("Do you want to choose another seat?")) {
                return;
            }
        }

        Customer customer = inputCustomer();
        Seat seat = controller.getHall().getSeatByCode(seatCode);
        view.showMessage("Seat " + seatCode + " | Price " + String.format("%,.0f", seat.getPrice())
                + " | Customer " + customer.getName());
        if (!view.confirm("Confirm this booking?")) {
            view.showMessage("Booking cancelled.");
            return;
        }
        view.showMessage(controller.bookSeat(customer, seatCode));
    }

    /**
     * Chuc nang 4: huy booking, ghe duoc tra se tu dong giao cho nguoi dau hang cho (neu co).
     */
    private void cancelBooking() {
        view.showMessage("---------------- Cancel Booking ----------------");
        String bookingId = view.readBookingId("Booking ID to cancel: ");
        Booking booking = controller.findBooking(bookingId);
        if (booking == null) {
            view.showMessage("Booking not found.");
            return;
        }
        view.showMessage(booking.getReceiptDetails());
        if (!view.confirm("Are you sure you want to cancel this booking?")) {
            view.showMessage("Cancel operation aborted.");
            return;
        }
        view.showMessage(controller.cancelBooking(bookingId));
    }

    /**
     * Chuc nang 5: dua khach vao hang cho (chi khi rap da het ghe).
     */
    private void joinWaitingList() {
        view.showMessage("---------------- Join Waiting List ----------------");
        if (!controller.isHallFull()) {
            view.showMessage("Seats are still available. Please book a seat instead of joining the waiting list.");
            return;
        }
        Customer customer = inputCustomer();
        if (!view.confirm("Add " + customer.getName() + " to the end of the waiting list?")) {
            view.showMessage("Operation aborted.");
            return;
        }
        view.showMessage(controller.joinWaitingList(customer));
    }

    /**
     * Chuc nang 6: hien thi hang cho theo thu tu FIFO kem so thu tu.
     */
    private void showWaitingList() {
        view.showMessage("---------------- Waiting List ----------------");
        List<Customer> waiting = controller.getWaitingList();
        if (waiting == null || waiting.isEmpty()) {
            view.showMessage("No customer is waiting.");
            return;
        }
        System.out.printf("%-4s | %-6s | %-22s | %-10s | %s%n", "No.", "ID", "Name", "Phone", "Email");
        System.out.println(repeat('-', 78));
        for (int i = 0; i < waiting.size(); i++) {
            Customer c = waiting.get(i);
            System.out.printf("%-4d | %-6s | %-22s | %-10s | %s%n",
                    i + 1, c.getCustomerId(), c.getName(), c.getPhone(), c.getEmail());
        }
        view.showMessage("Total waiting: " + waiting.size() + " customer(s). The first one is served first.");
    }

    /**
     * Chuc nang 7: tim booking theo ma dat ve hoac theo ten khach hang.
     */
    private void searchBooking() {
        boolean back = false;
        while (!back) {
            int choice = view.showSearchMenu();
            switch (choice) {
                case 1:
                    searchBookingById();
                    break;
                case 2:
                    searchBookingByName();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    view.showMessage("This function is not available.");
            }
        }
    }

    /**
     * Tim mot booking theo ma dat ve va in hoa don.
     */
    private void searchBookingById() {
        String bookingId = view.readBookingId("Booking ID: ");
        Booking booking = controller.findBooking(bookingId);
        if (booking == null) {
            view.showMessage("Booking not found.");
        } else {
            view.showMessage(booking.getReceiptDetails());
        }
    }

    /**
     * Tim cac booking theo tu khoa trong ten khach hang va in bang ket qua.
     */
    private void searchBookingByName() {
        String keyword = view.readRequiredString("Customer name keyword: ");
        printBookingTable(controller.searchBookingsByName(keyword));
    }

    /**
     * Chuc nang 8: bao cao booking (sap xep theo thoi gian, theo ten, thong ke tong hop).
     */
    private void showReport() {
        boolean back = false;
        while (!back) {
            int choice = view.showReportMenu();
            switch (choice) {
                case 1:
                    view.showMessage("---------- Bookings sorted by booking time ----------");
                    printBookingTable(controller.getBookingsSortedByTime());
                    break;
                case 2:
                    view.showMessage("---------- Bookings sorted by customer name ----------");
                    printBookingTable(controller.getBookingsSortedByName());
                    break;
                case 3:
                    printSummary();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    view.showMessage("This function is not available.");
            }
        }
    }

    /**
     * Chuc nang 9: luu du lieu xuong file.
     */
    private void saveData() {
        boolean result = controller.saveToFile();
        view.showMessage(result ? "Data saved successfully." : "Save data failed!");
    }

    /**
     * Chuc nang 10: thoat chuong trinh, hoi luu neu du lieu da thay doi. Tra ve true neu thoat.
     */
    private boolean quitProgram() {
        if (controller.isChanged()) {
            boolean save = view.confirm("Data has not been saved. Do you want to save before quitting?");
            if (save) {
                if (!controller.saveToFile()) {
                    view.showMessage("Save data failed! The program will not quit.");
                    return false;
                }
                view.showMessage("Data saved successfully.");
            } else if (!view.confirm("Quit without saving?")) {
                return false;
            }
        }
        view.showMessage("Goodbye!");
        return true;
    }

    /**
     * Nhap thong tin khach hang (dung chung cho dat ghe va vao hang cho).
     */
    private Customer inputCustomer() {
        String customerId = view.readCustomerId("Customer ID (C + 3 digits, e.g. C001): ");
        String name = view.readName("Customer name: ");
        String phone = view.readPhone("Phone (10 digits, starts with 0): ");
        String email = view.readEmail("Email: ");
        return new Customer(customerId, name, phone, email);
    }

    /**
     * Ve so do ghe: hang A-F, cot 0-9, [O] la ghe trong, [X] la ghe da dat.
     */
    private void printSeatMap() {
        CinemaHall hall = controller.getHall();
        int width = hall.getCols() * 5;
        System.out.println();
        System.out.println("    " + center(" SCREEN ", width, '='));
        System.out.println();

        StringBuilder header = new StringBuilder("    ");
        for (int c = 0; c < hall.getCols(); c++) {
            header.append(String.format("  %d  ", c));
        }
        System.out.println(header);

        for (int r = 0; r < hall.getRows(); r++) {
            StringBuilder line = new StringBuilder(String.format(" %c  ", (char) (Constants.FIRST_ROW_LETTER + r)));
            for (int c = 0; c < hall.getCols(); c++) {
                line.append(' ').append(hall.getSeat(r, c).getStatus().toSymbol()).append(' ');
            }
            System.out.println(line);
        }
        System.out.println();
        System.out.println("[O] available    [X] booked");
        System.out.println("Available seats: " + hall.countAvailableSeats() + " / " + hall.getTotalSeats());
    }

    /**
     * In bang danh sach booking, hoac thong bao neu danh sach rong.
     */
    private void printBookingTable(List<Booking> bookings) {
        if (bookings == null || bookings.isEmpty()) {
            view.showMessage("No booking found.");
            return;
        }
        System.out.printf("%-8s | %-6s | %-22s | %-10s | %-4s | %-19s | %9s%n",
                "Booking", "Cust", "Name", "Phone", "Seat", "Booked at", "Price");
        System.out.println(repeat('-', 98));
        for (Booking b : bookings) {
            Customer c = b.getCustomer();
            System.out.printf("%-8s | %-6s | %-22s | %-10s | %-4s | %-19s | %,9.0f%n",
                    b.getBookingId(), c.getCustomerId(), c.getName(), c.getPhone(),
                    b.getSeat().getSeatCode(),
                    b.getBookingTime().format(Constants.DATE_TIME_FORMATTER), b.getAmount());
        }
        view.showMessage("Total: " + bookings.size() + " booking(s).");
    }

    /**
     * In thong ke tong hop: so ghe, ty le lap day, doanh thu, so khach dang cho.
     */
    private void printSummary() {
        CinemaHall hall = controller.getHall();
        int booked = controller.getBookedSeatCount();
        System.out.println("---------------- Summary ----------------");
        System.out.println("Total seats       : " + hall.getTotalSeats());
        System.out.println("Booked seats      : " + booked);
        System.out.println("Available seats   : " + (hall.getTotalSeats() - booked));
        System.out.printf("Occupancy rate    : %.1f%%%n", controller.getOccupancyRate());
        System.out.printf("Total revenue     : %,.0f%n", controller.getTotalRevenue());
        System.out.println("Waiting customers : " + controller.getWaitingCount());
    }

    /**
     * Tao chuoi lap lai mot ky tu n lan (thay cho String.repeat cua Java 11).
     */
    private String repeat(char ch, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * Can giua mot doan chu trong do rong cho truoc, phan thua duoc dien bang ky tu fill.
     */
    private String center(String text, int width, char fill) {
        int left = Math.max(0, (width - text.length()) / 2);
        int right = Math.max(0, width - text.length() - left);
        return repeat(fill, left) + text + repeat(fill, right);
    }
}
