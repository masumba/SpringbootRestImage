package com.demo.image.rest.restimage.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class HomeController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {

        try {
            File convertFile = new File("C:\\Demo Data Dump\\" + file.getOriginalFilename());
            convertFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
            return new ResponseEntity<>("File is Uploaded", HttpStatus.OK);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.CONFLICT);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.CONFLICT);

        }
    }
}
