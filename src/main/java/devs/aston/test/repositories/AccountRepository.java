package devs.aston.test.repositories;

import devs.aston.test.models.Account;
import devs.aston.test.models.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByNumber(String number);

    List<Account> findByBeneficiaryName(String name);

}
