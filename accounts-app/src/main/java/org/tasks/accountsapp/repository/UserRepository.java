package org.tasks.accountsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tasks.accountsapp.dto.ExtUsersDto;
import org.tasks.accountsapp.model.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByLogin(String login);

    @Modifying
    @Query(
            value = "update users ac set password_hash = :passwordHash where login = :login",
            nativeQuery = true
    )
    void editPassword(String login, String passwordHash);

}
