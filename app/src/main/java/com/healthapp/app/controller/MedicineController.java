package com.healthapp.app.controller;

import com.healthapp.app.dto.MedicineRequest;
import com.healthapp.app.service.MedicineService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/medicine")
@CrossOrigin(origins = "*")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @PostMapping("/search")
    public String searchMedicine(@RequestBody MedicineRequest request) {

        System.out.println("Incoming: " + request.getMedicineName()); // debug

        return medicineService.getMedicineInfo(request.getMedicineName());
    }
}