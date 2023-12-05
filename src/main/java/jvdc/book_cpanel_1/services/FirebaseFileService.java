package jvdc.book_cpanel_1.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class FirebaseFileService {
    private Storage storage;

    @EventListener
    public void init(ApplicationReadyEvent event) {
        try {
            ClassLoader classLoader = event.getClass().getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccount.json")).getFile());
            InputStream is = new ClassPathResource("serviceAccount.json").getInputStream();

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(is))
                    .setStorageBucket("book-app-8a4c9.appspot.com")
                    .build();

            FirebaseApp.initializeApp(options);

            // Initialize the Cloud Storage instance
            this.storage = StorageOptions.getDefaultInstance().getService();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String saveTest(MultipartFile file) throws IOException{
        String imageName = generateFileName(file.getOriginalFilename());
        Map<String, String> map = new HashMap<>();
        map.put("firebaseStorageDownloadTokens", imageName);
        BlobId blobId = BlobId.of("book-app-8a4c9.appspot.com", "ProfileImages/" + imageName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setMetadata(map)
                .setContentType(file.getContentType())
                .build();
        storage.create(blobInfo, file.getInputStream());
        return imageName;
    }
    private String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "." + getExtension(originalFileName);
    }

    private String getExtension(String originalFileName) {
        return StringUtils.getFilenameExtension(originalFileName);
    }
}
