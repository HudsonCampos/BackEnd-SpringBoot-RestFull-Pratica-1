package com.curso.poo.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.curso.poo.config.FileStorageConfig;
import com.curso.poo.exception.FileStorageException;
import com.curso.poo.exception.MyFileNotFound;

@Service
public class FileStorageService {

	private Path fileStorage;

	@Autowired
	public FileStorageService(FileStorageConfig fileStorageConfig) {
		this.fileStorage = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();
	
		try {
			Files.createDirectories(this.fileStorage);
		} catch (Exception e) {
			throw new FileStorageException("Diretorio nao localizado.", e);
		}		
	}
	
	public String storageFileUpload(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if(fileName.contains("..")) {
				throw new FileStorageException("Formato invalido.");
			}
			
			Path fileTarget = this.fileStorage.resolve(fileName);
			Files.copy(file.getInputStream(), fileTarget, StandardCopyOption.REPLACE_EXISTING);
			
			return fileName;
			
		} catch (Exception e) {
			throw new FileStorageException("Arquivo " + fileName + "não localizado.", e);
		}
		
	}
		
	public Resource downloadFile(String fileNameDownload) {
			
		try {
			Path fileTargetDownload = this.fileStorage.resolve(fileNameDownload).normalize();
			Resource resource = new UrlResource(fileTargetDownload.toUri()); 				
			
			if(resource.exists()) {
				return resource;
			}else {
				throw new MyFileNotFound("O arquivo " + fileNameDownload + " não foi localizado!");
			}			
			
		} catch (Exception e) {
			throw new MyFileNotFound("O arquivo " + fileNameDownload + " não foi localizado!", e);
		}
	}
	
}
