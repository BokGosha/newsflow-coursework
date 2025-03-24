package edu.mirea.bokgosha.newsflow.cloud;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudStorageService {

    String uploadNewsImage(MultipartFile file) throws IOException;
}
