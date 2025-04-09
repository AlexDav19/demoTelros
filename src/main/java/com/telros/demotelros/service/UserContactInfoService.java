package com.telros.demotelros.service;

import com.telros.demotelros.dto.response.UserContactInfoResponse;
import com.telros.demotelros.entity.User;
import com.telros.demotelros.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserContactInfoService {
    @Autowired
    private final UserRepository userRepository;

    public UserContactInfoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Поиск контактной информации пользователя по id
     *
     * @param userId id пользователя
     * @return {@link UserContactInfoResponse} - контактная информация пользователя
     * @throws EntityNotFoundException если данного пользователя не существует в базе данных
     */
    public UserContactInfoResponse getUserById(Long userId) {

        return new UserContactInfoResponse(userRepository.findById(userId).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Изменение контактной информации пользователя
     *
     * @param userId    id пользователя
     * @param telephone новый телефон пользователя
     * @param email     новый email пользователя
     * @return {@link UserContactInfoResponse} - изменненая контактная информация пользователя
     * @throws EntityNotFoundException если данного пользователя не существует в базе данных
     */
    public UserContactInfoResponse updateUser(Long userId, String telephone, String email) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        if (telephone == null && email == null) {
            return new UserContactInfoResponse(user);
        }
        if (telephone == null) {
            User updateUser = new User(userId, user.getLastName(), user.getFirstName(), user.getSurName(), user.getBirthday(), email, user.getTelephone());
            return new UserContactInfoResponse(userRepository.save(updateUser));
        }
        if (email == null) {
            User updateUser = new User(userId, user.getLastName(), user.getFirstName(), user.getSurName(), user.getBirthday(), user.getEmail(), telephone);
            return new UserContactInfoResponse(userRepository.save(updateUser));
        }
        User updateUser = new User(userId, user.getLastName(), user.getFirstName(), user.getSurName(), user.getBirthday(), email, telephone);
        return new UserContactInfoResponse(userRepository.save(updateUser));
    }
}
