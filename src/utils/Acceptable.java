package utils;

/**
 * Interface Acceptable chua cac mau regex hop le va ham kiem tra chuoi theo mau.
 */
public interface Acceptable {

    /** Ma khach hang: chu C + 3 chu so, vi du C001. */
    String CUSTOMER_ID = "(?i)^C\\d{3}$";

    /** Ma dat ve: BK- + tu 4 chu so tro len, vi du BK-1001. */
    String BOOKING_ID = "(?i)^BK-\\d{4,}$";

    /** Ma ghe: 1 chu cai A-F + 1 chu so 0-9, vi du C4 (A0 la ghe dau, F9 la ghe cuoi). */
    String SEAT_CODE = "(?i)^[A-F][0-9]$";

    /** So dien thoai: 10 chu so, bat dau bang 0. */
    String PHONE = "^0\\d{9}$";

    /** Email co dang ten@ten-mien.duoi. */
    String EMAIL = "^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$";

    /** Ten nguoi: chi gom chu cai (co dau) va dung 1 dau cach giua cac tu, khong co dau phay. */
    String PERSON_NAME = "^[\\p{L}\\p{M}]+(?: [\\p{L}\\p{M}]+)*$";

    /**
     * Kiem tra chuoi co khop voi mau regex hay khong (null thi tra ve false).
     */
    static boolean isValid(String data, String pattern) {
        return data != null && data.trim().matches(pattern);
    }
}
