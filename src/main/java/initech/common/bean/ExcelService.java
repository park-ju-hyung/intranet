package initech.common.bean;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelService {

	public ExcelService() {
		
	}
	
	public CellStyle cellStyleSetting(Workbook workbook, String kind) {
		//테이블 스타일
        CellStyle cellStyle = workbook.createCellStyle();
        
        //가는 경계선
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        if(kind.equals("header")) {
        	//배경색 회색
        	cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        	cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        }
        
        //데이터는 가운데 정렬
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); //중앙 정렬
        cellStyle.setWrapText(true);
        
        //폰트 설정
        Font fontOfGothic = workbook.createFont();
        fontOfGothic.setFontName("맑은 고딕");
        if(kind.equals("title")) {
        	fontOfGothic.setFontHeightInPoints((short) 12);
        	fontOfGothic.setBold(true);
        }
        else {
        	fontOfGothic.setFontHeightInPoints((short) 8);
        }
        cellStyle.setFont(fontOfGothic);

        return cellStyle;
	}

}
