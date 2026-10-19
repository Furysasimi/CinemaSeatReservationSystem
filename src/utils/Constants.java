package utils;

import java.time.format.DateTimeFormatter;

/**
 * Lop Constants chua cac hang so dung chung cho toan bo chuong trinh.
 */
public final class Constants {

    private Constants() {
    }

    // ---------------- File du lieu ----------------

    /** Duong dan file luu danh sach booking. */
    public static final String BOOKING_FILE = "src/file/bookings.txt";

    /** Duong dan file luu hang cho (thu tu dong trong file = thu tu FIFO). */
    public static final String WAITING_FILE = "src/file/waitinglist.txt";

    /** Chuoi noi cac truong khi GHI file. */
    public static final String FILE_SEPARATOR = ", ";

    /** Regex tach cac truong khi DOC file. */
    public static final String FILE_SPLIT_REGEX = ",";

    // ---------------- Kich thuoc rap ----------------

    /** So hang ghe: A, B, C, D, E, F. */
    public static final int ROWS = 6;

    /** So cot ghe: 0, 1, 2, ..., 9. */
    public static final int COLS = 10;

    /** Tong so ghe cua rap (6 x 10 = 60). */
    public static final int TOTAL_SEATS = ROWS * COLS;

    /** Ky tu cua hang dau tien (hang co chi so 0). */
    public static final char FIRST_ROW_LETTER = 'A';

    /** Ky tu cua cot dau tien (cot co chi so 0). */
    public static final char FIRST_COL_DIGIT = '0';

    /** Gioi han ma ghe gom 1 chu cai (A-Z) nen toi da 26 hang. */
    public static final int MAX_ROWS_SUPPORTED = 26;

    /** Gioi han ma ghe gom 1 chu so (0-9) nen toi da 10 cot. */
    public static final int MAX_COLS_SUPPORTED = 10;

    /** Gia ve mac dinh cua moi ghe (VND). */
    public static final double DEFAULT_SEAT_PRICE = 80000;

    // ---------------- Booking va khach hang ----------------

    /** Tien to cua ma dat ve, vi du BK-1001. */
    public static final String BOOKING_ID_PREFIX = "BK-";

    /** So thu tu cua booking dau tien. */
    public static final int FIRST_BOOKING_NUMBER = 1001;

    /** Do dai toi da cua ten khach hang. */
    public static final int MAX_NAME_LENGTH = 50;

    // ---------------- Thoi gian ----------------

    /** Dinh dang thoi gian luu trong file va hien thi. */
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /** Bo dinh dang thoi gian dung chung (doc/ghi file, in hoa don). */
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
}
