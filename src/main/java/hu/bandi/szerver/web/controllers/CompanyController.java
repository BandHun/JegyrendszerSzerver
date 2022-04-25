package hu.bandi.szerver.web.controllers;

import hu.bandi.szerver.models.Company;
import hu.bandi.szerver.services.interfaces.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private final CompanyService companyService;

    public CompanyController(final CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAllCompany() {
        return new ResponseEntity<>(companyService.findAllCompany(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(companyService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Company> addCompany(@RequestBody final String name) {
        return new ResponseEntity<>(companyService.addCompany(name), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable("id") final Long id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
