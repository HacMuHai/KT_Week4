package vn.edu.iuh.fit;

import jdepend.xmlui.JDepend;

import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CmdTest {
    public static void main(String[] args) throws Exception {
        //tạo report
        JDepend depend = new JDepend(new PrintWriter("reports/report.xml"));
        depend.addDirectory("Library-Assistant");
        depend.analyze();

        //lấy path
        String projectDir = System.getProperty("user.dir");
        Path jdependPath = Paths.get(projectDir, "jdepend-ui");

        Path reportsDir = Paths.get(projectDir, "reports");
        Path reportPath = Paths.get(reportsDir.toString(), "report.xml");

        //tạo file index.html
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "cd %s && npm run jdepend-ui %s be".formatted(jdependPath, reportPath));
        builder.redirectErrorStream(true);
        builder.start();

        Process process = builder.start();
        process.waitFor();

        //mở file index.html
        try
        {
            File file = new File("jdepend-ui/index.html");
            if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not
            {
                System.out.println("not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if(file.exists())         //checks file exists or not
                desktop.open(file);              //opens the specified file
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

}