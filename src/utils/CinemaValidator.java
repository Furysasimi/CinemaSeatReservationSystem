package utils;

import model.Booking;
import model.Customer;

/**
 * Lop CinemaValidator chua cac ham kiem tra va chuan hoa du lieu cua he thong dat ve.
 */
public final class CinemaValidator {

    private CinemaValidator() {
    }

    /**
     * Kiem tra chuoi co null hoac rong hay khong.
     */
    public static boolean isNullOrEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }

    /**
     * Chuan hoa ma khach hang thanh chu hoa, vi du c001 thanh C001.
     */
    public static String normalizeCustomerId(String id) {
        if (id == null) {
            return null;
        }
        return id.trim().toUpperCase();
    }

    /**
     * Chuan hoa ma dat ve thanh chu hoa, vi du bk-1001 thanh BK-1001.
     */
    public static String normalizeBookingId(String id) {
        if (id == null) {
            return null;
        }
        return id.trim().toUpperCase();
    }

    /**
     * Chuan hoa ma ghe thanh chu hoa, vi du c4 thanh C4.
     */
    public static String normalizeSeatCode(String code) {
        if (code == null) {
            return null;
        }
        return code.trim().toUpperCase();
    }

    /**
     * Chuan hoa ten: bo khoang trang dau cuoi va gop nhieu dau cach thanh 1 dau cach.
     */
    public static String normalizeName(String name) {
        if (name == null) {
            return null;
        }
        return name.trim().replaceAll("\\s+", " ");
    }

    /**
     * Kiem tra ma khach hang dung format C + 3 chu so hay khong.
     */
    public static boolean isValidCustomerId(String id) {
        return Acceptable.isValid(id, Acceptable.CUSTOMER_ID);
    }

    /**
     * Kiem tra ma dat ve dung format BK- + it nhat 4 chu so hay khong.
     */
    public static boolean isValidBookingId(String id) {
        return Acceptable.isValid(id, Acceptable.BOOKING_ID);
    }

    /**
     * Kiem tra ma ghe dung format 1 chu cai A-F + 1 chu so 0-9 hay khong.
     */
    public static boolean isValidSeatCode(String code) {
        return Acceptable.isValid(code, Acceptable.SEAT_CODE);
    }

    /**
     * Kiem tra ten khach hang chi gom chu cai, dau cach va khong qua do dai toi da.
     */
    public static boolean isValidName(String name) {
        return name != null
                && name.length() <= Constants.MAX_NAME_LENGTH
                && Acceptable.isValid(name, Acceptable.PERSON_NAME);
    }

    /**
     * Kiem tra so dien thoai gom 10 chu so va bat dau bang 0.
     */
    public static boolean isValidPhone(String phone) {
        return Acceptable.isValid(phone, Acceptable.PHONE);
    }

    /**
     * Kiem tra email dung dinh dang co ban.
     */
    public static boolean isValidEmail(String email) {
        return Acceptable.isValid(email, Acceptable.EMAIL);
    }

    /**
     * Chuan hoa du lieu cua khach hang (tren chinh doi tuong) roi kiem tra tung truong.
     */
    public static boolean isValidCustomer(Customer customer) {
        if (customer == null) {
            return false;
        }
        customer.setCustomerId(normalizeCustomerId(customer.getCustomerId()));
        customer.setName(normalizeName(customer.getName()));
        if (customer.getPhone() != null) {
            customer.setPhone(customer.getPhone().trim());
        }
        if (customer.getEmail() != null) {
            customer.setEmail(customer.getEmail().trim());
        }
        return isValidCustomerId(customer.getCustomerId())
                && isValidName(customer.getName())
                && isValidPhone(customer.getPhone())
                && isValidEmail(customer.getEmail());
    }

    /**
     * Kiem tra mot booking day du va hop le (ma dat ve, khach hang, ghe, thoi gian).
     */
    public static boolean isValidBooking(Booking booking) {
        if (booking == null) {
            return false;
        }
        return isValidBookingId(booking.getBookingId())
                && isValidCustomer(booking.getCustomer())
                && booking.getSeat() != null
                && booking.getBookingTime() != null;
    }
}
