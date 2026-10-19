package manager;

import model.Seat;
import utils.CinemaValidator;
import utils.Constants;

/**
 * Lop CinemaHall quan ly so do ghe cua rap bang mang 2 chieu Seat[][].
 * Ma ghe gom 1 chu cai (hang A-F) + 1 chu so (cot 0-9), vi du C4 la seats[2][4].
 * Day la noi DUY NHAT luu trang thai ghe da dat hay con trong.
 */
public class CinemaHall {

    private final int rows;
    private final int cols;
    private final Seat[][] seats;

    /**
     * Khoi tao rap voi kich thuoc mac dinh trong Constants (6 hang x 10 cot = 60 ghe).
     */
    public CinemaHall() {
        this(Constants.ROWS, Constants.COLS);
    }

    /**
     * Khoi tao rap voi so hang va so cot tuy chinh, moi ghe duoc tao san o trang thai trong.
     */
    public CinemaHall(int rows, int cols) {
        if (rows < 1 || rows > Constants.MAX_ROWS_SUPPORTED
                || cols < 1 || cols > Constants.MAX_COLS_SUPPORTED) {
            throw new IllegalArgumentException("Hall size must be 1-" + Constants.MAX_ROWS_SUPPORTED
                    + " rows and 1-" + Constants.MAX_COLS_SUPPORTED + " columns.");
        }
        this.rows = rows;
        this.cols = cols;
        this.seats = new Seat[rows][cols];
        initializeSeats();
    }

    /**
     * Tao tung doi tuong Seat cho toan bo mang 2 chieu (chi chay mot lan khi khoi tao).
     */
    private void initializeSeats() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                seats[r][c] = new Seat(r, c, Constants.DEFAULT_SEAT_PRICE);
            }
        }
    }

    /**
     * Lay so hang cua rap.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Lay so cot cua rap.
     */
    public int getCols() {
        return cols;
    }

    /**
     * Lay tong so ghe cua rap (rows x cols).
     */
    public int getTotalSeats() {
        return rows * cols;
    }

    /**
     * Kiem tra vi tri (row, col) co nam trong so do ghe hay khong.
     */
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * Lay ghe theo chi so hang va cot, tra ve null neu vi tri nam ngoai so do. Do phuc tap O(1).
     */
    public Seat getSeat(int row, int col) {
        if (!isValidPosition(row, col)) {
            return null;
        }
        return seats[row][col];
    }

    /**
     * Lay ghe theo ma ghe (khong phan biet hoa thuong), vi du "c4" tra ve ghe o seats[2][4].
     * Tra ve null neu ma ghe sai format hoac nam ngoai so do. Do phuc tap O(1).
     */
    public Seat getSeatByCode(String seatCode) {
        String code = CinemaValidator.normalizeSeatCode(seatCode);
        if (!CinemaValidator.isValidSeatCode(code)) {
            return null;
        }
        int row = code.charAt(0) - Constants.FIRST_ROW_LETTER;
        int col = code.charAt(1) - Constants.FIRST_COL_DIGIT;
        return getSeat(row, col);
    }

    /**
     * Kiem tra ghe theo ma co ton tai va con trong hay khong.
     */
    public boolean isSeatAvailable(String seatCode) {
        Seat seat = getSeatByCode(seatCode);
        return seat != null && seat.isAvailable();
    }

    /**
     * Dem so ghe con trong bang cach duyet toan bo mang. Do phuc tap O(rows x cols).
     */
    public int countAvailableSeats() {
        int count = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (seats[r][c].isAvailable()) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Dem so ghe da duoc dat.
     */
    public int countBookedSeats() {
        return getTotalSeats() - countAvailableSeats();
    }

    /**
     * Kiem tra rap da het ghe hay chua (khong con ghe trong nao).
     */
    public boolean isFull() {
        return countAvailableSeats() == 0;
    }

    /**
     * Dua toan bo ghe ve trang thai trong (giu nguyen cac doi tuong Seat). Dung truoc khi load file.
     */
    public void reset() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                seats[r][c].release();
            }
        }
    }
}
