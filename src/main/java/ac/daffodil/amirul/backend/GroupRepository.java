package ac.daffodil.amirul.backend;

import ac.daffodil.amirul.backend.data.entity.Groups;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Groups, Long> {
    Page<Groups> findByGroupNameLikeIgnoreCase(String groupName, Pageable page);

    int countByGroupNameLikeIgnoreCase(String groupName);

    /*@Override
    List<Groups> findAll();*/

}
