package com.daud.datamaster.controller.rest;

import com.daud.datamaster.dto.request.EmployeeRequestDTO;
import com.daud.datamaster.dto.response.EmployeeResponseDTO;
import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import com.daud.datamaster.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<Void> createNewEmployee(@Valid @RequestBody EmployeeRequestDTO dto) {
        employeeService.createEmployee(dto);
        return ResponseEntity.created(URI.create("/employee")).build();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeResponseDTO> findEmployeeById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(employeeService.findEmployeeById(id));
    }

    @PutMapping("/employee/{employeeId}")
    public ResponseEntity<Void> updateEmployee(@PathVariable(name = "employeeId") UUID employeeId,
                                               @RequestBody EmployeeRequestDTO dto) {
        employeeService.updateEmployeeById(employeeId, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/employees")
    public ResponseEntity<ResultPageResponseDTO<EmployeeResponseDTO>> findEmployees(
           @RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
           @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
           @RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy,
           @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
           @RequestParam(name = "employeeName", required = false) String employeeName
    ) {
        return ResponseEntity.ok(employeeService.findEmployeesByName(pages, limit, sortBy, direction, employeeName));
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(name = "employeeId") UUID employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/employee/name/{employeeName}")
    public ResponseEntity<EmployeeResponseDTO> findEmployeeByName(@PathVariable(name = "employeeName") String employeeName) {
        return ResponseEntity.ok(employeeService.findEmployeeByName(employeeName));
    }
}
