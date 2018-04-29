package app.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import app.storage.StorageService;
import app.models.Image;
import java.util.Collection;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

// import org.springframework.http.ResponseEntity;

@RestController
public class ImagesController {
    private StorageService storageService;

    @Autowired
    ImagesController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping(value="/images", method=RequestMethod.GET)
    public Collection<Image> getImages() {
        Image im = new Image(1, "blabla", "name.png");
        ArrayList<Image> list = new ArrayList<Image>();
        list.add(im);
        return list;
    }

    @PostMapping("/files")
    public ResponseEntity<Resource> handleFileUpload(@RequestParam("file") MultipartFile file) {
        System.out.println("Just got a file");
        System.out.println(file.getOriginalFilename());

        storageService.store(file);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}