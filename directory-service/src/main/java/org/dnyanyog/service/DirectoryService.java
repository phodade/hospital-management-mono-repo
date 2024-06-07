package org.dnyanyog.service;

import org.dnyanyog.dto.DirectoryServiceRequest;
import org.dnyanyog.dto.DirectoryServiceResponse;

public interface DirectoryService {
  public DirectoryServiceResponse addUser(DirectoryServiceRequest request);

  public DirectoryServiceResponse updateUser(Long userid, DirectoryServiceRequest request);

  public DirectoryServiceResponse getSingleUser(Long userid);

  public DirectoryServiceResponse Deleteuser(Long userid);
}
