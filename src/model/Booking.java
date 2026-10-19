package model;

import java.time.LocalDateTime;
import utils.Constants;

/**
 * Lop Booking luu thong tin mot lan dat ve: ma dat ve, khach hang, ghe va thoi gian dat.
 * Moi booking giu MOT ghe (tham chieu den dung doi tuong Seat trong so do ghe).
 */
public class Booking {

    private final String bookingId;
    private final Customer customer;
    private final Seat seat;
    private final LocalDateTime bookingTime;

    /**
     * Khoi tao booking day du thong tin.
     */
    public Booking(String bookingId, Customer customer, Seat seat, LocalDateTime bookingTime) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.seat = seat;
        this.bookingTime = bookingTime;
    }

    /**
     * Lay ma dat ve.
     */
    public String getBookingId() {
        return bookingId;
    }

    /**
     * Lay khach hang da dat ve.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Lay ghe da dat.
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * Lay thoi gian dat ve.
     */
    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    /**
     * Lay so tien cua booking, bang gia ve cua ghe (1 booking = 1 ghe).
     */
    public double getAmount() {
        return seat.getPrice();
    }

    /**
     * Tao chuoi hoa don chi tiet de in ra man hinh.
     */
    public String getReceiptDetails() {
        String nl = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        sb.append("========== BOOKING RECEIPT ==========").append(nl);
        sb.append("Booking ID : ").append(bookingId).append(nl);
        sb.append("Customer   : ").append(customer.getName())
                .append(" (").append(customer.getCustomerId()).append(")").append(nl);
        sb.append("Phone      : ").append(customer.getPhone()).append(nl);
        sb.append("Email      : ").append(customer.getEmail()).append(nl);
        sb.append("Seat       : ").append(seat.getSeatCode()).append(nl);
        sb.append("Price      : ").append(String.format("%,.0f", getAmount())).append(nl);
        sb.append("Booked at  : ").append(bookingTime.format(Constants.DATE_TIME_FORMATTER)).append(nl);
        sb.append("=====================================");
        return sb.toString();
    }

    /**
     * Tra ve chuoi mo ta ngan gon cua booking de debug.
     */
    @Override
    public String toString() {
        return bookingId + " - " + customer.getName() + " - " + seat.getSeatCode();
    }
}
