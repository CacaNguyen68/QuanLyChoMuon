package com.quanlychomuon.springjwt.upload_image.service;

import com.quanlychomuon.springjwt.exception.FileNotFoundException;
import com.quanlychomuon.springjwt.exception.FileStorageException;
import com.quanlychomuon.springjwt.upload_image.dto.FileStorageProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileStorageService {

  private final Path fileStorageLocation;


  @Autowired
  public FileStorageService(FileStorageProperties fileStorageProperties) {
    this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

    try {
      Files.createDirectories(this.fileStorageLocation);
    } catch (Exception ex) {
      throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
    }
  }

  public String storeFile(MultipartFile file, int idUser) {
    // Normalize file name

    String fileName = StringUtils.cleanPath(LocalDate.now() + "_IdUser_" + idUser + "." + file.getOriginalFilename());

    try {
      // Check if the file's name contains invalid characters
      if (fileName.contains("..")) {
        throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
      }

      // Copy file to the target location (Replacing existing file with the same name)
//      String storedPath = "nguoidung//daidien//" + fileName;
      String storedPath = "avatar//" + fileName;
      Path targetLocation = this.fileStorageLocation.resolve(storedPath);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

      return fileName;
    } catch (IOException ex) {
      throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
    }
  }

  public String storeFileWebCapture(MultipartFile file) {
    // Normalize file name
    String fileName = StringUtils.cleanPath(LocalDate.now() + "_" + file.getOriginalFilename());

    try {
      // Check if the file's name contains invalid characters
      if (fileName.contains("..")) {
        throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
      }

      // Copy file to the target location (Replacing existing file with the same name)
      //Path targetLocation = this.fileStorageLocation.resolve(fileName);
//      String storedPath = "nguoidung//daidien//" + fileName;
      String storedPath ="device//" + fileName;

      Path targetLocation = this.fileStorageLocation.resolve(storedPath);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

      return fileName;
    } catch (IOException ex) {
      throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
    }
  }

  public Resource loadFileAsResource(String fileName) {
    try {
      Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
      Resource resource = new UrlResource(filePath.toUri());
      if (resource.exists()) {
        return resource;
      } else {
        throw new FileNotFoundException("File not found " + fileName);
      }
    } catch (MalformedURLException ex) {
      throw new FileNotFoundException("File not found " + fileName, ex);
    }
  }
}
