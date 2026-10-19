package model;

import utils.Constants;
import utils.SeatStatus;

/**
 * Lop Seat dai dien cho mot ghe trong rap. Trang thai dat/trong nam trong thuoc tinh status.
 */
public class Seat {

    private final int row;
    private final int col;
    private final double price;
    private SeatStatus status;

    /**
     * Khoi tao ghe o vi tri (row, col), gia ve cho truoc, trang thai ban dau la AVAILABLE.
     */
    public Seat(int row, int col, double price) {
        this.row = row;
        this.col = col;
        this.price = price;
        this.status = SeatStatus.AVAILABLE;
    }

    /**
     * Lay chi so hang (bat dau tu 0, hang A la 0).
     */
    public int getRow() {
        return row;
    }

    /**
     * Lay chi so cot (bat dau tu 0).
     */
    public int getCol() {
        return col;
    }

    /**
     * Lay gia ve cua ghe.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Lay trang thai hien tai cua ghe.
     */
    public SeatStatus getStatus() {
        return status;
    }

    /**
     * Tinh ma ghe tu vi tri hang va cot, vi du (2, 4) tra ve C4.
     */
    public String getSeatCode() {
        return (char) (Constants.FIRST_ROW_LETTER + row) + String.valueOf(col);
    }

    /**
     * Dat ghe: chuyen AVAILABLE thanh BOOKED. Tra ve false neu ghe da duoc dat tu truoc.
     */
    public boolean reserve() {
        if (status == SeatStatus.BOOKED) {
            return false;
        }
        status = SeatStatus.BOOKED;
        return true;
    }

    /**
     * Tra ghe: chuyen BOOKED thanh AVAILABLE. Tra ve false neu ghe dang trong san.
     */
    public boolean release() {
        if (status == SeatStatus.AVAILABLE) {
            return false;
        }
        status = SeatStatus.AVAILABLE;
        return true;
    }

    /**
     * Kiem tra ghe con trong hay khong.
     */
    public boolean isAvailable() {
        return status == SeatStatus.AVAILABLE;
    }

    /**
     * Tra ve chuoi mo ta ghe, vi du C4 [X].
     */
    @Override
    public String toString() {
        return getSeatCode() + " " + status.toSymbol();
    }
}
