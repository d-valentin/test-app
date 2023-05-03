package devs.aston.test.repositories;

import devs.aston.test.models.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, UUID> {

    boolean existsByName(String name);

    Optional<Beneficiary> findByName(String name);

}
