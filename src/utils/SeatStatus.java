package utils;

/**
 * Enum SeatStatus the hien trang thai cua mot ghe: con trong hoac da duoc dat.
 */
public enum SeatStatus {
    AVAILABLE("[O]", "Available"),
    BOOKED("[X]", "Booked");

    private final String symbol;
    private final String displayName;

    /**
     * Khoi tao trang thai ghe voi ky hieu ve so do va ten hien thi.
     */
    SeatStatus(String symbol, String displayName) {
        this.symbol = symbol;
        this.displayName = displayName;
    }

    /**
     * Tra ve ky hieu dung de ve so do ghe: [O] la ghe trong, [X] la ghe da dat.
     */
    public String toSymbol() {
        return symbol;
    }

    /**
     * Tra ve ten trang thai de hien thi cho nguoi dung.
     */
    public String toDisplayName() {
        return displayName;
    }
}
