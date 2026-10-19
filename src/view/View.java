package view;

import java.util.Scanner;
import utils.CinemaValidator;
import utils.Constants;

/**
 * Lop View xu ly viec nhap xuat co ban tren console (menu, doc du lieu co validate, thong bao).
 */
public class View {

    private final Scanner scanner;

    /**
     * Khoi tao View voi Scanner dung chung.
     */
    public View(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Hien thi menu chinh va tra ve lua chon cua nguoi dung.
     */
    public int showMainMenu() {
        System.out.println();
        System.out.println("========== Cinema Seat Reservation & Waiting List System ==========");
        String[] options = {
            "Load data from file",
            "View seat map",
            "Book a seat",
            "Cancel a booking",
            "Join the waiting list",
            "View the waiting list",
            "Search bookings",
            "Booking reports",
            "Save data to file",
            "Quit program"
        };
        Menu.showMenu(options);
        return readInt("", 1, 10);
    }

    /**
     * Hien thi menu tim kiem booking va tra ve lua chon.
     */
    public int showSearchMenu() {
        System.out.println();
        System.out.println("---------------- Search Bookings ----------------");
        String[] options = {"Search by booking ID", "Search by customer name", "Back"};
        Menu.showMenu(options);
        return readInt("", 1, 3);
    }

    /**
     * Hien thi menu bao cao va tra ve lua chon.
     */
    public int showReportMenu() {
        System.out.println();
        System.out.println("---------------- Booking Reports ----------------");
        String[] options = {
            "Bookings sorted by booking time",
            "Bookings sorted by customer name",
            "Summary statistics",
            "Back"
        };
        Menu.showMenu(options);
        return readInt("", 1, 4);
    }

    /**
     * Doc so nguyen trong khoang min max.
     */
    public int readInt(String prompt, int min, int max) {
        while (true) {
            try {
                if (prompt != null && !prompt.isEmpty()) {
                    System.out.print(prompt);
                }
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value < min || value > max) {
                    System.out.println("Value must be from " + min + " to " + max + ".");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Please try again.");
            }
        }
    }

    /**
     * Doc chuoi khong rong.
     */
    public String readRequiredString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println("This field cannot be empty.");
            } else {
                return value;
            }
        }
    }

    /**
     * Doc ma dat ve va tu chuan hoa thanh chu hoa.
     */
    public String readBookingId(String prompt) {
        while (true) {
            String id = CinemaValidator.normalizeBookingId(readRequiredString(prompt));
            if (CinemaValidator.isValidBookingId(id)) {
                return id;
            }
            System.out.println("Booking ID must be BK- followed by at least 4 digits, for example BK-1001.");
        }
    }

    /**
     * Doc ma ghe va tu chuan hoa thanh chu hoa (A0 den F9).
     */
    public String readSeatCode(String prompt) {
        while (true) {
            String code = CinemaValidator.normalizeSeatCode(readRequiredString(prompt));
            if (CinemaValidator.isValidSeatCode(code)) {
                return code;
            }
            System.out.println("Seat code must be a row letter followed by a column digit, from "
                    + getSeatCodeRangeText() + ", for example C4.");
        }
    }

    /**
     * Doc ma khach hang va tu chuan hoa thanh chu hoa.
     */
    public String readCustomerId(String prompt) {
        while (true) {
            String id = CinemaValidator.normalizeCustomerId(readRequiredString(prompt));
            if (CinemaValidator.isValidCustomerId(id)) {
                return id;
            }
            System.out.println("Customer ID must be C followed by 3 digits, for example C001.");
        }
    }

    /**
     * Doc ten khach hang, tu gop khoang trang thua va kiem tra chi gom chu cai.
     */
    public String readName(String prompt) {
        while (true) {
            String name = CinemaValidator.normalizeName(readRequiredString(prompt));
            if (CinemaValidator.isValidName(name)) {
                return name;
            }
            System.out.println("Name must contain letters and single spaces only (no digits, no commas), "
                    + "maximum " + Constants.MAX_NAME_LENGTH + " characters.");
        }
    }

    /**
     * Doc so dien thoai (10 chu so, bat dau bang 0).
     */
    public String readPhone(String prompt) {
        while (true) {
            String phone = readRequiredString(prompt);
            if (CinemaValidator.isValidPhone(phone)) {
                return phone;
            }
            System.out.println("Phone must have 10 digits and start with 0, for example 0901234567.");
        }
    }

    /**
     * Doc email dung dinh dang.
     */
    public String readEmail(String prompt) {
        while (true) {
            String email = readRequiredString(prompt);
            if (CinemaValidator.isValidEmail(email)) {
                return email;
            }
            System.out.println("Invalid email format, for example an.nguyen@mail.com.");
        }
    }

    /**
     * Hien thi thong bao ra man hinh.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Hoi nguoi dung xac nhan yes/no.
     */
    public boolean confirm(String message) {
        while (true) {
            System.out.print(message + " (Y/N): ");
            String answer = scanner.nextLine().trim();
            if (answer.equalsIgnoreCase("Y")) {
                return true;
            }
            if (answer.equalsIgnoreCase("N")) {
                return false;
            }
            System.out.println("Please input Y or N.");
        }
    }

    /**
     * Tao chuoi mo ta khoang ma ghe hop le dua tren Constants, vi du "A0 - F9".
     */
    public String getSeatCodeRangeText() {
        char lastRow = (char) (Constants.FIRST_ROW_LETTER + Constants.ROWS - 1);
        return Constants.FIRST_ROW_LETTER + "0 - " + lastRow + (Constants.COLS - 1);
    }
}
