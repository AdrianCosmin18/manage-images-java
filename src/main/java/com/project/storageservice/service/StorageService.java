package com.project.storageservice.service;

import com.project.storageservice.entity.ImageData;
import com.project.storageservice.repos.StorageRepo;
import com.project.storageservice.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepo storageRepo;


    public String uploadImage(MultipartFile file) throws IOException {

        ImageData imageData = storageRepo.save(ImageData.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .imageData(ImageUtils.compressImage(file.getBytes()))
                        .build());

        if(imageData != null){
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName){

        Optional<ImageData> dbImageData = storageRepo.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
