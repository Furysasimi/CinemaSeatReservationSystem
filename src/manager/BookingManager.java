package manager;

import java.util.HashMap;
import java.util.List;
import model.Booking;
import model.Customer;
import model.Seat;
import utils.Constants;

/**
 * Lop BookingManager quan ly tat ca booking bang HashMap (key = bookingId, value = Booking).
 * Moi booking giu tham chieu den dung doi tuong Seat trong CinemaHall, nen khi doi trang thai
 * ghe (reserve/release) thi so do ghe va booking luon khop nhau.
 *
 * LUU Y: chi luu booking o MOT cho duy nhat (bookingMap). Khi can danh sach de sap xep hoac
 * bao cao thi tao ban sao tu bookingMap.values(), khong giu them ArrayList thu hai.
 *
 * TRANG THAI: KHUNG TRONG - cac phuong thuc ben duoi can duoc cai dat.
 */
public class BookingManager {

    private final HashMap<String, Booking> bookingMap;
    private int nextBookingNumber;

    /**
     * Khoi tao bookingMap rong va dat so thu tu booking bat dau tu Constants.FIRST_BOOKING_NUMBER.
     */
    public BookingManager() {
        bookingMap = new HashMap<>();
        nextBookingNumber = Constants.FIRST_BOOKING_NUMBER;
    }

    /**
     * Sinh ma dat ve moi, vi du BK-1001, BK-1002, ...
     *
     * Tra ve: chuoi ma dat ve chua ton tai trong bookingMap.
     * Goi y: Constants.BOOKING_ID_PREFIX + nextBookingNumber roi tang nextBookingNumber len 1.
     * Do phuc tap: O(1).
     */
    public String generateBookingId() {
        // TODO: cai dat sinh ma dat ve
        throw new UnsupportedOperationException("TODO: BookingManager.generateBookingId chua duoc cai dat.");
    }

    /**
     * Tao booking moi cho khach hang tai mot ghe dang trong.
     *
     * Tham so: customer (da validate), seat (lay tu CinemaHall).
     * Tra ve: Booking vua tao, hoac null neu khong dat duoc ghe (ghe null hoac da bi dat).
     * Goi y:
     *  1. Neu seat == null thi tra ve null.
     *  2. Goi seat.reserve(); neu tra ve false (ghe da dat) thi tra ve null.
     *  3. Sinh bookingId, tao Booking voi LocalDateTime.now().withNano(0)
     *     (bo nano giay de gia tri trong bo nho giong het gia tri doc lai tu file).
     *  4. bookingMap.put(bookingId, booking) roi tra ve booking.
     * Do phuc tap: O(1).
     */
    public Booking createBooking(Customer customer, Seat seat) {
        // TODO: cai dat tao booking
        throw new UnsupportedOperationException("TODO: BookingManager.createBooking chua duoc cai dat.");
    }

    /**
     * Huy booking theo ma dat ve: xoa khoi bookingMap VA tra ghe ve trang thai trong.
     *
     * Tra ve: Booking da bi huy (de controller biet ghe nao vua duoc giai phong,
     *         dung booking.getSeat() de chuyen ghe cho nguoi dau hang cho), hoac null neu khong tim thay.
     * Goi y:
     *  1. Booking b = bookingMap.remove(bookingId); neu b == null thi tra ve null.
     *  2. b.getSeat().release() de ghe tro lai trang thai AVAILABLE.
     *  3. Tra ve b.
     * Do phuc tap: O(1) trung binh (vi chi co mot cau truc luu tru).
     */
    public Booking cancelBooking(String bookingId) {
        // TODO: cai dat huy booking
        throw new UnsupportedOperationException("TODO: BookingManager.cancelBooking chua duoc cai dat.");
    }

    /**
     * Tim booking theo ma dat ve (tra cuu bang HashMap).
     *
     * Tra ve: Booking tim duoc, hoac null neu khong co.
     * Do phuc tap: O(1) trung binh.
     */
    public Booking findById(String bookingId) {
        // TODO: cai dat tim theo ma
        throw new UnsupportedOperationException("TODO: BookingManager.findById chua duoc cai dat.");
    }

    /**
     * Lay ban sao danh sach tat ca booking (de sap xep, tim kiem hoac bao cao ma khong lam hong bookingMap).
     *
     * Tra ve: ArrayList moi chua cac gia tri cua bookingMap (thu tu khong dam bao).
     * Goi y: new ArrayList<>(bookingMap.values()).
     * Do phuc tap: O(N).
     */
    public List<Booking> getAllBookings() {
        // TODO: cai dat lay danh sach booking
        throw new UnsupportedOperationException("TODO: BookingManager.getAllBookings chua duoc cai dat.");
    }

    /**
     * Khoi phuc mot booking doc tu file: dat lai ghe va them vao bookingMap (khong sinh ma moi).
     *
     * Tra ve: true neu khoi phuc thanh cong, false neu trung ma dat ve hoac ghe da bi dat.
     * Goi y:
     *  1. Neu bookingMap da co ma nay thi tra ve false.
     *  2. Goi booking.getSeat().reserve(); neu tra ve false thi tra ve false.
     *  3. bookingMap.put(...).
     *  4. Cap nhat nextBookingNumber de ma moi khong trung: lay phan so cua ma
     *     (bo tien to Constants.BOOKING_ID_PREFIX, Integer.parseInt) roi dat
     *     nextBookingNumber = max(nextBookingNumber, so + 1).
     * Do phuc tap: O(1).
     */
    public boolean restoreBooking(Booking booking) {
        // TODO: cai dat khoi phuc booking tu file
        throw new UnsupportedOperationException("TODO: BookingManager.restoreBooking chua duoc cai dat.");
    }

    /**
     * Xoa toan bo booking va dat lai bo dem ma dat ve ve Constants.FIRST_BOOKING_NUMBER.
     * Ham nay KHONG tra ghe (viec do la cua hall.reset(), controller se goi truoc khi load file).
     */
    public void clear() {
        // TODO: cai dat xoa toan bo booking
        throw new UnsupportedOperationException("TODO: BookingManager.clear chua duoc cai dat.");
    }

    /**
     * Lay so luong booking hien co.
     */
    public int size() {
        // TODO: cai dat dem booking
        throw new UnsupportedOperationException("TODO: BookingManager.size chua duoc cai dat.");
    }
}
