package org.tasks.accountsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tasks.accountsapp.model.AccountEntity;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByLogin(String login);
}
