package org.dnyanyog.service;

import java.util.Optional;
import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.DirectoryServiceRequest;
import org.dnyanyog.dto.DirectoryServiceResponse;
import org.dnyanyog.encrypt.EncryptionUtils;
import org.dnyanyog.entity.Directory;
import org.dnyanyog.repo.DirectoryServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectoryServiceImpl {

  @Autowired DirectoryServiceRepository userRepo;

  @Autowired EncryptionUtils encrypt;

  public DirectoryServiceResponse addUser(DirectoryServiceRequest request) throws Exception {
    DirectoryServiceResponse response = DirectoryServiceResponse.getInstance();
    Directory user = new Directory();
    user.setmobileNumber(request.getmobileNumber());
    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());
    user.setEncPassword(encrypt.encrypt(request.getPassword()));
    user.setRole(request.getRole());
    user.setUserid(request.getUserid());

    try {
      user = userRepo.save(user);

      response.setmobileNumber(user.getmobileNumber());
      response.setRole(user.getRole());
      response.setPassword(user.getPassword());
      response.setEncPassword(user.getEncPassword());
      response.setEmail(user.getEmail());
      response.setUserid(user.getUserid());

      response.setMessage(ResponseCode.Add_User_Success.getMessage());
      response.setStatus(ResponseCode.Add_User_Success.getStatus());

    } catch (Exception e) {
      response.setStatus(ResponseCode.Add_User_Fail.getStatus());
      response.setMessage(ResponseCode.Add_User_Fail.getMessage());
    }
    return response;
  }

  public DirectoryServiceResponse updateUser(Long userid, DirectoryServiceRequest request)
      throws Exception {
    DirectoryServiceResponse response = DirectoryServiceResponse.getInstance();
    Optional<Directory> receiveData = userRepo.findById(userid);
    if (receiveData.isEmpty()) {
      response.setMessage(ResponseCode.Update_User_Fail.getMessage());
      response.setStatus(ResponseCode.Update_User_Fail.getStatus());
      response.setUserid(userid);
    } else {
      Directory user = receiveData.get();
      user.setEmail(request.getEmail());
      user.setmobileNumber(request.getmobileNumber());
      user.setPassword(request.getPassword());
      user.setEncPassword(encrypt.encrypt(request.getPassword()));
      user.setRole(request.getRole());

      userRepo.save(user);

      response.setMessage(ResponseCode.Update_User_Success.getMessage());
      response.setStatus(ResponseCode.Update_User_Success.getStatus());
      response.setmobileNumber(user.getmobileNumber());
      response.setRole(user.getRole());
      response.setPassword(user.getPassword());
      response.setEncPassword(user.getEncPassword());
      response.setEmail(user.getEmail());
      response.setUserid(user.getUserid());
    }
    return response;
  }

  public DirectoryServiceResponse getSingleUser(Long userid) {
    DirectoryServiceResponse response = DirectoryServiceResponse.getInstance();
    Optional<Directory> receiveData = userRepo.findById(userid);
    if (receiveData.isEmpty()) {
      response.setMessage(ResponseCode.Search_User_Fail.getMessage());
      response.setStatus(ResponseCode.Search_User_Fail.getStatus());
      response.setUserid(userid);
    } else {
      Directory user = receiveData.get();
      response.setMessage(ResponseCode.Search_User_Success.getMessage());
      response.setStatus(ResponseCode.Search_User_Success.getStatus());
      response.setEmail(user.getEmail());
      response.setmobileNumber(user.getmobileNumber());
      response.setRole(user.getRole());
      response.setPassword(user.getPassword());
      response.setEncPassword(user.getEncPassword());
    }
    return response;
  }

  public DirectoryServiceResponse deleteUser(Long userid) {
    DirectoryServiceResponse response = DirectoryServiceResponse.getInstance();
    Optional<Directory> receiveData = userRepo.findById(userid);
    if (receiveData.isPresent()) {
      userRepo.deleteById(userid);
      response.setMessage(ResponseCode.Delete_User_Success.getMessage());
      response.setStatus(ResponseCode.Delete_User_Success.getStatus());
      response.setUserid(userid);
    } else {
      response.setMessage(ResponseCode.Delete_User_Fail.getMessage());
      response.setStatus(ResponseCode.Delete_User_Fail.getStatus());
      response.setUserid(userid);
    }
    return response;
  }
}
