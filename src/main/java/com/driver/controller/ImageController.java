package com.driver.controller;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/{blogId}/add-image")
    public ResponseEntity<String> addImage(@PathVariable int blogId, @RequestParam String description, @RequestParam String dimensions) {
        // Add image into the give blog
        imageService.addImage(blogId, description, dimensions);
        return new ResponseEntity<>("Added image successfully", HttpStatus.OK);
//        try {
//        } catch(Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
//        }
    }

    @GetMapping("/countImagesInScreen/{id}/{screenDimensions}")
    public ResponseEntity<Integer> countImagesInScreen(@PathVariable int id, @PathVariable String screenDimensions){
        int count = imageService.countImagesInScreen(id, screenDimensions);
        return new ResponseEntity<>(count, HttpStatus.OK);
//        try {
//        } catch(Exception e) {
//            System.out.println(e.getMessage());
//            return new ResponseEntity<>(e.getMessage().length(), HttpStatus.NOT_ACCEPTABLE);
//        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable int id) {
        // delete image using deleteById
        imageService.deleteImage(id);
        return new ResponseEntity<>(HttpStatus.OK);
//        try {
//        } catch(Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
    }
}



