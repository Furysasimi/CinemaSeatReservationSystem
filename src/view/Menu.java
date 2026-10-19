package view;

/**
 * Lop Menu hien thi cac lua chon cua chuong trinh.
 */
public class Menu {

    /**
     * Hien thi menu tu mang option truyen vao.
     */
    public static void showMenu(Object[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Your option from 1 - " + options.length + ": ");
    }
}
