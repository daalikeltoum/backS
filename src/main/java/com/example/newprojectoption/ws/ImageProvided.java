package com.example.newprojectoption.ws;


import com.example.newprojectoption.bean.ImageModl;
import com.example.newprojectoption.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("ispits-project/image")

public class ImageProvided {
    @Autowired
    private ImageService imageService;

    @PostMapping("/upload/nameData/{nameData}")
    public void uplaodImage( @RequestParam("imageFile") MultipartFile file,@PathVariable String nameData) throws IOException {
         imageService.uplaodImage(file,nameData);
    }

    @GetMapping(path = { "/get/{imageName}" })
    public ImageModl getImage(@PathVariable("imageName") String imageName) throws IOException {
        return imageService.getImage(imageName);
    }
}
