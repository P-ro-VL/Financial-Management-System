package vn.neu.soa.fms.services;

import sun.security.pkcs11.wrapper.CK_TLS12_MASTER_KEY_DERIVE_PARAMS;

import java.util.ArrayList;
import java.util.Scanner;

public class Soketoan {
    public static void main(String[] args) {
        int n;
        Bill bill;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Bill> arrBill = new ArrayList<>();
        System.out.print("Nhập số hóa đơn :");
        n = scanner.nextInt();
        for (int i = 0; i<n;i++){
            bill= new Bill();
            System.out.println("Nhập thông tin hóa đơn " + (i+1) + ":" );
            bill.nhapBill();
            arrBill.add(bill);
        }
        System.out.println("Thông tin sổ kế toán của công ty:");
        for (int i=0; i< arrBill.size();i++){
            System.out.println("Thông tin của hóa đơn thứ: " + (i+1) + ":");
            arrBill.get(i).hienthibill();
        }
        System.out.println("Sổ kế toán sau khi đã phân loại :");
        ArrayList<Bill> arrBanHang = new ArrayList<>();
        ArrayList<Bill> arrMuaHang = new ArrayList<>();
        ArrayList<Bill> arrChungTu = new ArrayList<>();
        for (Bill hoaDon : arrBill) {
            switch (hoaDon.getLoaihoadon().toLowerCase()) {
                case "bán hàng":
                    arrBanHang.add(hoaDon);
                    break;
                case "mua hàng":
                    arrMuaHang.add(hoaDon);
                    break;
                case "chứng từ":
                    arrChungTu.add(hoaDon);
                    break;
            }
        }
        System.out.println("Hóa đơn bán hàng:");
        for (Bill hoaDon : arrBanHang) {
            hoaDon.hienthibill();
        }
        System.out.println("Hóa đơn mua hàng:");
        for (Bill hoaDon : arrMuaHang) {
            hoaDon.hienthibill();
        }
        System.out.println("Chứng từ:");
        for (Bill hoaDon : arrChungTu) {
            hoaDon.hienthibill();
        }

    }
}



