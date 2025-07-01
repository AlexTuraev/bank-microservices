package org.tasks.accountsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tasks.aaacommons.enums.CurrencyType;
import org.tasks.accountsapp.model.BankAccountEntity;

import java.math.BigDecimal;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {

    @Query(
            value = """
                update bank_account b 
                            set value = value + :deltaValue 
                            from users u 
                                        where u.id = b.user_id and b.currency = :currencyType and u.login = :login
            """,
            nativeQuery = true
    )
    @Modifying
    void changeCash(BigDecimal deltaValue, String currencyType, String login);

}
