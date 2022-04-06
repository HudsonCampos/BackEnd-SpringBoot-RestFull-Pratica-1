package com.curso.poo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "file")
@Data
public class FileStorageConfig {

	private String uploadDir;
	
	
}
