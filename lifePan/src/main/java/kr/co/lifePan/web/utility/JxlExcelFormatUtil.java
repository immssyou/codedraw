package kr.co.lifePan.web.utility;

import java.util.Date;

import jxl.CellView;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.ScriptStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Blank;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class JxlExcelFormatUtil {

	private static final WritableCellFormat emptyNumberCellFormat;

	static {
		emptyNumberCellFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Malgun Gothic"),
		/* 폰트 크기 */9,
		/* Bold 스타일 */WritableFont.NO_BOLD,
		/* 이탤릭체 여부 */false,
		/* 밑줄 스타일 */UnderlineStyle.NO_UNDERLINE,
		/* 폰트 색 */Colour.BLACK,
		/* 스크립트 스타일 */ScriptStyle.NORMAL_SCRIPT));
		try {
			emptyNumberCellFormat.setBackground(Colour.WHITE);
			emptyNumberCellFormat.setAlignment(Alignment.RIGHT);
			emptyNumberCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			emptyNumberCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
		} catch (WriteException ex) {
		}
	}

	public static WritableFont.FontName getFont() {
		WritableFont.FontName fontName = WritableFont.createFont("Malgun Gothic");
		return fontName;
	}
	
	public static WritableCellFormat getUnderlineTitleFormat() throws WriteException {
		WritableCellFormat titleFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Malgun Gothic"),
		/* 폰트 크기 */11,
		/* Bold 스타일 */WritableFont.BOLD,
		/* 이탤릭체 여부 */false,
		/* 밑줄 스타일 */UnderlineStyle.SINGLE,
		/* 폰트 색 */Colour.BLACK,
		/* 스크립트 스타일 */ScriptStyle.NORMAL_SCRIPT));
		titleFormat.setAlignment(Alignment.CENTRE);
		titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		titleFormat.setWrap(true);
		titleFormat.setBorder(Border.ALL, BorderLineStyle.NONE);

		return titleFormat;
	}
	
	public static WritableCellFormat getNormalTitleFormat() throws WriteException {
		WritableCellFormat titleFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Malgun Gothic"),
		/* 폰트 크기 */9,
		/* Bold 스타일 */WritableFont.BOLD,
		/* 이탤릭체 여부 */false,
		/* 밑줄 스타일 */UnderlineStyle.NO_UNDERLINE,
		/* 폰트 색 */Colour.BLACK,
		/* 스크립트 스타일 */ScriptStyle.NORMAL_SCRIPT));
		titleFormat.setAlignment(Alignment.LEFT);
		titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		titleFormat.setWrap(true);
		titleFormat.setBorder(Border.ALL, BorderLineStyle.NONE);

		return titleFormat;
	}

	public static WritableCellFormat getTitleFormat() throws WriteException {
		WritableCellFormat titleFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Malgun Gothic"),
		/* 폰트 크기 */9,
		/* Bold 스타일 */WritableFont.BOLD,
		/* 이탤릭체 여부 */false,
		/* 밑줄 스타일 */UnderlineStyle.NO_UNDERLINE,
		/* 폰트 색 */Colour.BLACK,
		/* 스크립트 스타일 */ScriptStyle.NORMAL_SCRIPT));
		titleFormat.setBackground(Colour.GREY_25_PERCENT);
		titleFormat.setAlignment(Alignment.CENTRE);
		titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		titleFormat.setWrap(true);
		titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

		return titleFormat;
	}

	public static WritableCellFormat getTextFormat() throws WriteException {
		WritableCellFormat textFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Malgun Gothic"),
		/* 폰트 크기 */9,
		/* Bold 스타일 */WritableFont.NO_BOLD,
		/* 이탤릭체 여부 */false,
		/* 밑줄 스타일 */UnderlineStyle.NO_UNDERLINE,
		/* 폰트 색 */Colour.BLACK,
		/* 스크립트 스타일 */ScriptStyle.NORMAL_SCRIPT));
		textFormat.setAlignment(Alignment.CENTRE);
		textFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		textFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

		return textFormat;
	}
	
	public static WritableCellFormat getCommentFormat() throws WriteException {
		WritableCellFormat textFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Malgun Gothic"),
		/* 폰트 크기 */9,
		/* Bold 스타일 */WritableFont.NO_BOLD,
		/* 이탤릭체 여부 */false,
		/* 밑줄 스타일 */UnderlineStyle.NO_UNDERLINE,
		/* 폰트 색 */Colour.RED,
		/* 스크립트 스타일 */ScriptStyle.NORMAL_SCRIPT));
		textFormat.setAlignment(Alignment.CENTRE);
		textFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		textFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

		return textFormat;
	}

	public static WritableCellFormat getTextEvenFormat() throws WriteException {
		WritableCellFormat textEvenFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Malgun Gothic"),
		/* 폰트 크기 */9,
		/* Bold 스타일 */WritableFont.NO_BOLD,
		/* 이탤릭체 여부 */false,
		/* 밑줄 스타일 */UnderlineStyle.NO_UNDERLINE,
		/* 폰트 색 */Colour.BLACK,
		/* 스크립트 스타일 */ScriptStyle.NORMAL_SCRIPT));
		textEvenFormat.setBackground(Colour.ICE_BLUE);
		textEvenFormat.setAlignment(Alignment.CENTRE);
		textEvenFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		textEvenFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

		return textEvenFormat;
	}

	public static WritableCellFormat getDoubleNumberFormat() throws WriteException {
		NumberFormat twodps = new NumberFormat("#,##0.00");
		// NumberFormat twodps = new NumberFormat("#,###.##");
		WritableCellFormat numberFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Malgun Gothic"),
		/* 폰트 크기 */9,
		/* Bold 스타일 */WritableFont.NO_BOLD,
		/* 이탤릭체 여부 */false,
		/* 밑줄 스타일 */UnderlineStyle.NO_UNDERLINE,
		/* 폰트 색 */Colour.BLACK,
		/* 스크립트 스타일 */ScriptStyle.NORMAL_SCRIPT), twodps);
		numberFormat.setAlignment(Alignment.RIGHT);
		numberFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		numberFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

		return numberFormat;
	}

	public static WritableCellFormat getDoubleNumberEvenFormat() throws WriteException {
		NumberFormat twodps = new NumberFormat("#,##0.00");
		// NumberFormat twodps = new NumberFormat("#,###.##");
		WritableCellFormat numberEvenFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Malgun Gothic"),
		/* 폰트 크기 */9,
		/* Bold 스타일 */WritableFont.NO_BOLD,
		/* 이탤릭체 여부 */false,
		/* 밑줄 스타일 */UnderlineStyle.NO_UNDERLINE,
		/* 폰트 색 */Colour.BLACK,
		/* 스크립트 스타일 */ScriptStyle.NORMAL_SCRIPT), twodps);
		numberEvenFormat.setBackground(Colour.ICE_BLUE);
		numberEvenFormat.setAlignment(Alignment.RIGHT);
		numberEvenFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		numberEvenFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

		return numberEvenFormat;
	}

	public static WritableCellFormat getLongNumberFormat() throws WriteException {
		NumberFormat nf = new NumberFormat("#,##0");
		// NumberFormat nf = new NumberFormat("#,###");
		WritableCellFormat numberFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Malgun Gothic"),
		/* 폰트 크기 */9,
		/* Bold 스타일 */WritableFont.NO_BOLD,
		/* 이탤릭체 여부 */false,
		/* 밑줄 스타일 */UnderlineStyle.NO_UNDERLINE,
		/* 폰트 색 */Colour.BLACK,
		/* 스크립트 스타일 */ScriptStyle.NORMAL_SCRIPT), nf);
		numberFormat.setAlignment(Alignment.RIGHT);
		numberFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		numberFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

		return numberFormat;
	}

	public static WritableCellFormat getLongNumberEvenFormat() throws WriteException {
		NumberFormat nf = new NumberFormat("#,##0");
		// NumberFormat nf = new NumberFormat("#,###");
		WritableCellFormat numberEvenFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Malgun Gothic"),
		/* 폰트 크기 */9,
		/* Bold 스타일 */WritableFont.NO_BOLD,
		/* 이탤릭체 여부 */false,
		/* 밑줄 스타일 */UnderlineStyle.NO_UNDERLINE,
		/* 폰트 색 */Colour.BLACK,
		/* 스크립트 스타일 */ScriptStyle.NORMAL_SCRIPT), nf);
		numberEvenFormat.setBackground(Colour.ICE_BLUE);
		numberEvenFormat.setAlignment(Alignment.RIGHT);
		numberEvenFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		numberEvenFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

		return numberEvenFormat;
	}

	public static WritableCellFormat getDateFormat() throws WriteException {
		DateFormat dateFormat = new DateFormat("yyyy-MM-dd");
		WritableCellFormat numberFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Malgun Gothic"),
		/* 폰트 크기 */9,
		/* Bold 스타일 */WritableFont.NO_BOLD,
		/* 이탤릭체 여부 */false,
		/* 밑줄 스타일 */UnderlineStyle.NO_UNDERLINE,
		/* 폰트 색 */Colour.BLACK,
		/* 스크립트 스타일 */ScriptStyle.NORMAL_SCRIPT), dateFormat);
		numberFormat.setAlignment(Alignment.CENTRE);
		numberFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		numberFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

		return numberFormat;
	}

	public static WritableCellFormat getDateEvenFormat() throws WriteException {
		DateFormat dateFormat = new DateFormat("yyyy-MM-dd");
		WritableCellFormat numberEvenFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Malgun Gothic"),
		/* 폰트 크기 */9,
		/* Bold 스타일 */WritableFont.NO_BOLD,
		/* 이탤릭체 여부 */false,
		/* 밑줄 스타일 */UnderlineStyle.NO_UNDERLINE,
		/* 폰트 색 */Colour.BLACK,
		/* 스크립트 스타일 */ScriptStyle.NORMAL_SCRIPT), dateFormat);
		numberEvenFormat.setBackground(Colour.ICE_BLUE);
		numberEvenFormat.setAlignment(Alignment.CENTRE);
		numberEvenFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		numberEvenFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

		return numberEvenFormat;
	}

	public static WritableCellFormat getDateTimeFormat() throws WriteException {
		DateFormat dateFormat = new DateFormat("yyyy-MM-dd HH:mm:ss");
		WritableCellFormat numberFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Malgun Gothic"),
		/* 폰트 크기 */9,
		/* Bold 스타일 */WritableFont.NO_BOLD,
		/* 이탤릭체 여부 */false,
		/* 밑줄 스타일 */UnderlineStyle.NO_UNDERLINE,
		/* 폰트 색 */Colour.BLACK,
		/* 스크립트 스타일 */ScriptStyle.NORMAL_SCRIPT), dateFormat);
		numberFormat.setAlignment(Alignment.CENTRE);
		numberFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		numberFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

		return numberFormat;
	}
	
	public static WritableCellFormat getTimeFormat() throws WriteException {
		DateFormat dateFormat = new DateFormat("HH:mm:ss");
		WritableCellFormat numberFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Malgun Gothic"),
		/* 폰트 크기 */9,
		/* Bold 스타일 */WritableFont.NO_BOLD,
		/* 이탤릭체 여부 */false,
		/* 밑줄 스타일 */UnderlineStyle.NO_UNDERLINE,
		/* 폰트 색 */Colour.BLACK,
		/* 스크립트 스타일 */ScriptStyle.NORMAL_SCRIPT), dateFormat);
		numberFormat.setAlignment(Alignment.CENTRE);
		numberFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		numberFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

		return numberFormat;
	}

	public static WritableCellFormat getDateTimeEvenFormat() throws WriteException {
		DateFormat dateFormat = new DateFormat("yyyy-MM-dd HH:mm:ss");
		WritableCellFormat numberEvenFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Malgun Gothic"),
		/* 폰트 크기 */9,
		/* Bold 스타일 */WritableFont.NO_BOLD,
		/* 이탤릭체 여부 */false,
		/* 밑줄 스타일 */UnderlineStyle.NO_UNDERLINE,
		/* 폰트 색 */Colour.BLACK,
		/* 스크립트 스타일 */ScriptStyle.NORMAL_SCRIPT), dateFormat);
		numberEvenFormat.setBackground(Colour.ICE_BLUE);
		numberEvenFormat.setAlignment(Alignment.CENTRE);
		numberEvenFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		numberEvenFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

		return numberEvenFormat;
	}

	public static WritableCellFormat getDateTimeMillisFormat() throws WriteException {
		DateFormat dateFormat = new DateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		WritableCellFormat numberFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Malgun Gothic"),
		/* 폰트 크기 */9,
		/* Bold 스타일 */WritableFont.NO_BOLD,
		/* 이탤릭체 여부 */false,
		/* 밑줄 스타일 */UnderlineStyle.NO_UNDERLINE,
		/* 폰트 색 */Colour.BLACK,
		/* 스크립트 스타일 */ScriptStyle.NORMAL_SCRIPT), dateFormat);
		numberFormat.setAlignment(Alignment.CENTRE);
		numberFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		numberFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

		return numberFormat;
	}

	public static WritableCellFormat getDateTimeMillisEvenFormat() throws WriteException {
		DateFormat dateFormat = new DateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		WritableCellFormat numberEvenFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Malgun Gothic"),
		/* 폰트 크기 */9,
		/* Bold 스타일 */WritableFont.NO_BOLD,
		/* 이탤릭체 여부 */false,
		/* 밑줄 스타일 */UnderlineStyle.NO_UNDERLINE,
		/* 폰트 색 */Colour.BLACK,
		/* 스크립트 스타일 */ScriptStyle.NORMAL_SCRIPT), dateFormat);
		numberEvenFormat.setBackground(Colour.ICE_BLUE);
		numberEvenFormat.setAlignment(Alignment.CENTRE);
		numberEvenFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		numberEvenFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

		return numberEvenFormat;
	}

	public static CellView getCellByWidth(int width) throws WriteException {
		CellView cellView = new CellView();
		cellView.setSize(256 * width);
		return cellView;
	}
	
	public static CellView getCellView10() throws WriteException {
		CellView cellView = new CellView();
		cellView.setSize(256 * 10);

		return cellView;
	}
	
	public static CellView getCellView15() throws WriteException {
		CellView cellView = new CellView();
		cellView.setSize(256 * 15);

		return cellView;
	}

	public static CellView getCellView20() throws WriteException {
		CellView cellView = new CellView();
		cellView.setSize(256 * 20);

		return cellView;
	}

	public static CellView getCellView30() throws WriteException {
		CellView cellView = new CellView();
		cellView.setSize(256 * 30);

		return cellView;
	}

	public static CellView getCellView40() throws WriteException {
		CellView cellView = new CellView();
		cellView.setSize(256 * 40);

		return cellView;
	}

	public static CellView getCellView50() throws WriteException {
		CellView cellView = new CellView();
		cellView.setSize(256 * 50);

		return cellView;
	}

	public static CellView getCellView60() throws WriteException {
		CellView cellView = new CellView();
		cellView.setSize(256 * 60);

		return cellView;
	}

	public static CellView getCellView70() throws WriteException {
		CellView cellView = new CellView();
		cellView.setSize(256 * 70);

		return cellView;
	}

	public static CellView getCellView80() throws WriteException {
		CellView cellViewWide = new CellView();
		cellViewWide.setSize(256 * 80);

		return cellViewWide;
	}

	public static void addCellView(WritableSheet sheet, CellView[] cellViews) {
		for (int i = 0; i < cellViews.length; i++) {
			sheet.setColumnView(i, cellViews[i]);
		}
	}

	public static void addTextCell(WritableSheet sheet, int cellIndex, int rowIndex, String value) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex);
			sheet.addCell(cell);
		} else {
			Label cell = new Label(cellIndex, rowIndex, value);
			sheet.addCell(cell);
		}
	}

	public static void addTextCell(WritableSheet sheet, int cellIndex, int rowIndex, String value, WritableCellFormat cellFormat) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, cellFormat);
			sheet.addCell(cell);
		} else {
			Label cell = new Label(cellIndex, rowIndex, value, cellFormat);
			sheet.addCell(cell);
		}
	}

	public static void addTextCell(WritableSheet sheet, int cellIndex, int rowIndex, String value, WritableCellFormat cellFormat, CellView cellView) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, cellFormat);
			sheet.addCell(cell);
		} else {
			Label cell = new Label(cellIndex, rowIndex, value, cellFormat);
			sheet.addCell(cell);
		}
		sheet.setColumnView(cellIndex, cellView);
	}
		
	public static void addTextCell(WritableSheet sheet, int cellIndex1, int cellIndex2, int rowIndex1, int rowIndex2, String value, WritableCellFormat cellFormat, CellView cellView) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex1, rowIndex1, cellFormat);
			sheet.addCell(cell);
		} else {
			Label cell = new Label(cellIndex1, rowIndex1, value, cellFormat);
			sheet.addCell(cell);
		}
		sheet.mergeCells(cellIndex1, rowIndex1, cellIndex2, rowIndex2);
		sheet.setColumnView(cellIndex1, cellView);
	}

	public static void addNumberCell(WritableSheet sheet, int cellIndex, int rowIndex, Short value) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex);
			sheet.addCell(cell);
		} else {
			jxl.write.Number cell = new jxl.write.Number(cellIndex, rowIndex, value);
			sheet.addCell(cell);
		}
	}

	public static void addNumberCell(WritableSheet sheet, int cellIndex, int rowIndex, Short value, WritableCellFormat cellFormat) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, emptyNumberCellFormat);
			sheet.addCell(cell);
		} else {
			jxl.write.Number cell = new jxl.write.Number(cellIndex, rowIndex, value, cellFormat);
			sheet.addCell(cell);
		}
	}

	public static void addNumberCell(WritableSheet sheet, int cellIndex, int rowIndex, Short value, WritableCellFormat cellFormat, CellView cellView) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, emptyNumberCellFormat);
			sheet.addCell(cell);
		} else {
			jxl.write.Number cell = new jxl.write.Number(cellIndex, rowIndex, value, cellFormat);
			sheet.addCell(cell);
		}
		sheet.setColumnView(cellIndex, cellView);
	}

	public static void addNumberCell(WritableSheet sheet, int cellIndex, int rowIndex, Integer value) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex);
			sheet.addCell(cell);
		} else {
			jxl.write.Number cell = new jxl.write.Number(cellIndex, rowIndex, value);
			sheet.addCell(cell);
		}
	}

	public static void addNumberCell(WritableSheet sheet, int cellIndex, int rowIndex, Integer value, WritableCellFormat cellFormat) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, emptyNumberCellFormat);
			sheet.addCell(cell);
		} else {
			jxl.write.Number cell = new jxl.write.Number(cellIndex, rowIndex, value, cellFormat);
			sheet.addCell(cell);
		}
	}

	public static void addNumberCell(WritableSheet sheet, int cellIndex, int rowIndex, Integer value, WritableCellFormat cellFormat, CellView cellView) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, emptyNumberCellFormat);
			sheet.addCell(cell);
		} else {
			jxl.write.Number cell = new jxl.write.Number(cellIndex, rowIndex, value, cellFormat);
			sheet.addCell(cell);
		}
		sheet.setColumnView(cellIndex, cellView);
	}

	public static void addNumberCell(WritableSheet sheet, int cellIndex, int rowIndex, Long value) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex);
			sheet.addCell(cell);
		} else {
			jxl.write.Number cell = new jxl.write.Number(cellIndex, rowIndex, value);
			sheet.addCell(cell);
		}
	}

	public static void addNumberCell(WritableSheet sheet, int cellIndex, int rowIndex, Long value, WritableCellFormat cellFormat) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, emptyNumberCellFormat);
			sheet.addCell(cell);
		} else {
			jxl.write.Number cell = new jxl.write.Number(cellIndex, rowIndex, value, cellFormat);
			sheet.addCell(cell);
		}
	}

	public static void addNumberCell(WritableSheet sheet, int cellIndex, int rowIndex, Long value, WritableCellFormat cellFormat, CellView cellView) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, emptyNumberCellFormat);
			sheet.addCell(cell);
		} else {
			jxl.write.Number cell = new jxl.write.Number(cellIndex, rowIndex, value, cellFormat);
			sheet.addCell(cell);
		}
		sheet.setColumnView(cellIndex, cellView);
	}

	public static void addNumberCell(WritableSheet sheet, int cellIndex, int rowIndex, Float value) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex);
			sheet.addCell(cell);
		} else {
			jxl.write.Number cell = new jxl.write.Number(cellIndex, rowIndex, value);
			sheet.addCell(cell);
		}
	}

	public static void addNumberCell(WritableSheet sheet, int cellIndex, int rowIndex, Float value, WritableCellFormat cellFormat) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, emptyNumberCellFormat);
			sheet.addCell(cell);
		} else {
			jxl.write.Number cell = new jxl.write.Number(cellIndex, rowIndex, value, cellFormat);
			sheet.addCell(cell);
		}
	}

	public static void addNumberCell(WritableSheet sheet, int cellIndex, int rowIndex, Float value, WritableCellFormat cellFormat, CellView cellView) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, emptyNumberCellFormat);
			sheet.addCell(cell);
		} else {
			jxl.write.Number cell = new jxl.write.Number(cellIndex, rowIndex, value, cellFormat);
			sheet.addCell(cell);
		}
		sheet.setColumnView(cellIndex, cellView);
	}

	public static void addNumberCell(WritableSheet sheet, int cellIndex, int rowIndex, Double value) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex);
			sheet.addCell(cell);
		} else {
			jxl.write.Number cell = new jxl.write.Number(cellIndex, rowIndex, value);
			sheet.addCell(cell);
		}
	}

	public static void addNumberCell(WritableSheet sheet, int cellIndex, int rowIndex, Double value, WritableCellFormat cellFormat) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, emptyNumberCellFormat);
			sheet.addCell(cell);
		} else {
			jxl.write.Number cell = new jxl.write.Number(cellIndex, rowIndex, value, cellFormat);
			sheet.addCell(cell);
		}
	}

	public static void addNumberCell(WritableSheet sheet, int cellIndex, int rowIndex, Double value, WritableCellFormat cellFormat, CellView cellView) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, emptyNumberCellFormat);
			sheet.addCell(cell);
		} else {
			jxl.write.Number cell = new jxl.write.Number(cellIndex, rowIndex, value, cellFormat);
			sheet.addCell(cell);
		}
		sheet.setColumnView(cellIndex, cellView);
	}

	public static void addNumberCell(WritableSheet sheet, int cellIndex, int rowIndex, Object value) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex);
			sheet.addCell(cell);
		} else {
			if (value instanceof Short) {
				addNumberCell(sheet, cellIndex, rowIndex, (Short) value);
			} else if (value instanceof Integer) {
				addNumberCell(sheet, cellIndex, rowIndex, (Integer) value);
			} else if (value instanceof Long) {
				addNumberCell(sheet, cellIndex, rowIndex, (Long) value);
			} else if (value instanceof Float) {
				addNumberCell(sheet, cellIndex, rowIndex, (Float) value);
			} else if (value instanceof Double) {
				addNumberCell(sheet, cellIndex, rowIndex, (Double) value);
			}
		}
	}

	public static void addNumberCell(WritableSheet sheet, int cellIndex, int rowIndex, Object value, WritableCellFormat cellFormat) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, emptyNumberCellFormat);
			sheet.addCell(cell);
		} else {
			if (value instanceof Short) {
				addNumberCell(sheet, cellIndex, rowIndex, (Short) value, cellFormat);
			} else if (value instanceof Integer) {
				addNumberCell(sheet, cellIndex, rowIndex, (Integer) value, cellFormat);
			} else if (value instanceof Long) {
				addNumberCell(sheet, cellIndex, rowIndex, (Long) value, cellFormat);
			} else if (value instanceof Float) {
				addNumberCell(sheet, cellIndex, rowIndex, (Float) value, cellFormat);
			} else if (value instanceof Double) {
				addNumberCell(sheet, cellIndex, rowIndex, (Double) value, cellFormat);
			}
		}
	}

	public static void addNumberCell(WritableSheet sheet, int cellIndex, int rowIndex, Object value, WritableCellFormat cellFormat, CellView cellView) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, emptyNumberCellFormat);
			sheet.addCell(cell);
		} else {
			if (value instanceof Short) {
				addNumberCell(sheet, cellIndex, rowIndex, (Short) value, cellFormat);
			} else if (value instanceof Integer) {
				addNumberCell(sheet, cellIndex, rowIndex, (Integer) value, cellFormat);
			} else if (value instanceof Long) {
				addNumberCell(sheet, cellIndex, rowIndex, (Long) value, cellFormat);
			} else if (value instanceof Float) {
				addNumberCell(sheet, cellIndex, rowIndex, (Float) value, cellFormat);
			} else if (value instanceof Double) {
				addNumberCell(sheet, cellIndex, rowIndex, (Double) value, cellFormat);
			}
		}
		sheet.setColumnView(cellIndex, cellView);
	}

	public static void addNumberCellDivide1000(WritableSheet sheet, int cellIndex, int rowIndex, Object value) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex);
			sheet.addCell(cell);
		} else {
			if (value instanceof Short) {
				addNumberCell(sheet, cellIndex, rowIndex, (Short) value / 1000);
			} else if (value instanceof Integer) {
				addNumberCell(sheet, cellIndex, rowIndex, (Integer) value / 1000);
			} else if (value instanceof Long) {
				addNumberCell(sheet, cellIndex, rowIndex, (Long) value / 1000);
			} else if (value instanceof Float) {
				addNumberCell(sheet, cellIndex, rowIndex, (Float) value / 1000);
			} else if (value instanceof Double) {
				addNumberCell(sheet, cellIndex, rowIndex, (Double) value / 1000);
			}
		}
	}

	public static void addNumberCellDivide1000(WritableSheet sheet, int cellIndex, int rowIndex, Object value, WritableCellFormat cellFormat) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, emptyNumberCellFormat);
			sheet.addCell(cell);
		} else {
			if (value instanceof Short) {
				addNumberCell(sheet, cellIndex, rowIndex, (Short) value / 1000, cellFormat);
			} else if (value instanceof Integer) {
				addNumberCell(sheet, cellIndex, rowIndex, (Integer) value / 1000, cellFormat);
			} else if (value instanceof Long) {
				addNumberCell(sheet, cellIndex, rowIndex, (Long) value / 1000, cellFormat);
			} else if (value instanceof Float) {
				addNumberCell(sheet, cellIndex, rowIndex, (Float) value / 1000, cellFormat);
			} else if (value instanceof Double) {
				addNumberCell(sheet, cellIndex, rowIndex, (Double) value / 1000, cellFormat);
			}
		}
	}

	public static void addNumberCellDivide1000(WritableSheet sheet, int cellIndex, int rowIndex, Object value, WritableCellFormat cellFormat, CellView cellView) throws RowsExceededException,
			WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, emptyNumberCellFormat);
			sheet.addCell(cell);
		} else {
			if (value instanceof Short) {
				addNumberCell(sheet, cellIndex, rowIndex, (Short) value / 1000, cellFormat);
			} else if (value instanceof Integer) {
				addNumberCell(sheet, cellIndex, rowIndex, (Integer) value / 1000, cellFormat);
			} else if (value instanceof Long) {
				addNumberCell(sheet, cellIndex, rowIndex, (Long) value / 1000, cellFormat);
			} else if (value instanceof Float) {
				addNumberCell(sheet, cellIndex, rowIndex, (Float) value / 1000, cellFormat);
			} else if (value instanceof Double) {
				addNumberCell(sheet, cellIndex, rowIndex, (Double) value / 1000, cellFormat);
			}
		}
		sheet.setColumnView(cellIndex, cellView);
	}

	public static void addDateCell(WritableSheet sheet, int cellIndex, int rowIndex, Date value) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex);
			sheet.addCell(cell);
		} else {
			DateTime cell = new DateTime(cellIndex, rowIndex, value);
			sheet.addCell(cell);
		}
	}

	public static void addDateCell(WritableSheet sheet, int cellIndex, int rowIndex, Date value, WritableCellFormat cellFormat) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, cellFormat);
			sheet.addCell(cell);
		} else {
			DateTime cell = new DateTime(cellIndex, rowIndex, value, cellFormat);
			sheet.addCell(cell);
		}
	}

	public static void addDateCell(WritableSheet sheet, int cellIndex, int rowIndex, Date value, WritableCellFormat cellFormat, CellView cellView) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, cellFormat);
			sheet.addCell(cell);
		} else {
			DateTime cell = new DateTime(cellIndex, rowIndex, value, cellFormat);
			sheet.addCell(cell);
		}
		sheet.setColumnView(cellIndex, cellView);
	}

	public static void addDateCell(WritableSheet sheet, int cellIndex, int rowIndex, java.sql.Time value) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex);
			sheet.addCell(cell);
		} else {
			DateTime cell = new DateTime(cellIndex, rowIndex, value);
			sheet.addCell(cell);
		}
	}

	public static void addDateCell(WritableSheet sheet, int cellIndex, int rowIndex, java.sql.Time value, WritableCellFormat cellFormat) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, cellFormat);
			sheet.addCell(cell);
		} else {
			DateTime cell = new DateTime(cellIndex, rowIndex, value, cellFormat);
			sheet.addCell(cell);
		}
	}

	public static void addDateCell(WritableSheet sheet, int cellIndex, int rowIndex, java.sql.Time value, WritableCellFormat cellFormat, CellView cellView) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, cellFormat);
			sheet.addCell(cell);
		} else {
			DateTime cell = new DateTime(cellIndex, rowIndex, value, cellFormat);
			sheet.addCell(cell);
		}
		sheet.setColumnView(cellIndex, cellView);
	}

	public static void addDateCell(WritableSheet sheet, int cellIndex, int rowIndex, java.sql.Timestamp value) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex);
			sheet.addCell(cell);
		} else {
			DateTime cell = new DateTime(cellIndex, rowIndex, value);
			sheet.addCell(cell);
		}
	}

	public static void addDateCell(WritableSheet sheet, int cellIndex, int rowIndex, java.sql.Timestamp value, WritableCellFormat cellFormat) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, cellFormat);
			sheet.addCell(cell);
		} else {
			DateTime cell = new DateTime(cellIndex, rowIndex, value, cellFormat);
			sheet.addCell(cell);
		}
	}

	public static void addDateCell(WritableSheet sheet, int cellIndex, int rowIndex, java.sql.Timestamp value, WritableCellFormat cellFormat, CellView cellView) throws RowsExceededException,
			WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, cellFormat);
			sheet.addCell(cell);
		} else {
			DateTime cell = new DateTime(cellIndex, rowIndex, value, cellFormat);
			sheet.addCell(cell);
		}
		sheet.setColumnView(cellIndex, cellView);
	}

	public static void addDateCell(WritableSheet sheet, int cellIndex, int rowIndex, Long value) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex);
			sheet.addCell(cell);
		} else {
			DateTime cell = new DateTime(cellIndex, rowIndex, new Date(value));
			sheet.addCell(cell);
		}
	}

	public static void addDateCell(WritableSheet sheet, int cellIndex, int rowIndex, Long value, WritableCellFormat cellFormat) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, cellFormat);
			sheet.addCell(cell);
		} else {
			DateTime cell = new DateTime(cellIndex, rowIndex, new Date(value), cellFormat);
			sheet.addCell(cell);
		}
	}

	public static void addDateCell(WritableSheet sheet, int cellIndex, int rowIndex, Long value, WritableCellFormat cellFormat, CellView cellView) throws RowsExceededException, WriteException {
		if (value == null) {
			Blank cell = new Blank(cellIndex, rowIndex, cellFormat);
			sheet.addCell(cell);
		} else {
			DateTime cell = new DateTime(cellIndex, rowIndex, new Date(value), cellFormat);
			sheet.addCell(cell);
		}
		sheet.setColumnView(cellIndex, cellView);
	}

	public static void addRow(WritableSheet sheet, int rowIndex, String[] cells, WritableCellFormat cellFormat) throws WriteException {
		for (int i = 0; i < cells.length; i++) {
			Label cell = new Label(i, rowIndex, cells[i], cellFormat);
			sheet.addCell(cell);
		}
	}
}
