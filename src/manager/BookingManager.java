package manager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Booking;
import model.Customer;
import model.Seat;
import utils.CinemaValidator;
import utils.Constants;

/**
 * Quan ly tat ca booking bang HashMap (key = bookingId, value = Booking).
 * Moi booking giu tham chieu den dung doi tuong Seat trong CinemaHall.
 */
public class BookingManager {

    private final HashMap<String, Booking> bookingMap;
    private int nextBookingNumber;

    /** Khoi tao bookingMap rong va bo dem ma dat ve. */
    public BookingManager() {
        bookingMap = new HashMap<>();
        nextBookingNumber = Constants.FIRST_BOOKING_NUMBER;
    }

    /** Sinh ma dat ve moi, vi du BK-1001, BK-1002, ... */
    public String generateBookingId() {
        String bookingId;

        do {
            bookingId = Constants.BOOKING_ID_PREFIX + nextBookingNumber;
            nextBookingNumber++;
        } while (bookingMap.containsKey(bookingId));

        return bookingId;
    }

    /** Tao booking moi cho khach hang tai mot ghe dang trong. */
    public Booking createBooking(Customer customer, Seat seat) {
        if (customer == null || seat == null || !seat.isAvailable()) {
            return null;
        }

        // Khong tao booking neu ghe da duoc dat.
        if (!seat.reserve()) {
            return null;
        }

        String bookingId = generateBookingId();
        LocalDateTime bookingTime = LocalDateTime.now().withNano(0);
        Booking booking = new Booking(bookingId, customer, seat, bookingTime);
        bookingMap.put(bookingId, booking);
        return booking;
    }

    /** Huy booking theo ma va tra ghe ve trang thai trong. */
    public Booking cancelBooking(String bookingId) {
        String id = CinemaValidator.normalizeBookingId(bookingId);
        if (CinemaValidator.isNullOrEmpty(id)) {
            return null;
        }

        Booking booking = bookingMap.remove(id);
        if (booking == null) {
            return null;
        }

        // Booking luu dung tham chieu Seat cua CinemaHall.
        booking.getSeat().release();
        return booking;
    }

    /** Tim booking theo ma dat ve. */
    public Booking findById(String bookingId) {
        String id = CinemaValidator.normalizeBookingId(bookingId);
        if (CinemaValidator.isNullOrEmpty(id)) {
            return null;
        }
        return bookingMap.get(id);
    }

    /** Tra ve ban sao danh sach booking, khong lo lo cau truc HashMap goc. */
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookingMap.values());
    }

    /**
     * Khoi phuc booking doc tu file. Khong tao ma moi; chi them khi du lieu hop le,
     * ma chua ton tai va ghe dang trong.
     */
    public boolean restoreBooking(Booking booking) {
        if (booking == null || !CinemaValidator.isValidBooking(booking)) {
            return false;
        }

        String bookingId = CinemaValidator.normalizeBookingId(booking.getBookingId());
        if (bookingMap.containsKey(bookingId)) {
            return false;
        }

        Seat seat = booking.getSeat();
        if (seat == null || !seat.reserve()) {
            return false;
        }

        bookingMap.put(bookingId, booking);

        // Dam bao ma sinh tiep theo lon hon ma da khoi phuc.
        String numberPart = bookingId.substring(Constants.BOOKING_ID_PREFIX.length());
        try {
            int number = Integer.parseInt(numberPart);
            nextBookingNumber = Math.max(nextBookingNumber, number + 1);
        } catch (NumberFormatException e) {
            // isValidBookingId da kiem tra format; giu bo dem hien tai neu so qua lon.
        }

        return true;
    }

    /** Xoa booking va dat lai bo dem. CinemaHall.reset() se reset trang thai ghe. */
    public void clear() {
        bookingMap.clear();
        nextBookingNumber = Constants.FIRST_BOOKING_NUMBER;
    }

    /** Tra ve so luong booking hien co. */
    public int size() {
        return bookingMap.size();
    }
}
