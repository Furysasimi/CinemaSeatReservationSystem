# Cinema Seat Reservation & Waiting List System (Topic 11 - CSD201)

Project console Java (NetBeans, Java with Ant), mo hinh MVC giong cau truc Lab 3.
Rap 6 hang (A-F) x 10 cot (0-9) = 60 ghe. Ma ghe vi du: A0, C4, F9.

## 1. Mo project bang NetBeans
1. File > Open Project... > chon thu muc `CinemaSeatReservation` (co bieu tuong tach ca phe).
2. Neu NetBeans hoi cap nhat build script thi bam dong y (khong anh huong code).
3. Run Project (F6). Main class: `Main`. Thu muc lam viec khi chay = thu muc goc project,
   nen duong dan file du lieu `src/file/bookings.txt` va `src/file/waitinglist.txt` hoat dong binh thuong.

## 2. Cau truc
```
src/
  Main.java
  model/    Customer, Seat, Booking
  manager/  CinemaHall (XONG), BookingManager, WaitingListManager,
            BookingReportService, CinemaController   (KHUNG RONG)
  file/     IFileReadWrite (interface), BookingFileHelper, WaitingListFileHelper,
            bookings.txt, waitinglist.txt
  utils/    Acceptable (regex), Constants, CinemaValidator, SeatStatus (enum)
  view/     Menu, View (nhap lieu + validate), CinemaView (cac man hinh)
```
Huong phu thuoc: view -> manager -> model/utils. `file` dung `CinemaHall` de doi ma ghe thanh doi tuong Seat.

## 3. Da code san (khong can sua)
Main, toan bo model, utils, file, view va `manager/CinemaHall`.
Trong `CinemaController` chi co san: constructor, `getHall()`, `isChanged()`.
Chuc nang chua cai dat se bao `TODO: ... chua duoc cai dat` tren man hinh (khong lam sap chuong trinh).

## 4. Can cai dat (40 phuong thuc) - moi ham deu co ghi chu goi y ngay tren dau ham
| Class | Cau truc / thuat toan | Phuong thuc |
|---|---|---|
| BookingManager | HashMap<String, Booking> | generateBookingId, createBooking, cancelBooking, findById, getAllBookings, restoreBooking, clear, size |
| WaitingListManager | Queue (LinkedList), FIFO | enqueue, dequeue, peek, isEmpty, size, contains, getAll, clear |
| BookingReportService | Merge Sort, Linear Search, (Binary Search) | searchByCustomerName, sortByTime, sortByName, mergeSort, merge, binarySearchByName (tuy chon), calculateRevenue, getOccupancyRate |
| CinemaController | dieu phoi, gom du lieu | loadFromFile, saveToFile, isHallFull, isSeatAvailable, bookSeat, findBooking, cancelBooking, joinWaitingList, getWaitingList, searchBookingsByName, getBookingsSortedByTime, getBookingsSortedByName, getBookedSeatCount, getWaitingCount, getTotalRevenue, getOccupancyRate |

Thu tu goi y: BookingManager, WaitingListManager, BookingReportService lam SONG SONG (khong phu thuoc nhau);
CinemaController lam sau cung vi goi ca ba lop tren.

## 5. Quy tac can nho
- Trang thai ghe chi nam o `Seat.status` trong `CinemaHall`. `Booking` giu tham chieu den DUNG doi tuong Seat do,
  nen `seat.reserve()` / `seat.release()` la cach duy nhat doi trang thai (khong luu trang thai ghe ra file).
- Chi luu Booking o MOT cho (`bookingMap`). Can danh sach thi tao ban sao `new ArrayList<>(bookingMap.values())`.
- Hang cho chi co nguoi khi rap het ghe. Huy booking => ghe tra ve duoc giao NGAY cho nguoi dau hang cho.
- Khi load file: `hall.reset()`, xoa du lieu cu, doc file, roi `restoreBooking` tung booking (ham nay moi reserve ghe).
- Quy tac nhap lieu (xem `Acceptable`): Customer ID `C001`, Booking ID `BK-1001`, ma ghe `A0..F9`,
  SDT 10 so bat dau bang 0, ten chi gom chu cai va dau cach.
- File du lieu: moi dong cach nhau boi `, `. Ten khach hang khong duoc chua dau phay (validator da chan).
  Doc/ghi UTF-8. Dong sai format, trung ma, trung ghe se bi bo qua va duoc dem trong `getSkippedLines()`.
- Code tuong thich Java 8 (khong dung `var`, `String.repeat`, `List.of`...).

## 6. Du lieu mau `src/file/bookings.txt`
4 dong dau hop le. 2 dong cuoi co chu y de test: `BK-1005` trung ghe A0 va `BK-1006` sai format => se bi bo qua
(sau khi cai dat xong, load file se bao 4 booking hop le, bo qua 2 dong). Doi sang ten/ma khac tuy y de test them.
`waitinglist.txt` de trong (chua co ai dang cho).

## 7. Kich ban test goi y sau khi cai dat
1. Load mau: 4 booking, A0 A1 C4 F9 hien [X].
2. Dat ghe trung (A0), ma ghe sai (G1, A10), thong tin khach sai (C1, ten co so, SDT 123) => bi chan va hoi lai.
3. Dat du 60 ghe => dat tiep se goi y vao hang cho; them 2 khach vao hang cho; them lai cung ma khach => bi tu choi.
4. Huy 1 booking => ghe duoc giao cho khach DAU hang cho; hang cho con 1 nguoi.
5. Tim theo ma/ten, bao cao sap xep theo thoi gian va theo ten, thong ke (ty le lap day, doanh thu).
6. Save roi thoat va chay lai: du lieu va hang cho giu nguyen thu tu.
