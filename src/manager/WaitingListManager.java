package manager;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import model.Customer;

/**
 * Lop WaitingListManager quan ly hang cho bang Queue (cai dat boi LinkedList) theo nguyen tac FIFO:
 * vao truoc duoc phuc vu truoc.
 *
 * QUY TAC: hang cho chi co nguoi khi rap da het ghe. Moi lan co ghe duoc tra lai
 * thi nguoi o DAU hang cho duoc giao ghe do ngay (viec nay do CinemaController dieu phoi).
 *
 * TRANG THAI: KHUNG TRONG - cac phuong thuc ben duoi can duoc cai dat.
 */
public class WaitingListManager {

    private final Queue<Customer> waitingQueue;

    /**
     * Khoi tao hang cho rong.
     */
    public WaitingListManager() {
        waitingQueue = new LinkedList<>();
    }

    /**
     * Them khach vao CUOI hang cho.
     *
     * Tra ve: true neu them thanh cong, false neu customer null hoac customerId da co trong hang cho.
     * Goi y: kiem tra contains(customer.getCustomerId()) truoc, sau do waitingQueue.offer(customer).
     * Do phuc tap: O(N) vi phai kiem tra trung ma.
     */
    public boolean enqueue(Customer customer) {
        // TODO: cai dat them vao hang cho
        throw new UnsupportedOperationException("TODO: WaitingListManager.enqueue chua duoc cai dat.");
    }

    /**
     * Lay va xoa khach o DAU hang cho.
     *
     * Tra ve: Customer o dau hang, hoac null neu hang cho rong.
     * Goi y: waitingQueue.poll().
     * Do phuc tap: O(1).
     */
    public Customer dequeue() {
        // TODO: cai dat lay khach dau hang
        throw new UnsupportedOperationException("TODO: WaitingListManager.dequeue chua duoc cai dat.");
    }

    /**
     * Xem khach o dau hang cho nhung KHONG xoa.
     *
     * Tra ve: Customer o dau hang, hoac null neu hang cho rong.
     * Do phuc tap: O(1).
     */
    public Customer peek() {
        // TODO: cai dat xem khach dau hang
        throw new UnsupportedOperationException("TODO: WaitingListManager.peek chua duoc cai dat.");
    }

    /**
     * Kiem tra hang cho co rong hay khong.
     */
    public boolean isEmpty() {
        // TODO: cai dat kiem tra rong
        throw new UnsupportedOperationException("TODO: WaitingListManager.isEmpty chua duoc cai dat.");
    }

    /**
     * Lay so khach dang cho.
     */
    public int size() {
        // TODO: cai dat dem so khach cho
        throw new UnsupportedOperationException("TODO: WaitingListManager.size chua duoc cai dat.");
    }

    /**
     * Kiem tra mot ma khach hang da o trong hang cho hay chua.
     *
     * Tham so: customerId (da chuan hoa chu hoa).
     * Do phuc tap: O(N).
     */
    public boolean contains(String customerId) {
        // TODO: cai dat kiem tra khach da trong hang cho
        throw new UnsupportedOperationException("TODO: WaitingListManager.contains chua duoc cai dat.");
    }

    /**
     * Lay ban sao danh sach khach dang cho, giu dung thu tu tu dau hang den cuoi hang.
     * Dung de hien thi hang cho va ghi file (thu tu dong trong file = thu tu FIFO).
     *
     * Goi y: new ArrayList<>(waitingQueue) (LinkedList giu thu tu them vao).
     */
    public List<Customer> getAll() {
        // TODO: cai dat lay danh sach hang cho
        throw new UnsupportedOperationException("TODO: WaitingListManager.getAll chua duoc cai dat.");
    }

    /**
     * Xoa toan bo hang cho (dung truoc khi load file).
     */
    public void clear() {
        // TODO: cai dat xoa hang cho
        throw new UnsupportedOperationException("TODO: WaitingListManager.clear chua duoc cai dat.");
    }
}
