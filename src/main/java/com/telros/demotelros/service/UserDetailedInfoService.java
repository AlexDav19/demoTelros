package com.telros.demotelros.service;

import com.telros.demotelros.dto.response.UserDetailedInfoResponse;
import com.telros.demotelros.entity.User;
import com.telros.demotelros.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserDetailedInfoService {
    @Autowired
    private final UserRepository userRepository;

    public UserDetailedInfoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Поиск детальной информации пользователя по id
     *
     * @param userId id пользователя
     * @return {@link UserDetailedInfoResponse} - детальная информация пользователя
     * @throws EntityNotFoundException если данного пользователя не существует в базе данных
     */
    public UserDetailedInfoResponse getUserById(Long userId) {

        return new UserDetailedInfoResponse(userRepository.findById(userId).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Изменение детальной информации пользователя
     *
     * @param userId   id пользователя
     * @param birthday новая дата рождения пользователя
     * @return {@link UserDetailedInfoResponse} - изменненая детальная информация пользователя
     * @throws EntityNotFoundException если данного пользователя не существует в базе данных
     */
    public UserDetailedInfoResponse updateUser(Long userId, LocalDate birthday) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        User updateUser = new User(userId, user.getLastName(), user.getFirstName(), user.getSurName(), birthday, user.getEmail(), user.getTelephone());
        return new UserDetailedInfoResponse(userRepository.save(updateUser));
    }

}
