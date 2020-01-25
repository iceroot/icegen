package com.icexxx.icegen.utils;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelUtils {

    public static void export(String[][][] data, String fileName) {
        WritableWorkbook book = null;
        try {
            book = Workbook.createWorkbook(new File(fileName));
            for (int i = 0; i < data.length; i++) {
                WritableSheet sheet = book.createSheet(data[i][0][0], 0);
                for (int m = 0; m < data[i].length; m++) {
                    for (int n = 0; n < data[i][m].length; n++) {
                        Label label = new Label(n, m, data[i][m][n]);
                        sheet.addCell(label);
                    }
                }
                sheet = null;
            }
            book.write();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (book != null) {
                try {
                    book.close();
                } catch (WriteException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
