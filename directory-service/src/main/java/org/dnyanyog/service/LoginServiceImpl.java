package org.dnyanyog.service;

import java.util.List;
import org.dnyanyog.common.ResponseCode;
import org.dnyanyog.dto.LoginRequest;
import org.dnyanyog.dto.LoginResponse;
import org.dnyanyog.encrypt.EncryptionUtils;
import org.dnyanyog.entity.Directory;
import org.dnyanyog.repo.DirectoryServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

  @Autowired DirectoryServiceRepository userRepo;

  @Autowired LoginResponse response;

  @Autowired EncryptionUtils encrypt;

  public LoginResponse validateUser(LoginRequest loginrequest) throws Exception {
    List<Directory> liuser = userRepo.findBymobileNumber(loginrequest.getmobileNumber());
    if (liuser.size() == 1) {
      Directory userdata = liuser.get(0);
      String encryptedpassword = userdata.getPassword();
      String getencryptedpassword = encrypt.encrypt(loginrequest.getPassword());
      if (getencryptedpassword.equalsIgnoreCase(encryptedpassword)) {
        response
            .getInstance()
            .setStatus(ResponseCode.Login_Success.getStatus())
            .setMessage(ResponseCode.Login_Success.getMessage());
      }
    } else {
      response
          .getInstance()
          .setStatus(ResponseCode.Login_Fail.getStatus())
          .setMessage(ResponseCode.Login_Fail.getMessage());
    }
    return response;
  }
}
