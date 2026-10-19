package file;

import java.util.List;

/**
 * Interface IFileReadWrite dinh nghia cac thao tac doc va ghi file cho mot kieu du lieu T.
 * Cac lop helper (BookingFileHelper, WaitingListFileHelper) se cai dat cu the tung thao tac.
 */
public interface IFileReadWrite<T> {

    /**
     * Doc toan bo du lieu hop le tu file va tra ve danh sach doi tuong.
     */
    List<T> read() throws Exception;

    /**
     * Ghi danh sach doi tuong xuong file, tra ve true neu ghi thanh cong.
     */
    boolean write(List<T> list) throws Exception;
}
