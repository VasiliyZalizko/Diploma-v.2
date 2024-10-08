package ru.netology.cloudstorage.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import ru.netology.cloudstorage.model.entity.StorageFile;
import ru.netology.cloudstorage.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping("/file")
    @Secured("ROLE_USER1")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        fileService.uploadFile(multipartFile);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/file")
    @Secured("ROLE_USER1")
    public ResponseEntity<?> deleteFile(@RequestParam("filename") String filename) {
        fileService.deleteFile(filename);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/file")
    public ResponseEntity<?> downloadFile(@RequestParam("filename") String filename) {
        byte[] file = fileService.downloadFile(filename);
        return ResponseEntity.ok().body(new ByteArrayResource(file));
    }

    @PutMapping("/file")
    @Secured("ROLE_USER1")
    public ResponseEntity<?> updateFile(@RequestParam("filename") String filename, @RequestBody() StorageFile storageFile) {
        fileService.updateFile(filename, storageFile);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<StorageFile> getAllFiles(@RequestParam("limit") Integer limit) {
        return fileService.getAllFiles(limit);
    }
}