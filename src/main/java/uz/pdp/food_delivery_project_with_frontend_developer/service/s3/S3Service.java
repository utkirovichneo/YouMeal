package uz.pdp.food_delivery_project_with_frontend_developer.service.s3;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @SneakyThrows
    public Map<String, String> uploadFile(MultipartFile file) {
        String fileName = generateFileName(file);
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));


        return Map.of("fileName", fileName,
                "prefix", prefix(file),
                "url", getFileUrl(fileName));
    }

    private String generateFileName(MultipartFile file) {
        return UUID.randomUUID() + "." + prefix(file);
    }

    private String prefix(MultipartFile file) {
        return Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1);
    }

    private String getFileUrl(String key) {
        return String.format("https://%s.s3.eu_north_1.amazonaws.com/%s",
                bucketName,
                key);
    }
}