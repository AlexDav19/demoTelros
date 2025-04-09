package com.telros.demotelros.service;

import com.telros.demotelros.entity.Photo;
import com.telros.demotelros.entity.User;
import com.telros.demotelros.repository.PhotoRepository;
import com.telros.demotelros.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class UserPhotoService {
    @Value("photo/")
    private String photoDir;
    @Autowired
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;

    public UserPhotoService(UserRepository userRepository, PhotoRepository photoRepository) {
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
    }

    /**
     * Поиск фото пользователя по id
     *
     * @param userId id пользователя
     * @return {@link Photo} - фото пользователя
     * @throws EntityNotFoundException если данного пользователя не существует в базе данных
     */
    public Photo getUserPhotoById(Long userId) {
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new).getPhoto();
    }

    /**
     * Добавление/изменение фото пользователя
     *
     * @param userId id пользователя
     * @param file   новое фото пользователя
     * @throws EntityNotFoundException если данного пользователя не существует в базе данных
     */
    public void createUserPhoto(Long userId, MultipartFile file) throws IOException {
        User user = userRepository.findUserById(userId).orElseThrow(EntityNotFoundException::new);

        Path filePath = Path.of(photoDir, userId + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
            bis.transferTo(bos);
        }

        Photo photo = photoRepository.findByUserId(userId).orElse(new Photo());
        photo.setUser(user);
        photo.setFilePath(filePath.toString());
        photo.setFileSize(file.getSize());
        photo.setMediaType(file.getContentType());
        photo.setData(generateImageData(filePath));

        photoRepository.save(photo);
        userRepository.findById(userId).orElseThrow(EntityNotFoundException::new).setPhoto(photo);
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


    /**
     * Формат фото для хранения в БД
     *
     * @param filePath путь файла
     */
    private byte[] generateImageData(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();

            ImageIO.write(preview, getExtension(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }

    /**
     * Удаление фото пользователя
     *
     * @param userId id пользователя
     * @throws EntityNotFoundException если данного пользователя не существует в базе данных
     */
    public void deleteUserPhoto(Long userId) {
        Long photoId = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new).getPhoto().getId();
        userRepository.findById(userId).orElseThrow(EntityNotFoundException::new).setPhoto(null);
        photoRepository.deleteById(photoId);
    }
}
