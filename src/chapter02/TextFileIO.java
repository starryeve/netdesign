package chapter02;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextFileIO {
    private PrintWriter pw;
    private Scanner sc;

    public void append(String msg) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(null);
        if(file == null) {
            return;
        }

        try {
            pw = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true), "UTF-8"));
            pw.println(msg);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pw.close();
        }
    }

    public String load() {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if(file == null) //用户放弃操作则返回
            return null;
        StringBuilder sb = new StringBuilder();
        try {
            //读和写的编码要注意保持一致
            sc = new Scanner(file,"utf-8");
            while (sc.hasNext()) {
                sb.append(sc.nextLine() + "\n"); //补上行读取的行末尾回车
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
        return sb.toString();
    }
}
