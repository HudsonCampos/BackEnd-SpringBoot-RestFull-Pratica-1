package com.curso.poo.model.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class UploadFileResponseVO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private String fileName;	
	private String fileDowloadUri;
	private String fileType;
	private long size;
		
	public UploadFileResponseVO(String fileName2, String fileDownloadUri, String contentType, long size2) {
		this.fileName = fileName2;
		this.fileDowloadUri = fileDownloadUri;
		this.fileType=contentType;
		this.size=size2;
	}
}
