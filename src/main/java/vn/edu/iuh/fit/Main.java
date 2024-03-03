package vn.edu.iuh.fit;

import jdepend.xmlui.JDepend;

import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws IOException {
            JDepend depend =new JDepend(new PrintWriter("reports/report.xml"));
            depend.addDirectory("T:\\LeHuuHiep\\Library-Assistant");
            depend.analyze();

            System.out.println("DONE");
    }
}
