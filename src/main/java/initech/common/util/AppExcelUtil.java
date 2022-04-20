package initech.common.util;

import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppExcelUtil {
	public static boolean isMerged(Sheet sheet, int rowIdx, int colIdx) { 
		for(int i = 0; i < sheet.getNumMergedRegions(); ++i) { 
			CellRangeAddress range = sheet.getMergedRegion(i); 
			String message = String.format("%d - %d - %d - %d"
					, range.getFirstRow()
					, range.getLastRow()
					, range.getFirstColumn()
					, range.getLastColumn()); 
			if( rowIdx >= range.getFirstRow() 
					&& rowIdx <= range.getLastRow() 
					&& colIdx >= range.getFirstColumn() 
					&& colIdx <= range.getLastColumn() 
					) { 
				return true; 
			} 
		} 
		return false; 
	}
	
	public static String getCellValue(XSSFCell cell) {
		String cellValue = "";
		// 데이터 포멧터
		DataFormatter formatter = new DataFormatter();
		// 데이트 포맷
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(cell == null) {
			return "";
		}
		
		switch (cell.getCellType()) {
		case FORMULA:
			cell.setCellType(CellType.STRING);
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case STRING:
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case NUMERIC:
			if (DateUtil.isInternalDateFormat(cell.getCellStyle().getDataFormat())) {
				cellValue = sdf.format(cell.getDateCellValue());
			}else {
				cell.setCellType(CellType.STRING);
				cellValue = String.valueOf(cell.getStringCellValue());
			}
			break;
		case BLANK:
			cell.setCellType(CellType.STRING);
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case ERROR:
			cell.setCellType(CellType.STRING);
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		default:
			cell.setCellType(CellType.STRING);
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		}
		
		if("-".equals(cellValue)) {
			cellValue = "";
		}
		return cellValue;
	}
	
	public static String getCellValueWithZero(XSSFCell cell) {
		String cellValue = "";
		// 데이터 포멧터
		DataFormatter formatter = new DataFormatter();
		// 데이트 포맷
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(cell == null) {
			return "";
		}
		
		switch (cell.getCellType()) {
		case FORMULA:
			cell.setCellType(CellType.STRING);
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case STRING:
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case NUMERIC:
			if (DateUtil.isInternalDateFormat(cell.getCellStyle().getDataFormat())) {
				cellValue = sdf.format(cell.getDateCellValue());
			}else {
				cell.setCellType(CellType.STRING);
				cellValue = String.valueOf(cell.getStringCellValue());
			}
			break;
		case BLANK:
			cell.setCellType(CellType.STRING);
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case ERROR:
			cell.setCellType(CellType.STRING);
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		default:
			cell.setCellType(CellType.STRING);
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		}
		
		if("-".equals(cellValue)) {
			cellValue = "0";
		}
		return cellValue;
	}
	
	public static String getCellValueEmptyWithZero(XSSFCell cell) {
		String cellValue = "";
		// 데이터 포멧터
		DataFormatter formatter = new DataFormatter();
		// 데이트 포맷
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(cell == null) {
			return "";
		}
		
		switch (cell.getCellType()) {
		case FORMULA:
			cell.setCellType(CellType.STRING);
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case STRING:
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case NUMERIC:
			if (DateUtil.isInternalDateFormat(cell.getCellStyle().getDataFormat())) {
				cellValue = sdf.format(cell.getDateCellValue());
			}else {
				cell.setCellType(CellType.STRING);
				cellValue = String.valueOf(cell.getStringCellValue());
			}
			break;
		case BLANK:
			cell.setCellType(CellType.STRING);
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case ERROR:
			cell.setCellType(CellType.STRING);
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		default:
			cell.setCellType(CellType.STRING);
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		}
		
		if("-".equals(cellValue) || "".equals(cellValue)) {
			cellValue = "0";
		}
		return cellValue;
	}
}
