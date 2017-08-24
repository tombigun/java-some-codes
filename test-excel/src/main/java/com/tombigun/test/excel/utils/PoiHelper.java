package com.tombigun.test.excel.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by tombigun on 2017/8/23.
 */
public class PoiHelper {

    public static Workbook newWorkbook(InputStream input, String name) throws IOException {
        return name.endsWith(".xlsx") ? new XSSFWorkbook(input) : new HSSFWorkbook(input);
    }

    public static void copyCell(Cell srcCell, Cell distCell) {
        CellType srcCellType = CellType.forInt(srcCell.getCellType());

        //distCell.getCellStyle().cloneStyleFrom(srcCell.getCellStyle());
        //copyCellStyle(srcCell.getCellStyle(), distCell.getCellStyle());

        if (srcCellType == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(srcCell)) {
                distCell.setCellValue(srcCell.getDateCellValue());
            } else {
                distCell.setCellValue(srcCell.getNumericCellValue());
            }
        } else if (srcCellType == CellType.STRING) {
            distCell.setCellValue(srcCell.getRichStringCellValue());
        } else if (srcCellType == CellType.BLANK) {
            // nothing21
        } else if (srcCellType == CellType.BOOLEAN) {
            distCell.setCellValue(srcCell.getBooleanCellValue());
        } else if (srcCellType == CellType.ERROR) {
            distCell.setCellErrorValue(srcCell.getErrorCellValue());
        } else if (srcCellType == CellType.FORMULA) {
            distCell.setCellFormula(srcCell.getCellFormula());
        } else { // nothing29
        }
    }

    public static void copyCellStyle(CellStyle fromStyle, CellStyle toStyle) {
        toStyle.setAlignment(fromStyle.getAlignmentEnum());
        toStyle.setBorderBottom(fromStyle.getBorderBottomEnum());
        toStyle.setBorderTop(fromStyle.getBorderTopEnum());
        toStyle.setBorderRight(fromStyle.getBorderRightEnum());
        toStyle.setBorderBottom(fromStyle.getBorderBottomEnum());
        toStyle.setBorderLeft(fromStyle.getBorderLeftEnum());
        toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
        toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
        toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
        toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());

        //背景和前景
        toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
        toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());

        toStyle.setDataFormat(fromStyle.getDataFormat());
        toStyle.setFillPattern(fromStyle.getFillPatternEnum());
        //toStyle.setFont();
        toStyle.setHidden(fromStyle.getHidden());
        toStyle.setIndention(fromStyle.getIndention());//首行缩进
        toStyle.setLocked(fromStyle.getLocked());
        toStyle.setRotation(fromStyle.getRotation());//旋转
        toStyle.setVerticalAlignment(fromStyle.getVerticalAlignmentEnum());
        toStyle.setWrapText(fromStyle.getWrapText());
        toStyle.setShrinkToFit(fromStyle.getShrinkToFit());
    }
}

