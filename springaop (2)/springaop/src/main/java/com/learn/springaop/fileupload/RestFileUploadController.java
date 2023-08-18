package com.learn.springaop.fileupload;

import com.learn.springaop.fileupload.exception.FileIsNotAvailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;

@RestController
@Slf4j
public class RestFileUploadController {


    @PostMapping("/uploadfile")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        Path imageFolder = Paths.get("ImageFolder");

        log.info("true");
        createFolder(imageFolder);


        FileResponse fileResponse = writeFile(multipartFile, imageFolder);
        // return ResponseEntity.ok(statusOfFileUpload);
        return ResponseEntity.ok(fileResponse);

    }

    private static FileResponse writeFile(MultipartFile multipartFile, Path imageFolder) throws IOException {
        if (!multipartFile.isEmpty()) {
            System.out.println(multipartFile.getOriginalFilename());
            System.out.println(multipartFile.getSize());
            System.out.println(multipartFile.isEmpty());
            Path path = Paths.get(imageFolder.toString(), multipartFile.getOriginalFilename());
            Path write = Files.write(path, multipartFile.getBytes());

            return new FileResponse("file is uploaded","downloadFile/"+multipartFile.getOriginalFilename());
        }

        throw new FileIsNotAvailableException("Please Upload the file.File is not available ");
    }

    private static void createFolder(Path imageFolder) throws IOException {
        if (!Files.exists(imageFolder)) {
            Files.createDirectory(imageFolder);
        }
    }

    private static ResponseEntity<String> fileUpload(MultipartFile multipartFile, Path imageFolder) throws IOException {

        return null;
    }

    @GetMapping("/name")
    public String name() {
        log.info("status");
        return "rushikesh";
    }

    @PostMapping("/uploadfiles")
    public ResponseEntity<String> uploadMultipleFiles(@RequestParam("files") MultipartFile[] multipartFiles) throws IOException {
        if(multipartFiles.length==0 ){
            throw new RuntimeException("File should not be null");
        }
        Path testfolder = Paths.get("TESTFOLDER");
        createFolder(testfolder);
        log.info("status of multipartFiles {}", Arrays.asList(multipartFiles).isEmpty());
        log.info("length of file {}", multipartFiles.length);

            for (MultipartFile file : multipartFiles) {
                //Files.copy(file.getInputStream(),Paths.get(testfolder,file.getOriginalFilename(),  StandardCopyOption.REPLACE_EXISTING))

                writeFile(file,testfolder);
                //Files.write()


            }
            return ResponseEntity.ok("Files are uploaded");



    }

    @GetMapping("downloadFile/{filename}")
    public ResponseEntity.BodyBuilder downloadFile(@PathVariable("filename") String fileName, HttpServletResponse response) throws IOException {
         System.out.println(fileName);

         Path pathForDownloadFile = Paths.get("ImageFolder", fileName);
        byte[] bytes = Files.readAllBytes(pathForDownloadFile);
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + fileName + "\"";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue);


    }

    }

