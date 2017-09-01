package com.tombigun.test.excel;

import com.tombigun.test.excel.utils.PoiHelper;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by tombigun on 2017/8/23.
 */
public class TestExcel {

    public static void main(String[] args) {

        String inputFileName = "/Users/tombigun/Downloads/test.xls";
        String outputFileName = getOutputFileName(inputFileName);

        try {
            deal(inputFileName, outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getOutputFileName(String inputFileName){
        File file = new File(inputFileName);

        String parent = file.getParent();

        String name = file.getName();
        int i = name.lastIndexOf(".");
        String s1 = name.substring(0, i);
        String s2 = name.substring(i);

        String outFileName = null;
        for (int j = 0; j < 10; j++) {
            String tmp = parent + System.getProperty("file.separator") +s1 + "汇总-" + j + s2;

            if (!new File(tmp).exists()) {
                outFileName = tmp;
                break;
            }
        }
        if(outFileName == null){
            outFileName = parent + System.getProperty("file.separator") +s1 + "汇总-" + System.currentTimeMillis() + s2;
        }

        return outFileName;
    }

    public static void deal(String inputFileName, String outputFileName) throws IOException {
        Map<String, BigDecimal> map = readExcel(inputFileName);

        writeExcel(outputFileName, map);
    }

    private static void writeExcel(String outputFileName, Map<String, BigDecimal> map) {
        List<String> list = new ArrayList<String>();
        for (String s : map.keySet()) {
            list.add(s);
        }
        Collections.sort(list);

        for (String key : list) {
            map.get(key);
        }

        boolean xlsx = outputFileName.toLowerCase().endsWith("xlsx");
        Workbook wbTmp = xlsx ? new XSSFWorkbook() : new HSSFWorkbook();

        Sheet sheetTmp = wbTmp.createSheet();

        Row rowTmp = sheetTmp.createRow(0);
        rowTmp.createCell(0).setCellValue("发货单号");
        rowTmp.createCell(1).setCellValue("开单日期");
        rowTmp.createCell(2).setCellValue("汇总");

        for (int i = 0; i < list.size(); i++) {
            String key = list.get(i);
            String[] ss = key.split("@");

            rowTmp = sheetTmp.createRow(i+1);
            rowTmp.createCell(0).setCellValue(ss[0]);
            rowTmp.createCell(1).setCellValue(ss[1]);
            rowTmp.createCell(2).setCellValue(Double.valueOf(map.get(key).toString()));
        }

        rowTmp = sheetTmp.createRow(list.size()+2);
        rowTmp.createCell(0).setCellValue("合计" + list.size() + "份");


        OutputStream out = null;
        try{
            out = new FileOutputStream(outputFileName);
            wbTmp.write(out);
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException("保存汇总文件失败" + e.getMessage(), e);
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) { }
        }

    }

    private static Map<String, BigDecimal> readExcel(String inputFileName) {
        Workbook wb;
        FileInputStream is = null;

        try {
            is = new FileInputStream(inputFileName);
            wb = PoiHelper.newWorkbook(is, inputFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("文件不存在：" + inputFileName, e);
        } catch (RecordInputStream.LeftoverDataException e) {
            throw new RuntimeException("Excel版本较低，打开Excel随便点个，再保存", e);
        } catch (IOException e) {
            if("Bad file descriptor".equals(e.getMessage())){
                throw new RuntimeException("Excel版本较低，打开Excel随便点个，再保存", e);
            } else
                throw new RuntimeException("打开原明细文件失败", e);
        } finally {
            if(is != null)
                try {
                    is.close();
                } catch (IOException e) {}
        }

        Sheet sheet = wb.getSheetAt(0);

        int rowNum = sheet.getLastRowNum();

        if (rowNum <= 1)
            throw new RuntimeException("文件没有内容！");

        int 发货单号column = -1;
        int 开单日期column = -1;
        int 金额column = -1;

        Row row = sheet.getRow(0);
        if(row == null)
            throw new RuntimeException("第一行缺少标题");

        Iterator<Cell> iterator = row.iterator();
        while (iterator.hasNext()){
            Cell cell = iterator.next();
            String value = cell.getStringCellValue();

            if("发货单号".equals(value)){
                发货单号column = cell.getColumnIndex();
            } else if("开单日期".equals(value)){
                开单日期column = cell.getColumnIndex();
            } else if("金额".equals(value)){
                金额column = cell.getColumnIndex();
            }
        }

        if(发货单号column == -1){
            throw new RuntimeException("文件内容缺少列：发货单号");
        } else if(开单日期column == -1){
            throw new RuntimeException("文件内容缺少列：开单日期");
        } else if(金额column == -1){
            throw new RuntimeException("文件内容缺少列：金额");
        }

        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();

        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);

            if(row == null)
                continue;

            String 发货单号num = row.getCell(发货单号column).getStringCellValue();
            String 开单日期num = row.getCell(开单日期column).getStringCellValue();

            String key = 发货单号num + "@" + 开单日期num;

            String cellValue;
            Cell cell = row.getCell(金额column);
            CellType typeEnum = cell.getCellTypeEnum();
            switch (typeEnum) {
                case NUMERIC:
                    cellValue = Double.toString(cell.getNumericCellValue());
                    break;
                default:
                    cellValue = cell.getStringCellValue();
            }

            BigDecimal 金额 = new BigDecimal(cellValue);

            if (map.containsKey(key)) {
                BigDecimal 原有金额 = map.get(key);

                map.put(key, 原有金额.add(金额));
            } else {
                map.put(key, 金额);
            }
        }

        return map;
    }

}
