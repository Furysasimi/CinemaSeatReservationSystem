package manager;

import java.util.ArrayList;
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
 * TRANG THAI: DA CAI DAT.
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
        if (customer == null || customer.getCustomerId() == null) {
            return false;
        }
        if (contains(customer.getCustomerId())) {
            return false;
        }
        return waitingQueue.offer(customer);
    }

    /**
     * Lay va xoa khach o DAU hang cho.
     *
     * Tra ve: Customer o dau hang, hoac null neu hang cho rong.
     * Goi y: waitingQueue.poll().
     * Do phuc tap: O(1).
     */
    public Customer dequeue() {
        return waitingQueue.poll();
    }

    /**
     * Xem khach o dau hang cho nhung KHONG xoa.
     *
     * Tra ve: Customer o dau hang, hoac null neu hang cho rong.
     * Do phuc tap: O(1).
     */
    public Customer peek() {
        return waitingQueue.peek();
    }

    /**
     * Kiem tra hang cho co rong hay khong.
     */
    public boolean isEmpty() {
        return waitingQueue.isEmpty();
    }

    /**
     * Lay so khach dang cho.
     */
    public int size() {
        return waitingQueue.size();
    }

    /**
     * Kiem tra mot ma khach hang da o trong hang cho hay chua.
     *
     * Tham so: customerId (da chuan hoa chu hoa).
     * Do phuc tap: O(N).
     */
    public boolean contains(String customerId) {
        if (customerId == null) {
            return false;
        }
        for (Customer c : waitingQueue) {
            if (customerId.equalsIgnoreCase(c.getCustomerId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Lay ban sao danh sach khach dang cho, giu dung thu tu tu dau hang den cuoi hang.
     * Dung de hien thi hang cho va ghi file (thu tu dong trong file = thu tu FIFO).
     *
     * Goi y: new ArrayList<>(waitingQueue) (LinkedList giu thu tu them vao).
     */
    public List<Customer> getAll() {
        return new ArrayList<>(waitingQueue);
    }

    /**
     * Xoa toan bo hang cho (dung truoc khi load file).
     */
    public void clear() {
        waitingQueue.clear();
    }
}