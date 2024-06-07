package org.dnyanyog.controller;

import org.dnyanyog.dto.DirectoryServiceRequest;
import org.dnyanyog.dto.DirectoryServiceResponse;
import org.dnyanyog.service.DirectoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectoryServiceController {

  @Autowired DirectoryServiceImpl userservice;

  @PostMapping(
      path = "/api/v1/directory/add",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public DirectoryServiceResponse addUser(@RequestBody DirectoryServiceRequest request)
      throws Exception {
    return userservice.addUser(request);
  }

  @PostMapping(path = "/api/v1/directory/{userid}")
  public DirectoryServiceResponse updateUser(
      @PathVariable long userid, @RequestBody DirectoryServiceRequest request) throws Exception {
    return userservice.updateUser(userid, request);
  }

  @GetMapping(path = "/api/v1/directory/{userid}")
  public DirectoryServiceResponse getSingleUser(@PathVariable long userid) {

    return userservice.getSingleUser(userid);
  }

  @DeleteMapping(path = "/api/v1/directory/{userid}")
  public DirectoryServiceResponse Deleteuser(@PathVariable long userid) {
    return userservice.deleteUser(userid);
  }
}
