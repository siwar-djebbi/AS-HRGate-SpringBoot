package as.tn.hrgate.Controller;

import as.tn.hrgate.Entities.LibreDemandePret;
import as.tn.hrgate.Entities.LibreDemandeSituation;
import as.tn.hrgate.Service.FileStorageService;
import as.tn.hrgate.Service.IPretService;
import as.tn.hrgate.Service.ISituationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Situation")
@Slf4j
public class LibreDemandeSituationController {

    private final ISituationService situationService;
    private final FileStorageService fileStorageService;


    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/addSituationRequest")
    public ResponseEntity<LibreDemandeSituation> addSituationRequest(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam("situation") String situationJson
    ) {
        try {
            LibreDemandeSituation situation = new ObjectMapper().readValue(situationJson, LibreDemandeSituation.class);

            if (file != null && !file.isEmpty()) {
                String filePath = saveFile(file);
                situation.setFilePath(filePath);
            }

            LibreDemandeSituation savedSituation = situationService.addOrUpdateDemandeSituation(situation);
            return new ResponseEntity<>(savedSituation, HttpStatus.CREATED);

        } catch (IOException e) {
            log.error("Error handling file upload", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateSituationRequest")
    public ResponseEntity<LibreDemandeSituation> updateSituationRequest(
            @RequestParam("file") MultipartFile file,
            @RequestParam("situation") String situationJson
    ) {
        try {
            String filePath = saveFile(file);

            LibreDemandeSituation situation = new ObjectMapper().readValue(situationJson, LibreDemandeSituation.class);
            situation.setFilePath(filePath);

            LibreDemandeSituation updatedSituation = situationService.addOrUpdateDemandeSituation(situation);
            return new ResponseEntity<>(updatedSituation, HttpStatus.OK);

        } catch (IOException e) {
            log.error("Error handling file upload", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String saveFile(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        try (var inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath);
        }

        return filePath.toString();
    }

    @GetMapping("/get/{idLibreDemSituation}")
    LibreDemandeSituation getLibreDemSituation(@PathVariable("idLibreDemSituation") Long idLibreDemSituation)
    {
        return situationService.retrieveSituation(idLibreDemSituation);
    }
    @GetMapping("/allDemandesSituations")
    List<LibreDemandeSituation> getAllSituations() { return situationService.retrieveAllSituations();}
    @DeleteMapping("/delete/{idLibreDemSituation}")
    void deleteDemSituation(@PathVariable("idLibreDemSituation") Long idLibreDemSituation)
    {
        situationService.removeSituation(idLibreDemSituation);
    }

    @GetMapping("/emailsituation")
    public ResponseEntity<LibreDemandeSituation> getSituationByEmail(@RequestParam String email) {
        LibreDemandeSituation situation = situationService.getSituationByEmail(email);
        if (situation != null) {
            return ResponseEntity.ok(situation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/downloadFile/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName, HttpServletRequest request) {
        try {
            String decodedFileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8.toString());
            log.info("Decoded file name: " + decodedFileName);

            Resource resource = fileStorageService.loadFileAsResource(decodedFileName);

            String contentType = null;
            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } catch (Exception ex) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException ex) {
            log.error("Error loading file", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
