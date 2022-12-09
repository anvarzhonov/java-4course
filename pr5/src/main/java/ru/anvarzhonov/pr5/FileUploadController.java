//package ru.anvarzhonov.pr5;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import ru.anvarzhonov.pr5.service.StorageService;
//
//import java.util.stream.Collectors;
//
///**
// * todo Document type FileUploadController
// *
// * @author - Anvarzhonov Z.T. IKBO-20-19 on 14.11.2022 - 19:43
// */
//@Controller
//@RequiredArgsConstructor
//public class FileUploadController {
//    private final StorageService service;
//
//    @GetMapping("/err")
//    public String listUploadedFiles(Model model) {
//        var servedFiles = service.loadAll()
//            .map(path -> MvcUriComponentsBuilder
//                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
//                .build()
//                .toUri()
//                .toString())
//            .collect(Collectors.toList());
//
//        model.addAttribute("files", servedFiles);
//
//        return "uploadForm";
//    }
//
//    @GetMapping("/files/{fileName:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {
//        Resource file = service.loadAsResource(fileName);
//
//        return ResponseEntity
//            .ok()
//            .header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"")
//            .body(file);
//    }
//
//    @PostMapping("/")
//    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
//        service.store(file);
//
//        redirectAttributes.addFlashAttribute("message",
//            "successfully uploaded" + file.getOriginalFilename());
//
//        return "redirect:/";
//    }
//
//    //    @PostMapping("/upload")
//    //    public String handleFileUpload(@RequestParam String name, @RequestParam MultipartFile file) {
//    //        if (!file.isEmpty()) {
//    //            try (var stream = new BufferedOutputStream(
//    //                                    new FileOutputStream(name + "-uploaded"))) {
//    //                var bytes = file.getBytes();
//    //
//    //                stream.write(bytes);
//    //
//    //                return "Вы удачно загрузили " + name + " в " + name + "-uploaded !";
//    //            } catch (IOException e) {
//    //                e.printStackTrace();
//    //            }
//    //        }
//    //        return "";
//    //    }
//}
