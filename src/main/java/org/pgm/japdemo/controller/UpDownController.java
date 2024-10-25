    package org.pgm.japdemo.controller;


    import groovyjarjarasm.asm.tree.TryCatchBlockNode;
    import lombok.Getter;
    import lombok.extern.log4j.Log4j2;
    import net.coobird.thumbnailator.Thumbnailator;
    import org.pgm.japdemo.dto.BoardDTO;
    import org.pgm.japdemo.dto.upload.UploadFileDTO;
    import org.pgm.japdemo.dto.upload.UploadResultDTO;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.boot.autoconfigure.ssl.SslProperties;
    import org.springframework.core.io.FileSystemResource;
    import org.springframework.core.io.Resource;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;

    import java.io.File;
    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Path;
    import java.nio.file.Paths;
    import java.util.*;

    @Controller
    @Log4j2
    @RequestMapping("/upload")
    public class UpDownController {

        @Value("${org.pgm.japdemo.upload.path}")
        private String uploadPath;

        @GetMapping("/uploadForm")
        public void uploadForm() {

        }

        @PostMapping("/uploadPro")
        public void uploadPro(UploadFileDTO uploadFileDTO, BoardDTO boardDTO, Model model) {
            log.info(boardDTO);
            if (uploadFileDTO.getFiles() != null) {
                final List<UploadResultDTO> list = new ArrayList<>();
                uploadFileDTO.getFiles().forEach(multipartFile -> {
                    String originalName = multipartFile.getOriginalFilename();
                    log.info(originalName);

                    String uuid = UUID.randomUUID().toString();
                    Path savePath = Paths.get(uploadPath, uuid + "_" + originalName);
                    boolean image = false;
                    try {
                        multipartFile.transferTo(savePath); //서버에 파일저장
                        if (Files.probeContentType(savePath).startsWith("image")) {
                            image = true;
                            File thumbFile = new File(uploadPath, "s_" + uuid + "_" + originalName);
                            Thumbnailator.createThumbnail(savePath.toFile(), thumbFile, 100, 100);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    list.add(UploadResultDTO.builder()
                            .uuid(uuid)
                            .fileName(originalName)
                            .image(image)
                            .build()
                    );
                    model.addAttribute("list", list);
                    model.addAttribute("uploadPath", uploadPath);
                });
            }
        }

        @GetMapping("/view/{fileName}")
        @ResponseBody
        public ResponseEntity<Resource> viewFileGet(@PathVariable("fileName") String fileName) {
            Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
            String resourceName = resource.getFilename();
            HttpHeaders headers = new HttpHeaders();

            try {
                headers.add("Content-type", Files.probeContentType(resource.getFile().toPath()));
            } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }
            return ResponseEntity.ok().headers(headers).body(resource);
        }

            @GetMapping("/remove")
            public String removeFile(@RequestParam("fileName") String fileName) {
                Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
                String resourceName = resource.getFilename();

                Map<String, Boolean> resultMap = new HashMap<>();
                boolean removed = false;

                try {
                    String contentType = Files.probeContentType(resource.getFile().toPath());
                    removed = resource.getFile().delete();

        //섬네일이 존재한다면
                    if (contentType.startsWith("image")) {
                       String fileName1=fileName.replace("s_","");
                       File originalFile = new File(uploadPath + File.separator + fileName1);
                       originalFile.delete();

                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }

                resultMap.put("result", removed);
                return "/upload/uploadForm";
            }

    }