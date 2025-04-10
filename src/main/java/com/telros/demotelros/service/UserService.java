package com.telros.demotelros.service;

import com.telros.demotelros.dto.request.UserRequest;
import com.telros.demotelros.dto.response.UserResponse;
import com.telros.demotelros.entity.User;
import com.telros.demotelros.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Поиск пользователя по id
     *
     * @param userId id пользователя
     * @return {@link User} - пользователь
     * @throws EntityNotFoundException если данного пользователя не существует в базе данных
     */
    public UserResponse getUserById(Long userId) {
        return new UserResponse(userRepository.findById(userId).orElseThrow(EntityNotFoundException::new));
    }

    /**
     * Добавление пользователя
     *
     * @param userRequest новый пользователь
     * @return {@link User} - пользователь
     */
    public User createUser(UserRequest userRequest) {
        User user = new User(userRequest.lastName(), userRequest.firstName(), userRequest.surName(), userRequest.birthday(), userRequest.email(), userRequest.telephone());
        return userRepository.save(user);
    }

    /**
     * Изменение данных пользователя
     *
     * @param userId id пользователя
     * @param user   новые данные пользователя
     * @return {@link User} - изменненый пользователь
     * @throws EntityNotFoundException если данного пользователя не существует в базе данных
     */
    public User updateUser(Long userId, UserRequest user) {
        userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        User updateUser = new User(userId, user.lastName(), user.firstName(), user.surName(), user.birthday(), user.email(), user.telephone());
        return userRepository.save(updateUser);
    }

    /**
     * Удаление пользователя
     *
     * @param userId id пользователя
     * @return {@link User} - данные удаленного пользователя
     * @throws EntityNotFoundException если данного пользователя не существует в базе данных
     */
    public User deleteUser(Long userId) {
        User deleteUser = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        userRepository.deleteById(userId);
        return deleteUser;
    }
}
