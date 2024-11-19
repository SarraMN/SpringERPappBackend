package com.pfe.back.BackPfe.ServiceImpl;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pfe.back.BackPfe.entities.FileDB;
import com.pfe.back.BackPfe.repository.FileDBRepository;
import  com.pfe.back.BackPfe.services.FileStorageService;
@Service
public class FileStorageServiceImpl implements FileStorageService{

	 @Autowired
	  private FileDBRepository fileDBRepository;
	 
	 @Override
	  public FileDB store(MultipartFile file) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
	    return fileDBRepository.save(FileDB);
	  }
	 @Override
	  public FileDB getFile(Long id) {
	    return fileDBRepository.findById(id).get();
	  }
	 @Override
	  public Stream<FileDB> getAllFiles() {
	    return fileDBRepository.findAll().stream();
	  }
	}