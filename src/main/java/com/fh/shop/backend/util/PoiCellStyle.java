package com.fh.shop.backend.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiCellStyle {
    /**
     * <pre>
     * titleCellStyle(这里用一句话描述这个方法的作用)   
     * 创建人：onlyShangfeng 1916015158@qq.com   
     * 创建时间：2018年8月28日 上午10:50:56    
     * 修改人：onlyShangfeng 1916015158@qq.com      
     * 修改时间：2018年8月28日 上午10:50:56    
     * 修改备注： 
     * &#64;param wb
     * &#64;param row
     * &#64;return
     * </pre>
     */
    public static XSSFCellStyle titleCellStyle(XSSFWorkbook wb) {

        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 水平居中
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直居中
        // 设置行高
        // row.setHeight((short) 30);

        // 设置字体
        XSSFFont font = wb.createFont();
        font.setFontHeight(24);
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);

        return style;
    }

    /**
     * <pre>
     * headCellStyle(头部样式)   
     * 创建人：onlyShangfeng 1916015158@qq.com   
     * 创建时间：2018年8月28日 上午10:57:44    
     * 修改人：onlyShangfeng 1916015158@qq.com      
     * 修改时间：2018年8月28日 上午10:57:44    
     * 修改备注： 
     * &#64;param wb
     * &#64;param row
     * &#64;return
     * </pre>
     */
    public static XSSFCellStyle headCellStyle(XSSFWorkbook wb) {
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 水平居中
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直居中
        // 设置行高
        // row.setHeight((short) 15);
        // 设置字体
        XSSFFont font = wb.createFont();
        font.setFontHeight(14);
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        style.setBorderBottom((short) 1);// 下边框
        style.setBorderTop((short) 1);// 上边框
        style.setBorderLeft((short) 1);// 左边框
        style.setBorderRight((short) 1); // 有边框
        // 设置单元格的背景色
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置前景填充样式
        style.setFillForegroundColor(HSSFColor.BRIGHT_GREEN.index);// 前景填充色

        return style;

    }

    public static XSSFCellStyle dataCellStyle(XSSFWorkbook wb) {
        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 水平居中
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直居中
        // 设置行高
        // row.setHeight((short) 12);
        // 设置字体
        XSSFFont font = wb.createFont();
        font.setFontHeight(12);
        style.setFont(font);

        style.setBorderBottom((short) 1);// 下边框
        style.setBorderTop((short) 1);// 上边框
        style.setBorderLeft((short) 1);// 左边框
        style.setBorderRight((short) 1); // 有边框
        return style;
    }
}
