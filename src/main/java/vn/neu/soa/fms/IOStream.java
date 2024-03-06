package vn.neu.soa.fms;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IOStream {
    @Data
    @AllArgsConstructor
    public static class Case {
        private int category;
        private int amount;
    }
    public static void main(String[] args) {
        List<Case> cases = new ArrayList<>();
        cases.add(new Case(1, 10));
        cases.add(new Case(1, 10));
        cases.add(new Case(2, 10));
        cases.add(new Case(1, 10));
        cases.add(new Case(2, 10));
        cases.add(new Case(2, 10));
        cases.add(new Case(1, 10));
        cases.add(new Case(2, 10));
        cases.add(new Case(2, 10));

        System.out.println(cases.stream().collect(Collectors.groupingBy(Case::getCategory, Collectors.summingInt(Case::getAmount))));
    }
    public static void _main(String[] args) {
        try {
            FileInputStream  fin = new FileInputStream("inp.txt");
            FileOutputStream fout = new FileOutputStream("out.txt");

            int c;
            while ((c = fin.read()) != -1)
                fout.write(c);

            fin.close();
            fout.close();
        } catch (IOException e) {
            System.out.println("Có lỗi xảy ra trong quá trình đọc ghi file");
        }
    }
}
