package org.tasks.accountsapp.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.tasks.accountsapp.dto.ChangePswDto;
import org.tasks.accountsapp.dto.ExtUsersDto;
import org.tasks.accountsapp.dto.UserDto;
import org.tasks.accountsapp.mapper.ExtUserMapper;
import org.tasks.accountsapp.mapper.UserMapper;
import org.tasks.accountsapp.model.ExtUsersModel;
import org.tasks.accountsapp.model.UserEntity;
import org.tasks.accountsapp.repository.UserRepository;
import org.tasks.accountsapp.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ExtUserMapper extUserMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, ExtUserMapper extUserMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.extUserMapper = extUserMapper;
    }

    @Override
    public Boolean createAccount(UserDto accountDto) {
        UserEntity userEntity = userMapper.mapToEntity(accountDto);

        var result = userRepository.save(userEntity);

        return true;
    }

    @Override
    public UserDto findByLogin(String login) {
        List<UserEntity> entities = userRepository.findByLogin(login);
        if (entities.size() > 1) {
            throw new RuntimeException("More than one account found for login: " + login);
        }

        return entities.stream().map(userMapper::mapToDto).findFirst().orElse(null);
    }

    @Override
    @Transactional
    public void editPassword(ChangePswDto changePswDto) {
        userRepository.editPassword(changePswDto.getLogin(), changePswDto.getPasswordHash());
    }

    @Override
    public ExtUsersDto getUsersData(String login) {
        List<UserEntity> users = userRepository.findAll();
        UserEntity currentUser = users.stream().filter(u -> u.getLogin().equals(login)).findFirst().orElseThrow();

        ExtUsersModel model = new ExtUsersModel();
        model.setUsers(users);
        model.setName(login);
        model.setBirthday(currentUser.getBirthdate());

        return extUserMapper.toDto(model);
    }

}
