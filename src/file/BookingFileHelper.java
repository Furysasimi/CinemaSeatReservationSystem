package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import manager.CinemaHall;
import model.Booking;
import model.Customer;
import model.Seat;
import utils.CinemaValidator;
import utils.Constants;

/**
 * Lop BookingFileHelper phu trach doc va ghi file bookings.txt.
 * Moi dong co dang: bookingId, customerId, name, phone, email, seatCode, bookingTime
 */
public class BookingFileHelper implements IFileReadWrite<Booking> {

    private static final int FIELD_COUNT = 7;

    private final String fileName;
    private final CinemaHall hall;
    private int skippedLines;
    private int totalLines;

    /**
     * Khoi tao file helper voi duong dan mac dinh. Can truyen CinemaHall de doi ma ghe thanh doi tuong Seat.
     */
    public BookingFileHelper(CinemaHall hall) {
        this(hall, Constants.BOOKING_FILE);
    }

    /**
     * Khoi tao file helper voi duong dan tuy chinh.
     */
    public BookingFileHelper(CinemaHall hall, String fileName) {
        this.hall = hall;
        this.fileName = fileName;
    }

    /**
     * Doc du lieu tu file, bo qua dong sai format, trung ma dat ve hoac trung ghe.
     * Ham nay CHI doc, khong doi trang thai ghe: controller phai hall.reset() truoc khi doc
     * va goi seat.reserve() (qua BookingManager.restoreBooking) cho tung booking sau khi doc.
     */
    @Override
    public List<Booking> read() throws Exception {
        List<Booking> list = new ArrayList<>();
        Set<String> bookingIds = new HashSet<>();
        Set<String> seatCodes = new HashSet<>();
        skippedLines = 0;
        totalLines = 0;

        File file = new File(fileName);
        if (!file.exists()) {
            return list;
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    line = removeBom(line);
                    firstLine = false;
                }
                if (line.trim().isEmpty()) {
                    continue;
                }
                totalLines++;
                Booking booking = convertToBooking(line);
                if (booking == null
                        || bookingIds.contains(booking.getBookingId())
                        || seatCodes.contains(booking.getSeat().getSeatCode())) {
                    skippedLines++;
                    continue;
                }
                bookingIds.add(booking.getBookingId());
                seatCodes.add(booking.getSeat().getSeatCode());
                list.add(booking);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return list;
    }

    /**
     * Ghi danh sach booking xuong file (ghi de toan bo noi dung cu), tra ve true neu thanh cong.
     */
    @Override
    public boolean write(List<Booking> list) throws Exception {
        File file = new File(fileName);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            return false;
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
            for (Booking booking : list) {
                writer.write(convertToString(booking));
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * Lay so dong du lieu bi bo qua trong lan doc gan nhat.
     */
    public int getSkippedLines() {
        return skippedLines;
    }

    /**
     * Lay tong so dong du lieu (khong tinh dong trong) trong lan doc gan nhat.
     */
    public int getTotalLines() {
        return totalLines;
    }

    /**
     * Doi mot booking thanh mot dong van ban de ghi file.
     */
    private String convertToString(Booking booking) {
        Customer customer = booking.getCustomer();
        return booking.getBookingId() + Constants.FILE_SEPARATOR
                + customer.getCustomerId() + Constants.FILE_SEPARATOR
                + customer.getName() + Constants.FILE_SEPARATOR
                + customer.getPhone() + Constants.FILE_SEPARATOR
                + customer.getEmail() + Constants.FILE_SEPARATOR
                + booking.getSeat().getSeatCode() + Constants.FILE_SEPARATOR
                + booking.getBookingTime().format(Constants.DATE_TIME_FORMATTER);
    }

    /**
     * Doi mot dong van ban thanh Booking, tra ve null neu sai format hoac du lieu khong hop le.
     */
    private Booking convertToBooking(String line) {
        try {
            String[] parts = line.split(Constants.FILE_SPLIT_REGEX, -1);
            if (parts.length != FIELD_COUNT) {
                return null;
            }
            String bookingId = CinemaValidator.normalizeBookingId(parts[0]);
            Customer customer = new Customer(parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim());
            Seat seat = hall.getSeatByCode(parts[5]);
            if (seat == null) {
                return null;
            }
            LocalDateTime bookingTime = LocalDateTime.parse(parts[6].trim(), Constants.DATE_TIME_FORMATTER);
            Booking booking = new Booking(bookingId, customer, seat, bookingTime);
            return CinemaValidator.isValidBooking(booking) ? booking : null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Bo ky tu BOM (neu co) o dau file, vi mot so trinh soan thao them BOM khi luu UTF-8.
     */
    private String removeBom(String line) {
        if (line.startsWith("\uFEFF")) {
            return line.substring(1);
        }
        return line;
    }
}
