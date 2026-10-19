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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Customer;
import utils.CinemaValidator;
import utils.Constants;

/**
 * Lop WaitingListFileHelper phu trach doc va ghi file waitinglist.txt.
 * Moi dong co dang: customerId, name, phone, email. Thu tu dong trong file chinh la thu tu FIFO
 * (dong dau tien la nguoi dung dau hang cho).
 */
public class WaitingListFileHelper implements IFileReadWrite<Customer> {

    private static final int FIELD_COUNT = 4;

    private final String fileName;
    private int skippedLines;
    private int totalLines;

    /**
     * Khoi tao file helper voi duong dan mac dinh.
     */
    public WaitingListFileHelper() {
        this(Constants.WAITING_FILE);
    }

    /**
     * Khoi tao file helper voi duong dan tuy chinh.
     */
    public WaitingListFileHelper(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Doc du lieu tu file theo dung thu tu dong, bo qua dong sai format hoac trung ma khach hang.
     */
    @Override
    public List<Customer> read() throws Exception {
        List<Customer> list = new ArrayList<>();
        Set<String> customerIds = new HashSet<>();
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
                Customer customer = convertToCustomer(line);
                if (customer == null || customerIds.contains(customer.getCustomerId())) {
                    skippedLines++;
                    continue;
                }
                customerIds.add(customer.getCustomerId());
                list.add(customer);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return list;
    }

    /**
     * Ghi danh sach khach dang cho xuong file (ghi de toan bo noi dung cu), tra ve true neu thanh cong.
     */
    @Override
    public boolean write(List<Customer> list) throws Exception {
        File file = new File(fileName);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            return false;
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
            for (Customer customer : list) {
                writer.write(convertToString(customer));
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
     * Doi mot khach hang thanh mot dong van ban de ghi file.
     */
    private String convertToString(Customer customer) {
        return customer.getCustomerId() + Constants.FILE_SEPARATOR
                + customer.getName() + Constants.FILE_SEPARATOR
                + customer.getPhone() + Constants.FILE_SEPARATOR
                + customer.getEmail();
    }

    /**
     * Doi mot dong van ban thanh Customer, tra ve null neu sai format hoac du lieu khong hop le.
     */
    private Customer convertToCustomer(String line) {
        try {
            String[] parts = line.split(Constants.FILE_SPLIT_REGEX, -1);
            if (parts.length != FIELD_COUNT) {
                return null;
            }
            Customer customer = new Customer(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim());
            return CinemaValidator.isValidCustomer(customer) ? customer : null;
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
