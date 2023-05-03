package devs.aston.test.services;

import devs.aston.test.dto.beneficiary.BeneficiaryDto;
import devs.aston.test.dto.beneficiary.BeneficiaryWithAccountListDto;
import devs.aston.test.models.Beneficiary;

import java.util.Optional;

public interface BeneficiaryService {

    Beneficiary create(BeneficiaryDto dto);

    Optional<Beneficiary> findByName(String name);

    BeneficiaryWithAccountListDto find(String name);

}
