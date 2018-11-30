package com.aspiresys.codes;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelUtils {
	
	public static String getCellData(Sheet sheet,int rowno,int columnno) {
		
		if(sheet.getRow(rowno).getCell(columnno).getCellType()!=Cell.CELL_TYPE_BLANK)
		return sheet.getRow(rowno).getCell(columnno).getStringCellValue();
		else return "";
		
	}

}
