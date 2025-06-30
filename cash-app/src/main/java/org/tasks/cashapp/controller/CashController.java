package org.tasks.cashapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tasks.cashapp.service.CashService;
import org.tasks.commonsapp.dto.CashDto;

@RestController
public class CashController {

    private final CashService cashService;

    public CashController(CashService cashService) {
        this.cashService = cashService;
    }

    @PostMapping("/cash")
    public ResponseEntity<?> cash(
            @RequestBody CashDto cashDto
    ) {
        cashService.changeCash(cashDto);
        return ResponseEntity.ok().build();
    }

}
