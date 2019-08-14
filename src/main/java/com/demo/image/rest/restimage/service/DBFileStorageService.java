package com.demo.image.rest.restimage.service;

import com.demo.image.rest.restimage.exception.FileStorageException;
import com.demo.image.rest.restimage.exception.MyFileNotFoundException;
import com.demo.image.rest.restimage.model.DBFile;
import com.demo.image.rest.restimage.repository.DBFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;

    public DBFile storeFile(MultipartFile file){
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")){
                throw new FileStorageException("Sorry! Filename contains invalid path sequence "+fileName);
            }

            DBFile dbFile = new DBFile(fileName,file.getContentType(),file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException e){
            throw new FileStorageException("Could not store file "+fileName+". Please try again!");
        }
    }

    public DBFile getFile(String fileId){
        return dbFileRepository.findById(fileId).orElseThrow(()->new MyFileNotFoundException("File not found with id "+fileId));
    }
}
