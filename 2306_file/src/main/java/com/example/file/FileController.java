package com.example.file;

import com.example.file.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Slf4j
@RestController
public class FileController {
    @PostMapping(
            value = "/multipart",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto multipart(
            @RequestParam("name") String name,
            @RequestParam("photo")MultipartFile multipartFile
    ) throws IOException {
        //저장할 경로를 생성한다
        Files.createDirectories(Path.of("media"));
        //Files.createDrectories(Path.of("media"));
        //저장할 파일이름을 포함한 경로를 작성한다.
        //Screenshot From 2023-06-23 10:38:00.png
        LocalDateTime now = LocalDateTime.now();
        log.info(now.toString());
        Path uploadTo = Path.of(String.format("media/%s.png", now));
        //저장한다.
       multipartFile.transferTo(uploadTo);
/*
        File file = new File("./filename.png");
        try (OutputStream outputStream = new FileOutputStream(file)) {
            byte[] fileBytes = multipartFile.getBytes();
            //여기에서 byte[]를 활용
            outputStream.write(fileBytes);
        }

 */

        ResponseDto response = new ResponseDto();
        response.setMessage(
                String.format("/static/%s.png", now));
        return response;
    }
}
