package com.quanlychomuon.springjwt.upload_image.controller;



import com.quanlychomuon.springjwt.upload_image.dto.FileResponse;
import com.quanlychomuon.springjwt.security.services.UserDetailsImpl;
import com.quanlychomuon.springjwt.upload_image.service.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class FileUploadController {
  @Autowired
  private FileStorageService fileStorageService;

  @PostMapping("/api/uploadFile/{id}")
  public FileResponse uploadFile(@PathVariable("id") int user, @RequestParam("file") MultipartFile file) {
    String fileName = fileStorageService.storeFile(file, user);

    LocalDate date = LocalDate.now();

    String url = ServletUriComponentsBuilder.fromCurrentContextPath()
      .path("uploads/avatar/")
      .path(fileName)
      .toUriString();

    return new FileResponse(fileName, url,
      file.getContentType(), file.getSize());
  }

  //@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
  @PostMapping("/api/upload/avatar")
  public List<FileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    return Arrays.asList(files)
      .stream()
      .map(file -> uploadFile(Integer.parseInt(String.valueOf(userDetails.getId())), file))
      .collect(Collectors.toList());
  }

  ///save anh app crop image
  public FileResponse saveImage(@RequestParam("file") MultipartFile file) {
    String fileName = fileStorageService.storeFileWebCapture(file);
    LocalDate date = LocalDate.now();
    String url = ServletUriComponentsBuilder.fromCurrentContextPath()
      .path("uploads/device/")
      .path(fileName)
      .toUriString();

    return new FileResponse(fileName, url,
      file.getContentType(), file.getSize());
  }


//  upload nhieu anh

  //    @CrossOrigin(origins = "true", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
  @CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
  @PostMapping("/api/uploads")
  public List<FileResponse> uploadMultipleFilesWeb(@RequestParam("files") MultipartFile[] files) {
    return Arrays.asList(files)
      .stream()
      .map(file -> saveImage(file))
      .collect(Collectors.toList());
  }

  @GetMapping("/uploads/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
    // Load file as Resource
    Resource resource = fileStorageService.loadFileAsResource(fileName);

    // Try to determine file's content type
    String contentType = null;
    try {
      contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    } catch (IOException ex) {
      //logger.info("Could not determine file type.");
    }

    // Fallback to the default content type if type could not be determined
    if(contentType == null) {
      contentType = "application/octet-stream";
    }

    return ResponseEntity.ok()
      .contentType(MediaType.parseMediaType(contentType))
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
      .body(resource);
  }
}
