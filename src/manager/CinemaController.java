package manager;

import file.BookingFileHelper;
import file.WaitingListFileHelper;
import java.util.List;
import model.Booking;
import model.Customer;
import utils.CinemaValidator;

/**
 * Lop CinemaController dieu phoi toan bo nghiep vu he thong dat ve (giong EmployeeController trong Lab 3).
 * View CHI noi chuyen voi lop nay; lop nay phoi hop CinemaHall, BookingManager, WaitingListManager,
 * BookingReportService va hai file helper.
 *
 * QUY TAC BAT BIEN CAN GIU:
 *  - Hang cho chi co nguoi khi rap da het ghe (hall.isFull() == true).
 *  - Khi huy booking ma hang cho khac rong: ghe vua tra phai duoc giao ngay cho nguoi dau hang
 *    (tao booking moi cho nguoi do), luc nay rap lai full va hang cho ngan di 1 nguoi.
 *  - Moi thay doi du lieu thanh cong (dat, huy, vao hang cho) phai dat changed = true.
 *
 * TRANG THAI: phan khoi tao va hai ham get/is don gian da co san, cac ham nghiep vu con lai la KHUNG TRONG.
 */
public class CinemaController {

    private final CinemaHall hall;
    private final BookingManager bookingManager;
    private final WaitingListManager waitingList;
    private final BookingReportService reportService;
    private final BookingFileHelper bookingFile;
    private final WaitingListFileHelper waitingFile;
    private boolean changed;

    /**
     * Khoi tao controller: tao rap 6x10, cac manager, service va hai file helper (da noi san voi nhau).
     */
    public CinemaController() {
        hall = new CinemaHall();
        bookingManager = new BookingManager();
        waitingList = new WaitingListManager();
        reportService = new BookingReportService();
        bookingFile = new BookingFileHelper(hall);
        waitingFile = new WaitingListFileHelper();
        changed = false;
    }

    /**
     * Lay rap de View ve so do ghe (View chi DOC thong tin, khong sua trang thai ghe).
     */
    public CinemaHall getHall() {
        return hall;
    }

    /**
     * Kiem tra du lieu da bi thay doi ma chua luu xuong file hay khong.
     */
    public boolean isChanged() {
        return changed;
    }

    /**
     * Load du lieu tu bookings.txt va waitinglist.txt, dung lai so do ghe va hang cho.
     *
     * Tra ve: thong bao tong ket de View in ra (so booking, so dong bi bo qua, so nguoi cho).
     * Goi y cac buoc:
     *  1. hall.reset(); bookingManager.clear(); waitingList.clear();  (xoa du lieu cu)
     *  2. List<Booking> bookings = bookingFile.read();  (helper tu bo qua dong sai/trung ma/trung ghe)
     *  3. Voi moi booking: bookingManager.restoreBooking(booking)  (ham nay se reserve ghe)
     *  4. List<Customer> waiting = waitingFile.read(); voi moi khach: waitingList.enqueue(customer)
     *  5. changed = false; tra ve thong bao, co the dung bookingFile.getSkippedLines(), getTotalLines().
     * Co the them: neu hang cho khac rong ma rap chua full thi bao du lieu khong nhat quan.
     */
    public String loadFromFile() {
        // TODO: cai dat load du lieu tu file
        throw new UnsupportedOperationException("TODO: CinemaController.loadFromFile chua duoc cai dat.");
    }

    /**
     * Luu booking va hang cho xuong file.
     *
     * Tra ve: true neu ca hai file ghi thanh cong.
     * Goi y: bookingFile.write(bookingManager.getAllBookings()) va
     *        waitingFile.write(waitingList.getAll()); thanh cong het thi changed = false.
     *        (Co the sortByTime danh sach booking truoc khi ghi de file de doc hon.)
     */
    public boolean saveToFile() {
        // TODO: cai dat luu du lieu xuong file
        throw new UnsupportedOperationException("TODO: CinemaController.saveToFile chua duoc cai dat.");
    }

    /**
     * Kiem tra rap da het ghe hay chua (View dung de goi y nguoi dung vao hang cho).
     *
     * Goi y: return hall.isFull().
     */
    public boolean isHallFull() {
        return hall.isFull();
    }

    /**
     * Kiem tra mot ma ghe co ton tai va con trong hay khong (View kiem tra truoc khi hoi thong tin khach).
     *
     * Goi y: return hall.isSeatAvailable(seatCode).
     */
    public boolean isSeatAvailable(String seatCode) {
        // TODO: cai dat kiem tra ghe con trong
        throw new UnsupportedOperationException("TODO: CinemaController.isSeatAvailable chua duoc cai dat.");
    }

    /**
     * Dat ghe cho khach hang.
     *
     * Tham so: customer (khach hang vua nhap), seatCode (ma ghe da chuan hoa, vi du C4).
     * Tra ve: thong bao ket qua; neu thanh cong thi tra ve hoa don booking.getReceiptDetails().
     * Goi y cac buoc:
     *  1. Validate lai bang CinemaValidator.isValidCustomer(customer) (lop bao ve cuoi cung).
     *  2. Seat seat = hall.getSeatByCode(seatCode); neu null thi bao ma ghe khong ton tai.
     *  3. Neu !seat.isAvailable() thi bao ghe da duoc dat (chong dat trung ghe / double booking).
     *  4. Booking booking = bookingManager.createBooking(customer, seat); neu null thi bao that bai.
     *  5. changed = true; tra ve booking.getReceiptDetails().
     */
    public String bookSeat(Customer customer, String seatCode) {
        // TODO: cai dat dat ghe
        throw new UnsupportedOperationException("TODO: CinemaController.bookSeat chua duoc cai dat.");
    }

    /**
     * Tim booking theo ma dat ve de View hien thi truoc khi huy hoac khi tra cuu.
     *
     * Tra ve: Booking, hoac null neu khong co.
     */
    public Booking findBooking(String bookingId) {
        // TODO: cai dat tim booking theo ma
        throw new UnsupportedOperationException("TODO: CinemaController.findBooking chua duoc cai dat.");
    }

    /**
     * Huy booking va tu dong chuyen ghe vua tra cho nguoi o dau hang cho (neu co).
     *
     * Tra ve: thong bao ket qua (khong tim thay / da huy / da huy va ghe duoc giao cho khach X).
     * Goi y cac buoc:
     *  1. Booking cancelled = bookingManager.cancelBooking(bookingId); null thi bao khong tim thay.
     *  2. changed = true.
     *  3. Neu !waitingList.isEmpty(): Customer next = waitingList.dequeue();
     *     bookingManager.createBooking(next, cancelled.getSeat());  (ghe vua tra duoc giao ngay)
     *  4. Tra ve thong bao, noi ro ghe nao da duoc giao cho ai (neu co).
     */
    public String cancelBooking(String bookingId) {
        // TODO: cai dat huy booking va chuyen ghe cho hang cho
        throw new UnsupportedOperationException("TODO: CinemaController.cancelBooking chua duoc cai dat.");
    }

    /**
     * Dua khach vao CUOI hang cho (chi cho phep khi rap da het ghe).
     *
     * Tra ve: thong bao ket qua kem vi tri cua khach trong hang cho.
     * Goi y cac buoc:
     *  1. Validate lai khach bang CinemaValidator.isValidCustomer(customer).
     *  2. Neu !hall.isFull() thi tu choi (con ghe thi phai dat ghe, khong vao hang cho).
     *  3. waitingList.enqueue(customer); false (trung ma khach) thi bao da co trong hang cho.
     *  4. changed = true; tra ve vi tri = waitingList.size().
     */
    public String joinWaitingList(Customer customer) {
        if (!CinemaValidator.isValidCustomer(customer)) {
            return "Failed: customer information is invalid.";
        }
        if (!hall.isFull()) {
            return "Failed: seats are still available. Please book a seat instead of joining the waiting list.";
        }
        if (!waitingList.enqueue(customer)) {
            return "Failed: customer " + customer.getCustomerId() + " is already in the waiting list.";
        }
        changed = true;
        return "Added " + customer.getName() + " to the waiting list. Position: " + waitingList.size() + ".";
    }

    /**
     * Lay danh sach khach dang cho theo dung thu tu FIFO (dau hang o vi tri 0).
     *
     * Goi y: return waitingList.getAll().
     */
    public List<Customer> getWaitingList() {
        return waitingList.getAll();
    }

    /**
     * Tim booking theo tu khoa trong ten khach hang.
     *
     * Tra ve: danh sach booking khop (rong neu khong co).
     * Goi y: reportService.searchByCustomerName(bookingManager.getAllBookings(), keyword).
     */
    public List<Booking> searchBookingsByName(String keyword) {
        // TODO: cai dat tim booking theo ten khach hang
        throw new UnsupportedOperationException("TODO: CinemaController.searchBookingsByName chua duoc cai dat.");
    }

    /**
     * Lay danh sach booking da sap xep theo thoi gian dat ve.
     *
     * Goi y: lay ban sao bang bookingManager.getAllBookings() roi reportService.sortByTime(...).
     */
    public List<Booking> getBookingsSortedByTime() {
        // TODO: cai dat lay danh sach booking sap xep theo thoi gian
        throw new UnsupportedOperationException("TODO: CinemaController.getBookingsSortedByTime chua duoc cai dat.");
    }

    /**
     * Lay danh sach booking da sap xep theo ten khach hang A-Z.
     *
     * Goi y: lay ban sao bang bookingManager.getAllBookings() roi reportService.sortByName(...).
     */
    public List<Booking> getBookingsSortedByName() {
        // TODO: cai dat lay danh sach booking sap xep theo ten
        throw new UnsupportedOperationException("TODO: CinemaController.getBookingsSortedByName chua duoc cai dat.");
    }

    /**
     * Lay so ghe da duoc dat.
     *
     * Goi y: return hall.countBookedSeats().
     */
    public int getBookedSeatCount() {
        // TODO: cai dat dem ghe da dat
        throw new UnsupportedOperationException("TODO: CinemaController.getBookedSeatCount chua duoc cai dat.");
    }

    /**
     * Lay so khach dang cho trong hang cho.
     */
    public int getWaitingCount() {
        return waitingList.size();
    }

    /**
     * Tinh tong doanh thu cua cac booking hien co.
     *
     * Goi y: reportService.calculateRevenue(bookingManager.getAllBookings()).
     */
    public double getTotalRevenue() {
        // TODO: cai dat tinh tong doanh thu
        throw new UnsupportedOperationException("TODO: CinemaController.getTotalRevenue chua duoc cai dat.");
    }

    /**
     * Tinh ty le lap day cua rap theo phan tram.
     *
     * Goi y: reportService.getOccupancyRate(hall.countBookedSeats(), hall.getTotalSeats()).
     */
    public double getOccupancyRate() {
        // TODO: cai dat tinh ty le lap day
        throw new UnsupportedOperationException("TODO: CinemaController.getOccupancyRate chua duoc cai dat.");
    }
}