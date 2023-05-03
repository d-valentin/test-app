package devs.aston.test.repositories;

import devs.aston.test.models.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, UUID> {

    List<AccountTransaction> findByAccountNumber(String number);

}
