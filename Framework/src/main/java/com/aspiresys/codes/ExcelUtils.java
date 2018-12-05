package com.aspiresys.codes;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public class ExcelUtils {
	
	public static int oprowno=1;
	
	public static String getCellData(Workbook workbook,int sheet,int rowno,int columnno) {
		try {
			if(workbook.getSheetAt(sheet).getRow(rowno).getCell(columnno).getCellType()!=Cell.CELL_TYPE_BLANK)
			return workbook.getSheetAt(sheet).getRow(rowno).getCell(columnno).getStringCellValue();
			else return "";
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static void writeOpHeaders() {
		try {
			CellStyle style = Runnable.opworkbook.createCellStyle();
			style.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
			style.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
			style.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
			style.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
			style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
         //   style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			Font font=Runnable.opworkbook.createFont();
			font.setBold(true);
			style.setFont(font);
			Runnable.opworkbook.getSheetAt(0).createRow(oprowno).createCell(0).setCellValue("Test Case");
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).createCell(1).setCellValue("Test Case Desciption");
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).createCell(2).setCellValue("Action");
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).createCell(3).setCellValue("Element");
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).createCell(4).setCellValue("Value");
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).createCell(5).setCellValue("Result");
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).getCell(0).setCellStyle(style);
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).getCell(1).setCellStyle(style);
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).getCell(2).setCellStyle(style);
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).getCell(3).setCellStyle(style);
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).getCell(4).setCellStyle(style);
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).getCell(5).setCellStyle(style);
			oprowno++;
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public static void writeOpBody(int rowno,Boolean bool) {
		try {
			CellStyle style = Runnable.opworkbook.createCellStyle();
			style.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
			style.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
			style.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
			style.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
			Runnable.opworkbook.getSheetAt(0).createRow(oprowno).createCell(0).setCellStyle(style);
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).createCell(1).setCellStyle(style);
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).createCell(2).setCellStyle(style);
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).createCell(3).setCellStyle(style);
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).createCell(4).setCellStyle(style);
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).createCell(5).setCellStyle(style);
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).getCell(0).setCellValue(getCellData(Runnable.workbook, 0, rowno, 0));
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).getCell(1).setCellValue(getCellData(Runnable.workbook, 0, rowno, 1));
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).getCell(2).setCellValue(getCellData(Runnable.workbook, 0, rowno, 3));
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).getCell(3).setCellValue(getCellData(Runnable.workbook, 0, rowno, 4));
			Runnable.opworkbook.getSheetAt(0).getRow(oprowno).getCell(4).setCellValue(getCellData(Runnable.workbook, 0, rowno, 5));
			CellStyle style2 = Runnable.opworkbook.createCellStyle();
			if(bool) {	
				style2.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
				style2.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
				style2.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
				style2.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
				style2.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
				style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
				Runnable.opworkbook.getSheetAt(0).getRow(oprowno).createCell(5).setCellStyle(style2);
				Runnable.opworkbook.getSheetAt(0).getRow(oprowno).getCell(5).setCellValue("Pass");
			}else {
				style2.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
				style2.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
				style2.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
				style2.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
				style2.setFillForegroundColor(IndexedColors.RED.getIndex());
				style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
				Runnable.opworkbook.getSheetAt(0).getRow(oprowno).createCell(5).setCellStyle(style2);
				Runnable.opworkbook.getSheetAt(0).getRow(oprowno).getCell(5).setCellValue("Fail");
			}
			
			oprowno++;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
