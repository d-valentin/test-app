package devs.aston.test.controllers;

import devs.aston.test.dto.beneficiary.BeneficiaryDto;
import devs.aston.test.dto.beneficiary.BeneficiaryWithAccountListDto;
import devs.aston.test.mappers.BeneficiaryMapper;
import devs.aston.test.services.BeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beneficiary")
@RequiredArgsConstructor
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    private final BeneficiaryMapper beneficiaryMapper;

    @PostMapping
    public BeneficiaryDto create(@RequestBody BeneficiaryDto dto) {
        return beneficiaryMapper.toLongDto(beneficiaryService.create(dto));
    }

    @GetMapping
    public BeneficiaryWithAccountListDto find(@RequestParam String name) {
        return beneficiaryService.find(name);
    }
}
