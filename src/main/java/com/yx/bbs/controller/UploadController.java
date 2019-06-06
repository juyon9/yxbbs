package com.yx.bbs.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yx.bbs.constant.RspType;
import com.yx.bbs.util.StringUtil;
import com.yx.bbs.vo.RspData;
import com.yx.bbs.vo.UploadVo;

@RestController
public class UploadController {

	@Value("${app.downloadurl}")
	private String httpUrl;

	@Value("${app.uploadpath}")
	private String uploadPath;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public RspData<UploadVo> handleFileUpload(@RequestParam("file") MultipartFile file) {
		RspData<UploadVo> rsp = new RspData<UploadVo>(RspType.操作失败);
		if (!file.isEmpty()) {
			try {
				String originalFilename = file.getOriginalFilename();
				int fileTypeIndex = originalFilename.lastIndexOf('.');
				String fileName = StringUtil.uuid();
				if (fileTypeIndex > -1) {
					fileName += originalFilename.substring(fileTypeIndex);
				}
				BufferedOutputStream out = new BufferedOutputStream(
						new FileOutputStream(new File(uploadPath + fileName)));
				out.write(file.getBytes());
				out.flush();
				out.close();
				rsp = new RspData<UploadVo>(RspType.成功);
				rsp.setData(new UploadVo(httpUrl + fileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				rsp = new RspData<UploadVo>(RspType.操作失败);
			} catch (IOException e) {
				e.printStackTrace();
				rsp = new RspData<UploadVo>(RspType.操作失败);
			}
		} else {
			rsp = new RspData<UploadVo>(RspType.操作失败);
		}
		return rsp;
	}
}
