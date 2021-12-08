package com.example.rainwire;

import java.io.File;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class RainwireApplicationController {

    @GetMapping("/")
	public String welcome() {
        return "uploader";
    }
    
    // ResponseEntity<?> used for status codes (like 200 OK!)
    // @RequestParam("file") use file as a parameter when looking at the html code
    // MultipartFile is used to break up and put together files when sending across network
    @PostMapping("/upload")
    public ResponseEntity<?> handleFileUpload( @RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try{
            file.transferTo( new File("//home//pi//Desktop//Rainwire//uploads//" + fileName));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File was uploaded successfuly!");
    }

}