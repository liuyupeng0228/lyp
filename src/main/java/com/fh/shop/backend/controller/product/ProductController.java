/**
 * <pre>项目名称:shop-admin-maven-v2
 * 文件名称:ProductController.java
 * 包名:com.fh.shop.backend.controller.product
 * 创建日期:2019年1月15日下午2:38:41
 * Copyright (c) 2019, LYLLYYLYP@163.com All Rights Reserved.</pre>
 */
package com.fh.shop.backend.controller.product;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.shop.backend.annotation.LogAnnotation;
import com.fh.shop.backend.biz.brand.IBrandService;
import com.fh.shop.backend.biz.images.IImagesService;
import com.fh.shop.backend.biz.user.IUserService;
import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.common.FileInfo;
import com.fh.shop.backend.common.ResponseEnum;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.controller.BaseController;
import com.fh.shop.backend.po.brand.Brand;
import com.fh.shop.backend.po.images.Images;
import com.fh.shop.backend.util.FileUtil;
import com.fh.shop.backend.util.SystemContext;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.shop.backend.biz.product.IProductService;
import com.fh.shop.backend.po.product.Product;
/**
 * <pre>项目名称：shop-admin-maven-v2    
 * 类名称：ProductController    
 * 类描述：    
 * 创建人：LiuYuPeng LYLLYYLYP@163.com    
 * 创建时间：2019年1月15日 下午2:38:41    
 * 修改人：LiuYuPeng LYLLYYLYP@163.com     
 * 修改时间：2019年1月15日 下午2:38:41    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
public class ProductController extends BaseController {

	@Resource(name="productService")
	private IProductService productService;

	@Autowired
	private IBrandService brandService;

	@Autowired
	private IImagesService imagesService;

	@Autowired
	private IUserService userService;

    /*跳转页面*/
    @RequestMapping("toProductList")
	public String toProductList(){
		return "product/productList";
	}

	//查询
	@RequestMapping("queryProduct")
	@ResponseBody
	public ServerResponse queryProduct(Product product, Integer draw, Integer start, Integer length, HttpServletRequest request){

		//排序的列号
		String order = request.getParameter(SystemContext.ORDER_INDEX);
		//排序的顺序asc or desc
		String orderDir = request.getParameter(SystemContext.ORDER_DIR);
		//排序的列。注意，我认为页面上的列的名字要和表中列的名字一致，否则，会导致SQL拼接错误
		String beanName = request.getParameter("columns["+order+"][data]");

		//用前台传递来的条件参数进行数据查询
		DataTableResult result = productService.buildDataTableResult(product, draw, start, length, orderDir, beanName);

		//响应给前台
		return ServerResponse.successData(ResponseEnum.SUCCESS,result);
	}


	/*导出Excel*/
	@RequestMapping("exportExcel")
	@LogAnnotation("Excel有样式导出")
	public void exportExcel(Product product, HttpServletResponse response){
		//查询符合要求的数据导出，不需要分页
		List<Product> productList = productService.findProductList(product);
		//将数据转换为Excel格式，创建一个工作簿，用以容纳sheet
		XSSFWorkbook workbook = new XSSFWorkbook();
		//创建sheet表
		XSSFSheet sheet = workbook.createSheet("产品表");
		//创建标题
		buildTitle(sheet,workbook);
		//创建表头
		buildHead(sheet,workbook);
		//创建表体
		buildBody(productList, sheet, workbook);
		//引用工具类,进行下载
		FileUtil.excelDownload(workbook,response);
	}

	private void buildTitle(XSSFSheet sheet,XSSFWorkbook workbook) {
		//字体样式
		XSSFFont font = workbook.createFont();
		//大小
		font.setFontHeightInPoints((short) 26);
		//加粗
		font.setBold(true);
		//颜色
		font.setColor(Font.COLOR_RED);
		//类型
		font.setFontName("宋体");

		//单元格样式
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		//字体
		cellStyle.setFont(font);
		//居中
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		//背景
		cellStyle.setFillForegroundColor(HSSFColor.BRIGHT_GREEN.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		XSSFRow row = sheet.createRow(3);
		XSSFCell cell = row.createCell(7);
		cell.setCellValue("商品信息");
		cell.setCellStyle(cellStyle);
		CellRangeAddress cellRangeAddress = new CellRangeAddress(3, 5, 7, 12);
		sheet.addMergedRegion(cellRangeAddress);
	}

	private void buildBody(List<Product> productList, XSSFSheet sheet,XSSFWorkbook workbook) {
		//对数据集合进行循环，将集合中的值放入到表的单元格中
		for (int i=0;i<productList.size();i++){
			//获取list集合中的数据
			Product productInfo = productList.get(i);
			//创建表的行，因为有表头所以第一行被占，需要将 i 变为 i+1
			XSSFRow row = sheet.createRow(i+7);

			//设置时间格式
			XSSFCellStyle timeCellStyle = workbook.createCellStyle();
			timeCellStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));

			//创建行中的单元格，并将数据放到单元格中
			XSSFCell productNameCell = row.createCell(7);
			productNameCell.setCellValue(productInfo.getProductName());
			XSSFCell priceCell = row.createCell(8);
			priceCell.setCellValue(productInfo.getProductPrice());
			XSSFCell brandCell = row.createCell(9);
			brandCell.setCellValue(productInfo.getBrand().getBrandName());
			XSSFCell createTimeCell = row.createCell(10);
			if (productInfo.getCreateTime()!=null){
				createTimeCell.setCellValue(productInfo.getCreateTime());
				createTimeCell.setCellStyle(timeCellStyle);
			}
			XSSFCell updateTimeCell = row.createCell(11);
			if (productInfo.getUpdateTime()!=null){
				updateTimeCell.setCellValue(productInfo.getUpdateTime());
				updateTimeCell.setCellStyle(timeCellStyle);
			}
			XSSFCell imgurlCell = row.createCell(12);
			imgurlCell.setCellValue(productInfo.getImgurl());
		}
	}

	private void buildHead(XSSFSheet sheet,XSSFWorkbook workbook) {
		//字体样式
		XSSFFont font = workbook.createFont();
		//字体大小
		font.setFontHeightInPoints((short) 16);
		//字体加粗
		font.setBold(true);
		//字体类型
		font.setFontName("宋体");

		//表头样式
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		//字体
		cellStyle.setFont(font);
		//居中
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);

		//创建sheet表中的表头
		XSSFRow title = sheet.createRow(6);
		String[] headers = {"产品名称","产品价格","产品品牌","录入时间","修改时间","图片路径"};
		//循环获取数组中的元素信息并赋给表头
		for (int i=0;i<headers.length;i++){
			String header = headers[i];
			XSSFCell titleCell = title.createCell(i+7);
			titleCell.setCellValue(header);
			titleCell.setCellStyle(cellStyle);

		}
	}


	/*分类导出Excel*/
	@RequestMapping("exportDifferentExcel")
	@LogAnnotation("Excel有样式分类导出")
	public void exportDifferentExcel(HttpServletResponse response){
		//先获取品牌的集合
		List<Brand> brandList = brandService.findBrand();

		try {
			//获取指定Excel文档的样式
			FileInputStream fis = new FileInputStream("D:\\style.xlsx");
			//因为是通过指定的Excel样式进行样式提取，并且通过指定Excel进行workbook的创建，所以会多出一个样式的sheet
			//将数据转换为Excel格式,创建一个工作簿
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			//获取第几张表
			XSSFSheet sheetAt2 = workbook.getSheetAt(0);
			//获取样式，只可通过单元格来获取，通过行无法获取
			//获取标题样式
			XSSFCellStyle headCellStyle = sheetAt2.getRow(0).getCell(0).getCellStyle();
			//获取表头样式
			XSSFCellStyle titleCellStyle = sheetAt2.getRow(3).getCell(0).getCellStyle();
            //获取警告样式
            XSSFCellStyle rowStyle = sheetAt2.getRow(4).getCell(0).getCellStyle();

			//设置时间格式
			XSSFCellStyle timeCellStyle = workbook.createCellStyle();
			timeCellStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));

			//时间警告样式
            XSSFCellStyle timeWaringStyle = workbook.createCellStyle();
            timeWaringStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
            timeWaringStyle.setFillForegroundColor(HSSFColor.RED.index);
            timeWaringStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);



            for (int i=0;i<brandList.size();i++){
				//通过集合中的数据获取品牌的id值
				Integer id = brandList.get(i).getId();
				//通过品牌的id值来查找商品个数，即可获取同种品牌的商品个数
				//不可使用分组查询，因为分组查询会得到一串数字，而在循环中我们只需要一种品牌的商品数
				Long totalCount = productService.queryTotal(id);
				//创建sheet表
				XSSFSheet sheet = workbook.createSheet(brandList.get(i).getBrandName()+"，产品数量："+totalCount);

				//创建标题
                XSSFRow headRow = sheet.createRow(0);
                //合并单元格 第一个0开始行  第二个0结束行   第三个0开始单元格  3结束单元格
                CellRangeAddress cellRangeAddress=new CellRangeAddress(0,2,0,5);
                //执行合并
                sheet.addMergedRegion(cellRangeAddress);
                //创建列
                XSSFCell headCell = headRow.createCell(0);
                //对列赋值
                headCell.setCellValue("关于"+brandList.get(i).getBrandName()+"品牌的商品表的信息");
                //赋予样式
                headCell.setCellStyle(headCellStyle);

                //创建sheet表中的表头
				XSSFRow title = sheet.createRow(3);
				//对表头进行赋值
				XSSFCell nameTitle = title.createCell(0);
				nameTitle.setCellValue("产品名称");
				nameTitle.setCellStyle(titleCellStyle);
				XSSFCell priceTitle = title.createCell(1);
				priceTitle.setCellValue("产品价格");
				priceTitle.setCellStyle(titleCellStyle);
				XSSFCell brandTitle = title.createCell(2);
				brandTitle.setCellValue("产品品牌");
				brandTitle.setCellStyle(titleCellStyle);
				XSSFCell createTimeTitle = title.createCell(3);
				createTimeTitle.setCellValue("录入时间");
				createTimeTitle.setCellStyle(titleCellStyle);
				XSSFCell updateTimeTitle = title.createCell(4);
				updateTimeTitle.setCellValue("修改时间");
				updateTimeTitle.setCellStyle(titleCellStyle);
				XSSFCell imgurlTitle = title.createCell(5);
				imgurlTitle.setCellValue("图片路径");
				imgurlTitle.setCellStyle(titleCellStyle);

				//通过品牌id查询出相同品牌的商品的集合，以便获取相同品牌的商品数据
				List<Product> productList = productService.findProductListByBrand(id);
				for (int j=0;j<productList.size();j++){
					//获取list集合中的数据
					Product productInfo = productList.get(j);

					//创建表的行，因为有表头所以第一行被占，需要将 j 变为 j+1
					XSSFRow row = sheet.createRow(j+4);
					//创建行中的单元格，并将数据放到单元格中
					XSSFCell productNameCell = row.createCell(0);
					productNameCell.setCellValue(productInfo.getProductName());
					XSSFCell priceCell = row.createCell(1);
					priceCell.setCellValue(productInfo.getProductPrice());
					XSSFCell brandCell = row.createCell(2);
					brandCell.setCellValue(productInfo.getBrand().getBrandName());
					XSSFCell createTimeCell = row.createCell(3);
					if (productInfo.getCreateTime()!=null){
						createTimeCell.setCellValue(productInfo.getCreateTime());
						createTimeCell.setCellStyle(timeCellStyle);
					}
					XSSFCell updateTimeCell = row.createCell(4);
					if (productInfo.getUpdateTime()!=null){
						updateTimeCell.setCellValue(productInfo.getUpdateTime());
						updateTimeCell.setCellStyle(timeCellStyle);
					}
					XSSFCell imgurlCell = row.createCell(5);
					imgurlCell.setCellValue(productInfo.getImgurl());

                    if (productInfo.getProductPrice()<5){
                        productNameCell.setCellStyle(rowStyle);
                        priceCell.setCellStyle(rowStyle);
                        brandCell.setCellStyle(rowStyle);
                        createTimeCell.setCellStyle(timeWaringStyle);
                        updateTimeCell.setCellStyle(timeWaringStyle);
                        imgurlCell.setCellStyle(rowStyle);
                    }
				}
			}
			//引用工具类,进行下载
			FileUtil.excelDownload(workbook,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


    @RequestMapping("exportExcelByBrand")
	@LogAnnotation("按品牌导出Excel")
	public void exportExcelByBrand(Product product,HttpServletResponse response){

		//将数据转换为Excel格式,创建一个工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();

		//将前台获取到的品牌id进行提取，作为全局变量，防止出现只可取得下拉列表框中第一个品牌id值的错误
		Integer brandId = product.getBrand().getId();

		//字体样式
		XSSFCellStyle cellStyle = buildWordStyle(workbook);

		//警告样式
		XSSFCellStyle rowStyle = buildWaringStyle(workbook);

        //设置时间格式
		XSSFCellStyle timeCellStyle = buildTimeStyle(workbook);

        //时间警告样式
		XSSFCellStyle timeWaringStyle = buildWaringTimeStyle(workbook);

		//获取品牌的集合
		List<Brand> brandList = brandService.findBrand();
		for (Brand brand : brandList) {
			/*//核心代码，查询时直接带入品牌的id
			product.getBrand().setId(brand.getId());*/

			//调用判断方法，判断是否进行了条件查询
			List<Product> productList = buildIfMethod(product, brandId, brand);

			//创建sheet表
			//可由条件查询出的商品的集合的大小来得知商品的个数
			XSSFSheet sheet = workbook.createSheet(brand.getBrandName()+"【"+productList.size()+"】");
			//创建sheet表中的表头
			XSSFRow title = sheet.createRow(0);
			//对表头进行赋值
			buildHeadByBrand(cellStyle, title);
			//导入数据
			buildBodyData(rowStyle, timeCellStyle, timeWaringStyle, productList, sheet);
		}
		//引用工具类,进行下载
		FileUtil.excelDownload(workbook,response);
	}

	private List<Product> buildIfMethod(Product product, Integer brandId, Brand brand) {
		//进行是否有条件查询的判断
		List<Product> productList = null;
		//判断前台获取的品牌id是否为-1
		if(brandId == -1){
			//对商品表中的品牌id进行赋值
			product.getBrand().setId(brand.getId());
			//查询符合要求的数据导出，不需要分页
			productList = productService.findProductList(product);
		}
		else {
			if (brand.getId() == product.getBrand().getId()){
				//查询符合要求的数据导出，不需要分页
				productList = productService.findProductList(product);
			}
			else {
				//对没有数据的集合进行空值的赋予，防止出现不同品牌的sheet是同一组数据的错误，以及空指针异常
				productList = new ArrayList<Product>();
			}
		}
		return productList;
	}

	private XSSFCellStyle buildWaringTimeStyle(XSSFWorkbook workbook) {
		XSSFCellStyle timeWaringStyle = workbook.createCellStyle();
		timeWaringStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
		timeWaringStyle.setFillForegroundColor(HSSFColor.RED.index);
		timeWaringStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return timeWaringStyle;
	}

	private XSSFCellStyle buildTimeStyle(XSSFWorkbook workbook) {
		XSSFCellStyle timeCellStyle = workbook.createCellStyle();
		timeCellStyle.setDataFormat(workbook.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
		return timeCellStyle;
	}

	private XSSFCellStyle buildWaringStyle(XSSFWorkbook workbook) {
		XSSFCellStyle rowStyle = workbook.createCellStyle();
		rowStyle.setFillForegroundColor(HSSFColor.RED.index);
		rowStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return rowStyle;
	}

	private XSSFCellStyle buildWordStyle(XSSFWorkbook workbook) {
		//字体样式
		XSSFFont font = workbook.createFont();
		//字体大小
		font.setFontHeightInPoints((short) 16);
		//字体加粗
		font.setBold(true);
		//字体类型
		font.setFontName("宋体");

		//表头样式
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		//字体
		cellStyle.setFont(font);
		//居中
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		return cellStyle;
	}

	private void buildBodyData(XSSFCellStyle rowStyle, XSSFCellStyle timeCellStyle, XSSFCellStyle timeWaringStyle, List<Product> productList, XSSFSheet sheet) {
		for (int j=0;j<productList.size();j++){
			//获取list集合中的数据
			Product productInfo = productList.get(j);
			//创建表的行，因为有表头所以第一行被占，需要将 j 变为 j+1
			XSSFRow row = sheet.createRow(j+1);
			//创建行中的单元格，并将数据放到单元格中
			buildBodyByBrand(rowStyle, timeCellStyle, timeWaringStyle, productInfo, row);
		}
	}

	private void buildHeadByBrand(XSSFCellStyle cellStyle, XSSFRow title) {
		XSSFCell nameTitle = title.createCell(0);
		nameTitle.setCellValue("产品名称");
		nameTitle.setCellStyle(cellStyle);
		XSSFCell priceTitle = title.createCell(1);
		priceTitle.setCellValue("产品价格");
		priceTitle.setCellStyle(cellStyle);
		XSSFCell brandTitle = title.createCell(2);
		brandTitle.setCellValue("产品品牌");
		brandTitle.setCellStyle(cellStyle);
		XSSFCell createTimeTitle = title.createCell(3);
		createTimeTitle.setCellValue("录入时间");
		createTimeTitle.setCellStyle(cellStyle);
		XSSFCell updateTimeTitle = title.createCell(4);
		updateTimeTitle.setCellValue("修改时间");
		updateTimeTitle.setCellStyle(cellStyle);
		XSSFCell imgurlTitle = title.createCell(5);
		imgurlTitle.setCellValue("图片路径");
		imgurlTitle.setCellStyle(cellStyle);
	}

	private void buildBodyByBrand(XSSFCellStyle rowStyle, XSSFCellStyle timeCellStyle, XSSFCellStyle timeWaringStyle, Product productInfo, XSSFRow row) {
		XSSFCell productNameCell = row.createCell(0);
		productNameCell.setCellValue(productInfo.getProductName());
		XSSFCell priceCell = row.createCell(1);
		priceCell.setCellValue(productInfo.getProductPrice());
		XSSFCell brandCell = row.createCell(2);
		brandCell.setCellValue(productInfo.getBrand().getBrandName());
		XSSFCell createTimeCell = row.createCell(3);
		if (productInfo.getCreateTime()!=null){
			createTimeCell.setCellValue(productInfo.getCreateTime());
			createTimeCell.setCellStyle(timeCellStyle);
		}
		XSSFCell updateTimeCell = row.createCell(4);
		if (productInfo.getUpdateTime()!=null){
			updateTimeCell.setCellValue(productInfo.getUpdateTime());
			updateTimeCell.setCellStyle(timeCellStyle);
		}
		XSSFCell imgurlCell = row.createCell(5);
		imgurlCell.setCellValue(productInfo.getImgurl());

		if (productInfo.getProductPrice()<5){
			productNameCell.setCellStyle(rowStyle);
			priceCell.setCellStyle(rowStyle);
			brandCell.setCellStyle(rowStyle);
			createTimeCell.setCellStyle(timeWaringStyle);
			updateTimeCell.setCellStyle(timeWaringStyle);
			imgurlCell.setCellStyle(rowStyle);
		}
	}


	@RequestMapping("toAdd")
	public String toAdd(ModelMap map){
		//给下拉框查询赋值
		//这是不用ajax进行下拉列表查询
		/*List<Brand> brandList = brandService.findBrand();
		map.put("brandList", brandList);*/
		return "product/addProduct";
	}


	@RequestMapping("addProduct")
	@LogAnnotation("商品添加")
	public String addProduct(Product product, HttpServletRequest request, @RequestParam("productImg") CommonsMultipartFile productImg, @RequestParam MultipartFile[] productImages){

		/*上传主图*/
		//将上传到远程服务器硬盘上的临时文件拷贝到指定的文件目录下
		try {
			//1.创建文件的输入流
			InputStream inputStream = productImg.getInputStream();
			//2.获取文件的原始名称
			String originalFilename = productImg.getOriginalFilename();
			//3.获取指定文件的路径
			//参数是相对于编译部署后的webapp文件来说的
			//因为编译后source类型文件不再存在，webapp也不存在，同时webapp下的文件会放在编译后的项目的根目录中，所以可以直接写文件名
			String realPath = request.getServletContext().getRealPath(SystemContext.IMAGES_PATH);
			//4.利用工具类进行文件的拷贝，就是通过原始路径获取文件通过输入流输入到指定的文件目录中
			//得到拷贝后文件的名字
			String uploadFileName = FileUtil.copyFile(inputStream, originalFilename, realPath);
			//将文件的路径赋给数据库中对应的字段，保存数据用以回显使用
			//文件路径+拷贝后文件名
			product.setImgurl(SystemContext.IMAGES_PATH + uploadFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*因只有在商品添加后才会有新添的商品的id，所以子图的添加要在商品添加完成后再执行*/
		productService.addProduct(product);

		/*上传子图*/
		Images images = new Images();
		if (productImages.length>1){
			//将上传到远程服务器硬盘上的临时文件拷贝到指定的文件目录下
			try {
				for (MultipartFile productImage : productImages) {

					//1.创建文件的输入流
					InputStream inputStream = productImage.getInputStream();
					//2.获取文件的原始名称
					String originalFilename = productImage.getOriginalFilename();
					//3.获取指定文件的路径
					//参数是相对于编译部署后的webapp文件来说的
					//因为编译后source类型文件不再存在，webapp也不存在，同时webapp下的文件会放在编译后的项目的根目录中，所以可以直接写文件名
					String realPath = request.getServletContext().getRealPath(SystemContext.IMAGES_PATH);
					//4.利用工具类进行文件的拷贝，就是通过原始路径获取文件通过输入流输入到指定的文件目录中
					//得到拷贝后文件的名字
					String uploadFileName = FileUtil.copyFile(inputStream, originalFilename, realPath);
					//将文件的路径赋给数据库中对应的字段，保存数据用以回显使用

					images.setImagePath(SystemContext.IMAGES_PATH+uploadFileName);
					images.setProductId(product.getId());
					imagesService.addImages(images);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return "redirect:toProductList.jhtml";
	}


	/**
	 * <pre>deleteProduct(单个删除)
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com
	 * 创建时间：2018年12月25日 下午9:27:44
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com
	 * 修改时间：2018年12月25日 下午9:27:44
	 * 修改备注：
	 * @param id
	 * @return</pre>
	 */
	@RequestMapping("deleteProduct")
	@ResponseBody
	@LogAnnotation("商品单个删除")
	public Map<String, Object> deleteProduct(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
			productService.deleteProduct(id);
			map.put("code", 200);
			map.put("mas", "删除成功");
		return map;
	}

	/**
	 * <pre>queryById(批量删除)
	 * 创建人：LiuYuPeng LYLLYYLYP@163.com
	 * 创建时间：2018年12月25日 下午9:28:07
	 * 修改人：LiuYuPeng LYLLYYLYP@163.com
	 * 修改时间：2018年12月25日 下午9:28:07
	 * 修改备注：
	 * @param
	 * @return</pre>
	 */
	@RequestMapping("deleteBatchProduct")
	@ResponseBody
	@LogAnnotation("商品批量删除")
	public Map<String, Object> deleteBatchProduct(String ids){
		Map<String, Object> map = new HashMap<String, Object>();
			productService.deleteBatchProduct(ids);
			map.put("code", 200);
			map.put("messageg", "删除成功");
		return map;
	}




	@RequestMapping("queryById")
	public ModelAndView queryById(Integer id){
		Product product = productService.queryById(id);
		List<Images> imagesList = imagesService.findProductImages(id);
		ModelAndView mav = new ModelAndView("product/updateProduct");

		//给下拉框查询赋值
		//这是不用ajax进行下拉列表查询
		/*List<Brand> brandList = brandService.findBrand();
		mav.addObject("brandList", brandList);*/

		mav.addObject("product", product);
		mav.addObject("imagesList",imagesList);
		return mav;
	}

	@RequestMapping("updateProduct")
	@LogAnnotation("商品修改")
	public String updateProduct(Product product,
								HttpServletRequest request,
								@RequestParam MultipartFile productImg,
								@RequestParam MultipartFile[] productChildImages) throws IOException {
		//获取项目在硬盘上的根目录
		String rootPath = getRootPath(request);
		//转换后的文件信息
		FileInfo fileInfo = convertFileInfo(productImg);

		List<FileInfo> fileInfos = new ArrayList<>();
		if (productChildImages[0].getSize()>0){
			fileInfos = convertFileInfoList(productChildImages);
		}

		//更新数据中的信息
		productService.updateProduct(product,fileInfo,fileInfos,rootPath);
		return "redirect:toProductList.jhtml";
	}

}

