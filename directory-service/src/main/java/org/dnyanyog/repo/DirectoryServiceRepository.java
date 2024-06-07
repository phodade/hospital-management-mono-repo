package org.dnyanyog.repo;

import java.util.List;
import org.dnyanyog.entity.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface DirectoryServiceRepository extends JpaRepository<Directory, Long> {
  List<Directory> findBymobileNumber(Long mobileNumber);
}
