package com.curso.poo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.curso.poo.model.vo.UploadFileResponseVO;
import com.curso.poo.service.FileStorageService;

import io.swagger.annotations.Api;

@RestController
@Api(value = "Upload file Car")
@RequestMapping(path = "/api/carro/v1/file")
public class CarroFileController {

	private static Logger logger = LoggerFactory.getLogger(CarroController.class);
	
	@Autowired
	FileStorageService fileStorrageService;
	
	@PostMapping(value = "/uploadFile")
	public UploadFileResponseVO createFileStorage(@RequestParam("file") MultipartFile file) {
		String fileName = fileStorrageService.storageFileUpload(file);				
				
		String storageFileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/api/carro/v1/file/dowloadFile/")
				.path(fileName)
				.toUriString();
		return new UploadFileResponseVO(fileName, storageFileUri, file.getContentType(), file.getSize());				
	}
	
	@PostMapping(value = "/uploadFiles")
	public List<UploadFileResponseVO> createFileStorages(@RequestParam("files") MultipartFile[] files){		
		return Arrays.asList(files)
				.stream()
				.map(file -> createFileStorage(file))
				.collect(Collectors.toList());			
	}
	
	@GetMapping(value = "/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFileStorage(@PathVariable String fileName, HttpServletRequest request){
		Resource resource = fileStorrageService.downloadFile(fileName);
		
		String contentType = null;
		
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());			
		} catch (Exception e) {
			logger.info("Tipo do arquivo não encontrado!");
		}
		
		if(contentType == null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + resource.getFilename() + "\"")
				.body(resource);		
	}
	
}








