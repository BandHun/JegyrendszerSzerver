package hu.bandi.szerver.web.controllers;

import hu.bandi.szerver.models.Company;
import hu.bandi.szerver.models.JoinCompanyRequest;
import hu.bandi.szerver.models.User;
import hu.bandi.szerver.services.implementations.CurrentUserService;
import hu.bandi.szerver.services.implementations.UserServiceImpl;
import hu.bandi.szerver.services.interfaces.CompanyService;
import hu.bandi.szerver.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private final CompanyService companyService;

    private UserService userService;

    public CompanyController(final CompanyService companyService, UserService userService) {
        this.companyService = companyService;
        this.userService = userService;
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

    @PostMapping("/join/{id}")
    public ResponseEntity<?> addCompany(@PathVariable("id") final Long companyId) {
        User toadd = CurrentUserService.getCurrentUser();
        Company company = companyService.findById(companyId);
        userService.addCompany(company);
        companyService.addUser(companyId,toadd);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/joinrequest/create")
    public ResponseEntity<JoinCompanyRequest> createJoinRequest(@RequestBody final Long companyId) {
        return new ResponseEntity<>(companyService.createJoinRequest(companyId), HttpStatus.OK);
    }

    @GetMapping("/joinrequest/getbyuser")
    public ResponseEntity<List<JoinCompanyRequest>> getJoinRequestsbyuser() {
        System.out.println("JOIN REQUEST GET");
        return new ResponseEntity<>(companyService.getJoinRequests(), HttpStatus.OK);
    }

    @GetMapping("/joinrequest/getbycompany")
    public ResponseEntity<List<JoinCompanyRequest>> getJoinRequestsbycompany() {
        System.out.println("JOIN REQUEST GET");
        return new ResponseEntity<>(companyService.getJoinRequestsByCompany(), HttpStatus.OK);
    }

    @PostMapping("/joinrequest/accept")
    public ResponseEntity<?> acceptJoinRequest(@RequestBody JoinCompanyRequest request) {

        //userService.addCompany(request.getUser(),request.getCompany());
        companyService.acceptJoinRequest(request);
        return new ResponseEntity<>( HttpStatus.OK);
    }


    @PostMapping("/joinrequest/decline")
    public ResponseEntity<?> declineJoinRequest(@RequestBody JoinCompanyRequest request) {
        companyService.declineJoinRequest(request);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable("id") final Long id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
