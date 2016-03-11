package com.qingtengzanya.wanghong.controller.basicinfo;

import com.ac.util.jsonresult.JsonResult;
import com.ac.util.jsonresult.JsonResultFactory;
import com.qingtengzanya.wanghong.dao.entity.WangHongInfoEty;
import com.qingtengzanya.wanghong.service.WhInfoService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 网红信息
 */
@Controller
@RequestMapping("/basicinfo/WanghonginfoCtrl/")
public class WangHonginfoCtrl {

	@Autowired
	private WhInfoService whInfoService;
	
	/**
	 * 查询
	 */
	@RequestMapping(value="search")
	public @ResponseBody JsonResult search(@RequestBody WangHongInfoEty wangHongInfoEty) throws Exception {
		String regx = "^(\\d{1,12})-(\\d{1,12})$";
		Pattern p = Pattern.compile(regx);
		String wxFriendNoStr = wangHongInfoEty.getWxFriendNoStr();
		String wbFriendNoStr = wangHongInfoEty.getWbFriendNoStr();
		Matcher m = null;
		if(wxFriendNoStr != null) {
			m = p.matcher(wxFriendNoStr);
			if(m.find()) {
				wangHongInfoEty.setWxFriendNo(Integer.parseInt(m.group(1)));
				wangHongInfoEty.setWxFriendNoSec(Integer.parseInt(m.group(2)));
			}
		}
		if(wbFriendNoStr != null) {
			m = p.matcher(wbFriendNoStr);
			if(m.find()) {
				wangHongInfoEty.setWbFriendNo(Integer.parseInt(m.group(1)));
				wangHongInfoEty.setWbFriendNoSec(Integer.parseInt(m.group(2)));
			}
		}
		int count = whInfoService.selectLimitCount(wangHongInfoEty);
		List<WangHongInfoEty> list = whInfoService.selectByLimit(wangHongInfoEty);
		return JsonResultFactory.extgrid(list, count);
	}

	/**
	 * 导入文件
	 * @param request
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value = "upload")
	public @ResponseBody JsonResult upload(HttpServletRequest request) throws Exception {
		if(request instanceof MultipartRequest) {
			MultipartRequest fileRequest = (MultipartRequest) request;
			MultipartFile file = fileRequest.getFile("file");
			if(file == null || file.isEmpty()) {
				return JsonResultFactory.error("文件不存在或者文件大小为零！");
			}
			if(!isExcelFileType(file)) {
				return JsonResultFactory.error("只支持文件格式Excel！");
			}
			List<WangHongInfoEty> whinfos = new ArrayList<>();
			Workbook wb = WorkbookFactory.create(file.getInputStream());
			FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
			Sheet sheet = wb.getSheetAt(0);
			int lastRowNum = sheet.getLastRowNum();
			for (int i = 1; i <= lastRowNum; i++) {
				Row row = sheet.getRow(i);
				WangHongInfoEty wangHongInfoEty = new WangHongInfoEty();
				int lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					Cell cell = row.getCell(j);
					switch (evaluator.evaluate(cell).getCellType()) {
						case Cell.CELL_TYPE_STRING:
							String value = evaluator.evaluate(cell).getStringValue();
							if(value == null || value.trim().equals("")) {
								continue;
							}else if((j == 3 || j == 4) && !value.matches("[0-9]{1,12}")) {
								return JsonResultFactory.error("表格第"+i+"行，第"+j+"列数值有问题，请修正后再导入！");
							}
							fillWhEty(j, value, wangHongInfoEty);
							break;
					}
				}
				whinfos.add(wangHongInfoEty);
			}
			if(whinfos.isEmpty()) {
				return JsonResultFactory.error("文件内容为空！");
			}
			try {
				whInfoService.insertEntityList(whinfos);
			}catch (Exception e) {
				if(e instanceof DuplicateKeyException) {
					return JsonResultFactory.error("用户姓名和类别重复！");
				}else {
					throw e;
				}
			}
			return JsonResultFactory.success();
		}else {
			return JsonResultFactory.error("文件上传出错，请重新上传!");
		}

	}

	private void fillWhEty(int index, String value, WangHongInfoEty wangHongInfoEty) {
		switch (index) {
			case 0:
				wangHongInfoEty.setType(value);
				break;
			case 1:
				wangHongInfoEty.setName(value);
				break;
			case 2:
				wangHongInfoEty.setWxNo(value);
				break;
			case 3:
				wangHongInfoEty.setWxFriendNo(Integer.parseInt(value));
				break;
			case 4:
				wangHongInfoEty.setWbFriendNo(Integer.parseInt(value));
				break;
			case 5:
				wangHongInfoEty.setWbLink(value);
				break;
			case 6:
				wangHongInfoEty.setArea(value);
				break;
			case 7:
				wangHongInfoEty.setSchool(value);
				break;
			case 8:
				wangHongInfoEty.setLevel(value);
				break;
			case 9:
				wangHongInfoEty.setRemark(value);
				break;
			default:
				break;
		}
		wangHongInfoEty.setCreateDate(new Date());
		wangHongInfoEty.setUpdateDate(new Date());
	}

	private boolean isExcelFileType(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String reg = ".+\\.(xls|xlsx)$";
		return fileName.matches(reg);
	}

	@RequestMapping(value = "export")
	public @ResponseBody JsonResult export(@RequestParam("ids") String ids, HttpServletResponse response) throws Exception {
		if(ids == null || ids.isEmpty()) {
			return JsonResultFactory.error("请选择要导出的数据！");
		}
		List<Long> idList = new ArrayList<>();
		for(String id : ids.split(",")) {
			idList.add(Long.parseLong(id));
		}
		List<WangHongInfoEty> wangHongInfoEties = whInfoService.selectByIds(idList);
		// 导出
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("网红信息");
		Row head = sheet.createRow(0);
		CellStyle headStyle = wb.createCellStyle();
		Font font = wb.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		headStyle.setFont(font);
		headStyle.setFillBackgroundColor(IndexedColors.BLUE.getIndex());
		createExcelHead(head, headStyle);
		createExcelBody(sheet, wangHongInfoEties);
		response.setContentType("application/vnd.ms-excel; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode("网红信息","utf-8") + ".xlsx");
		wb.write(response.getOutputStream());

		return JsonResultFactory.success();
	}

	private void createExcelBody(Sheet sheet, List<WangHongInfoEty> wangHongInfoEties) {
		for (int i = 0; i < wangHongInfoEties.size(); i++) {
			WangHongInfoEty wh = wangHongInfoEties.get(i);
			Row row = sheet.createRow(i+1);
			for (int j = 0; j < 10; j++) {
				Cell cell = row.createCell(j, Cell.CELL_TYPE_STRING);
				switch (j) {
					case 0:
						cell.setCellValue(wh.getType());
						break;
					case 1:
						cell.setCellValue(wh.getName());
						break;
					case 2:
						cell.setCellValue(wh.getWxNo());
						break;
					case 3:
						cell.setCellValue(wh.getWxFriendNo());
						break;
					case 4:
						cell.setCellValue(wh.getWbFriendNo());
						break;
					case 5:
						cell.setCellValue(wh.getWbLink());
						break;
					case 6:
						cell.setCellValue(wh.getArea());
						break;
					case 7:
						cell.setCellValue(wh.getSchool());
						break;
					case 8:
						cell.setCellValue(wh.getLevel());
						break;
					case 9:
						cell.setCellValue(wh.getRemark());
						break;
					default:
						break;
				}
			}
		}
	}

	private void createExcelHead(Row head, CellStyle cellStyle) {
		String[] headStr = new String[] {"类别", "姓名", "微信号", "微信好友数", "微博好友数", "微博链接", "地区", "学校", "评级", "备注"};
		for (int i = 0; i < headStr.length; i++) {
			Cell cell = head.createCell(i);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(headStr[i]);
			cell.setCellStyle(cellStyle);
		}
	}

	/**
	 * 保存
	 */
	@RequestMapping(value="save")
	public @ResponseBody JsonResult save(@RequestBody WangHongInfoEty wangHongInfoEty) throws Exception {
		if(wangHongInfoEty.getId() == null) {
			wangHongInfoEty.setCreateDate(new Date());
			wangHongInfoEty.setUpdateDate(new Date());
			try {
				whInfoService.insert(wangHongInfoEty);
			}catch (Exception e) {
				if(e instanceof DuplicateKeyException) {
					return JsonResultFactory.error("用户姓名和类别重复！");
				}else {
					throw e;
				}
			}
		}
		else {
			wangHongInfoEty.setUpdateDate(new Date());
			whInfoService.updateById(wangHongInfoEty);
		}
		return JsonResultFactory.success();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="delete")
	public @ResponseBody JsonResult delete(@RequestParam("id") Long id) {
		whInfoService.deleteById(id);
		return JsonResultFactory.success();
	}
	
	/**
	 * 得到详细信息
	 */
	@RequestMapping(value="getDetailInfo")
	public @ResponseBody JsonResult getDetailInfo(@RequestParam("id") Long id) throws Exception {
		WangHongInfoEty wangHongInfoEty = whInfoService.selectById(id);
		return JsonResultFactory.success(wangHongInfoEty);
	}
	
}