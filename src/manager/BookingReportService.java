package manager;

import java.util.Comparator;
import java.util.List;
import model.Booking;

/**
 * Lop BookingReportService chua cac thuat toan SAP XEP, TIM KIEM va THONG KE tren danh sach booking.
 * Lop nay KHONG co thuoc tinh (stateless): moi ham nhan danh sach tu ben ngoai va chi doc/tinh toan,
 * khong thay doi bookingMap hay trang thai ghe.
 *
 * YEU CAU DE BAI: it nhat 1 thuat toan search hoac sort. Nhom nen TU CAI DAT Merge Sort
 * (khong goi Collections.sort) va Linear Search, co the them Binary Search de noi sort -> search.
 *
 * TRANG THAI: KHUNG TRONG - cac phuong thuc ben duoi can duoc cai dat.
 */
public class BookingReportService {

    /**
     * Tim cac booking co ten khach hang CHUA tu khoa (khong phan biet hoa thuong) bang Linear Search.
     *
     * Tham so: bookings (danh sach can tim), keyword (tu khoa, da trim).
     * Tra ve: danh sach moi chua cac booking khop (rong neu khong co).
     * Goi y: duyet tung phan tu, so sanh toLowerCase().contains(keyword.toLowerCase()).
     * Do phuc tap: O(N).
     */
    public List<Booking> searchByCustomerName(List<Booking> bookings, String keyword) {
        // TODO: cai dat Linear Search theo ten khach hang
        throw new UnsupportedOperationException("TODO: BookingReportService.searchByCustomerName chua duoc cai dat.");
    }

    /**
     * Sap xep danh sach booking theo thoi gian dat ve tang dan (cu nhat truoc) bang Merge Sort.
     *
     * Tham so: bookings (sap xep truc tiep tren danh sach nay).
     * Goi y: goi mergeSort(bookings, 0, bookings.size() - 1, comparator) voi comparator so sanh
     *        getBookingTime(); neu trung thoi gian (cung giay) thi so sanh tiep theo bookingId.
     * Do phuc tap: O(N log N).
     */
    public void sortByTime(List<Booking> bookings) {
        // TODO: cai dat sap xep theo thoi gian dat ve
        throw new UnsupportedOperationException("TODO: BookingReportService.sortByTime chua duoc cai dat.");
    }

    /**
     * Sap xep danh sach booking theo ten khach hang A-Z (khong phan biet hoa thuong) bang Merge Sort.
     *
     * Tham so: bookings (sap xep truc tiep tren danh sach nay).
     * Goi y: dung lai mergeSort voi comparator so sanh getCustomer().getName(); neu trung ten
     *        thi so sanh tiep theo bookingId de ket qua on dinh.
     * Do phuc tap: O(N log N).
     */
    public void sortByName(List<Booking> bookings) {
        // TODO: cai dat sap xep theo ten khach hang
        throw new UnsupportedOperationException("TODO: BookingReportService.sortByName chua duoc cai dat.");
    }

    /**
     * Ham de quy cua Merge Sort: chia doi doan [left, right], sap xep hai nua roi tron lai.
     *
     * Tham so: list (danh sach can sap xep), left/right (chi so dau/cuoi cua doan),
     *          comparator (quy tac so sanh hai Booking).
     * Goi y: neu left >= right thi dung; mid = (left + right) / 2; mergeSort nua trai,
     *        mergeSort nua phai, roi merge(list, left, mid, right, comparator).
     */
    private void mergeSort(List<Booking> list, int left, int right, Comparator<Booking> comparator) {
        // TODO: cai dat phan chia cua Merge Sort
        throw new UnsupportedOperationException("TODO: BookingReportService.mergeSort chua duoc cai dat.");
    }

    /**
     * Tron hai doan da sap xep [left, mid] va [mid + 1, right] thanh mot doan da sap xep.
     *
     * Tham so: list, left, mid, right (ranh gioi hai doan), comparator (quy tac so sanh).
     * Goi y: copy hai nua ra hai danh sach tam, dung hai con tro i, j so sanh bang comparator
     *        va ghi gia tri nho hon vao lai list (dung "<=" de giu tinh on dinh).
     * Do phuc tap: O(N), can them O(N) bo nho phu.
     */
    private void merge(List<Booking> list, int left, int mid, int right, Comparator<Booking> comparator) {
        // TODO: cai dat phan tron cua Merge Sort
        throw new UnsupportedOperationException("TODO: BookingReportService.merge chua duoc cai dat.");
    }

    /**
     * (Tuy chon) Tim booking theo ten khach hang CHINH XAC bang Binary Search tren danh sach DA sap xep theo ten.
     *
     * Tham so: sortedByName (BAT BUOC da sap xep bang sortByName), name (ten can tim).
     * Tra ve: mot booking co ten khop, hoac null neu khong co.
     * Do phuc tap: O(log N).
     */
    public Booking binarySearchByName(List<Booking> sortedByName, String name) {
        // TODO: cai dat Binary Search theo ten (tuy chon)
        throw new UnsupportedOperationException("TODO: BookingReportService.binarySearchByName chua duoc cai dat.");
    }

    /**
     * Tinh tong doanh thu bang cach cong gia ve (booking.getAmount()) cua tat ca booking.
     *
     * Do phuc tap: O(N).
     */
    public double calculateRevenue(List<Booking> bookings) {
        // TODO: cai dat tinh doanh thu
        throw new UnsupportedOperationException("TODO: BookingReportService.calculateRevenue chua duoc cai dat.");
    }

    /**
     * Tinh ty le lap day cua rap theo phan tram (0 - 100): bookedSeats / totalSeats * 100.
     *
     * Luu y: neu totalSeats <= 0 thi tra ve 0 de tranh chia cho 0.
     */
    public double getOccupancyRate(int bookedSeats, int totalSeats) {
        // TODO: cai dat tinh ty le lap day
        throw new UnsupportedOperationException("TODO: BookingReportService.getOccupancyRate chua duoc cai dat.");
    }
}
