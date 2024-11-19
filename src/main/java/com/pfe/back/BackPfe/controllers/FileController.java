package com.pfe.back.BackPfe.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pfe.back.BackPfe.entities.FileDB;
import com.pfe.back.BackPfe.responses.ResponseFile;
import com.pfe.back.BackPfe.services.FileStorageService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/file")
public class FileController {
  
  @Autowired
  private FileStorageService storageService;
 
  
  @PostMapping("/upload")
  public Long uploadFile(@RequestParam("file") MultipartFile file) {
    try {
    	FileDB F = storageService.store(file);
      return F.getId();
    } catch (Exception e) {
      return (long) 0;
    }
  }
  @GetMapping("/files")
  public ResponseEntity<List<ResponseFile>> getListFiles() {
    List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
      String fileDownloadUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/files/")
          .path(dbFile.getId().toString())
          .toUriString();
      return new ResponseFile(
          dbFile.getName(),
          fileDownloadUri,
          dbFile.getType(),
          dbFile.getData().length);
    }).collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(files);
  }
  @GetMapping("/files/{id}")
  public ResponseEntity<byte[]> getfileData(@PathVariable Long id) {
    FileDB fileDB = storageService.getFile(id);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
        .body(fileDB.getData());
    
  }@GetMapping("/getfile/{id}")
  public FileDB getfile(@PathVariable Long id) {

	     FileDB F= storageService.getFile(id);
			System.out.println(F.getId());

	     return F;
	  }
}