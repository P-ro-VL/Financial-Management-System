package vn.neu.soa.fms.services;

import java.util.Date;
import java.util.Scanner;
import lombok.Getter;
public class Bill {
    private String ngayghiso;
    private String loaihoadon;
    private String mahoadon;
    private String ndhoadon;
    private double tienghino;
    private double tienghico;

    public Bill(){
        super();
    }
    public Bill(String ngayghiso,String loaihoadon, String mahoadon, String ndhoadon, double tienghino, double tienghico ){
        super();
        this.ngayghiso = ngayghiso;
        this.loaihoadon=loaihoadon;
        this.mahoadon=mahoadon;
        this.ndhoadon = ndhoadon;
        this.tienghino= tienghino;
        this.tienghico= tienghico;
    }
    public String getLoaihoadon(){
        return loaihoadon;
    }
    public void nhapBill(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập ngày ghi sổ:");
        ngayghiso = scanner.nextLine();
        System.out.print("Nhập loại hóa đơn: ");
        loaihoadon= scanner.nextLine();
        System.out.print("Nhập mã hóa đơn : ");
        mahoadon = scanner.nextLine();
        System.out.print("Nhập nội dung hóa đơn:  ");
        ndhoadon = scanner.nextLine();
        System.out.print("Nhập số tiền ghi nợ:");
        tienghino=scanner.nextDouble();
        System.out.print("Nhập số tiền ghi có:");
        tienghico= scanner.nextDouble();
    }
    public void hienthibill(){
        System.out.println("Ngày ghi sổ:" + ngayghiso);
        System.out.println("Loại hóa đơn : "+loaihoadon);
        System.out.println("Mã hóa đơn:"+mahoadon );
        System.out.println("Nội dung hóa đơn:"+ ndhoadon);
        System.out.println("Số tiền ghi nợ :" + tienghino +"VND");
        System.out.println("Số tiền ghi có: "+ tienghico + "VND");
    }

}
