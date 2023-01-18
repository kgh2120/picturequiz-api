package com.kk.picturequizapi.domain.report.ui;


import com.kk.picturequizapi.domain.report.application.ReportCreateService;
import com.kk.picturequizapi.domain.report.dto.ReportCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReportCreateController {

    private final ReportCreateService reportCreateService;

    @PostMapping("/reports")
    public ResponseEntity<Void> createReport(@RequestBody ReportCreateRequest dto) {
        reportCreateService.createReport(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
