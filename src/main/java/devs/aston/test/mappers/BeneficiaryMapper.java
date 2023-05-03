package devs.aston.test.mappers;

import devs.aston.test.dto.beneficiary.BeneficiaryDto;
import devs.aston.test.dto.beneficiary.BeneficiaryWithAccountListDto;
import devs.aston.test.models.Beneficiary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BeneficiaryMapper {

    Beneficiary toModel(BeneficiaryDto dto);

    BeneficiaryDto toDto(Beneficiary beneficiary);

    BeneficiaryWithAccountListDto toLongDto(Beneficiary beneficiary);

}
