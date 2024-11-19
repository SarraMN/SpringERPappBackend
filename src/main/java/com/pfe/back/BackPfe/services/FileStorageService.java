package com.pfe.back.BackPfe.services;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.pfe.back.BackPfe.entities.FileDB;
public interface FileStorageService {

	  public Stream<FileDB> getAllFiles() ;
	  public FileDB getFile(Long id) ;
	  public FileDB store(MultipartFile file) throws IOException;
		 

}
