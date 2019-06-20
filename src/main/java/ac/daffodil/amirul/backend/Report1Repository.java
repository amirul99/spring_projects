package ac.daffodil.amirul.backend;


import ac.daffodil.amirul.backend.data.entity.Report1;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Report1Repository extends JpaRepository<Report1, Long> {
    
    Page<Report1> findByRDate(Date date,Pageable page);
    int countByRDate(Date date);
    //List<Report1> findByRDateOrderByRDate(List<Report1> list);
    List<Report1> findAllByOrderByRDateAsc();

   

}
