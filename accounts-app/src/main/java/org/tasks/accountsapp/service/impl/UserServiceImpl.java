package org.tasks.accountsapp.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.tasks.accountsapp.dto.ChangePswDto;
import org.tasks.accountsapp.dto.ExtUsersDto;
import org.tasks.accountsapp.dto.UserDto;
import org.tasks.accountsapp.dto.commons.dto.CashDto;
import org.tasks.accountsapp.dto.commons.enums.CurrencyType;
import org.tasks.accountsapp.mapper.ExtUserMapper;
import org.tasks.accountsapp.mapper.UserMapper;
import org.tasks.accountsapp.model.BankAccountEntity;
import org.tasks.accountsapp.model.ExtUsersModel;
import org.tasks.accountsapp.model.UserEntity;
import org.tasks.accountsapp.repository.BankAccountRepository;
import org.tasks.accountsapp.repository.UserRepository;
import org.tasks.accountsapp.service.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ExtUserMapper extUserMapper;
    private final BankAccountRepository bankAccountRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, ExtUserMapper extUserMapper, BankAccountRepository bankAccountRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.extUserMapper = extUserMapper;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    @Transactional
    public Boolean createAccount(UserDto accountDto) {
        UserEntity userEntity = userMapper.mapToEntity(accountDto);

        var savedUser = userRepository.save(userEntity);
        List<BankAccountEntity> bankAccounts = createNewBankAccounts(savedUser.getId());

        bankAccountRepository.saveAll(bankAccounts);

        return true;
    }

    private List<BankAccountEntity> createNewBankAccounts(Long userId) {
        List<BankAccountEntity> accounts = new ArrayList<>();
        accounts.add(new BankAccountEntity(userId, CurrencyType.rub, BigDecimal.ZERO));
        accounts.add(new BankAccountEntity(userId, CurrencyType.dollar, BigDecimal.ZERO));
        accounts.add(new BankAccountEntity(userId, CurrencyType.euro, BigDecimal.ZERO));

        return accounts;
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

        users.forEach(user -> user.setPasswordHash(""));

        model.setUsers(users);
        model.setName(currentUser.getName());
        model.setBirthdate(currentUser.getBirthdate());

        return extUserMapper.toDto(model);
    }

    @Override
    @Transactional
    public void changeCash(CashDto cashDto) {
        bankAccountRepository.changeCash(cashDto.getValue(), cashDto.getCurrency().getTitle(), cashDto.getLogin());
    }

}
