package model;

/**
 * Lop Customer luu thong tin khach hang dat ve hoac dang cho trong hang cho.
 */
public class Customer {

    private String customerId;
    private String name;
    private String phone;
    private String email;

    /**
     * Khoi tao khach hang rong.
     */
    public Customer() {
    }

    /**
     * Khoi tao khach hang day du thong tin.
     */
    public Customer(String customerId, String name, String phone, String email) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Lay ma khach hang.
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Gan ma khach hang.
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * Lay ten khach hang.
     */
    public String getName() {
        return name;
    }

    /**
     * Gan ten khach hang.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Lay so dien thoai.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gan so dien thoai.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Lay email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gan email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Tra ve chuoi mo ta khach hang de debug hoac in nhanh.
     */
    @Override
    public String toString() {
        return customerId + " - " + name + " - " + phone + " - " + email;
    }
}
