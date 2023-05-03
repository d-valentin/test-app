package devs.aston.test.services;

import devs.aston.test.dto.beneficiary.BeneficiaryDto;
import devs.aston.test.dto.beneficiary.BeneficiaryWithAccountListDto;
import devs.aston.test.enums.Errors;
import devs.aston.test.exceptions.SimpleException;
import devs.aston.test.mappers.BeneficiaryMapper;
import devs.aston.test.models.Beneficiary;
import devs.aston.test.repositories.BeneficiaryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final BeneficiaryRepository repository;

    private final BeneficiaryMapper beneficiaryMapper;

    private final AccountService accountService;

    @Override
    @Transactional
    public Beneficiary create(BeneficiaryDto dto) {
        validateDto(dto);

        Beneficiary user = beneficiaryMapper.toModel(dto);
        return repository.save(user);
    }

    @Override
    public Optional<Beneficiary> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public BeneficiaryWithAccountListDto find(String name) {
        Optional<Beneficiary> opt = findByName(name);

        if (opt.isPresent()) {
            Beneficiary beneficiary = opt.get();
            BeneficiaryWithAccountListDto dto = beneficiaryMapper.toLongDto(beneficiary);
            dto.setAccounts(accountService.findByBeneficiary(name));
            return dto;
        }

        throw SimpleException.with(name, Errors.BENEFICIARY_BY_NAME_NOT_FOUND);
    }


    private void validateDto(BeneficiaryDto dto) {
        if (!StringUtils.hasLength(dto.getName())) {
            throw SimpleException.with(dto.getName(), Errors.NAME_CANNOT_BE_EMPTY);
        }
        if (repository.existsByName(dto.getName())) {
            throw SimpleException.with(dto.getName(), Errors.NAME_ALREADY_EXISTS);
        }
    }
}
