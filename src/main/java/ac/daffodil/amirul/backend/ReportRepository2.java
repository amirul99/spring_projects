package ac.daffodil.amirul.backend;


import ac.daffodil.amirul.backend.data.entity.Report1;
import ac.daffodil.amirul.backend.data.entity.Report2;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository2 extends JpaRepository<Report2, Long> {
    
    /*Page<Report2> findByRDate(Date date,Pageable page);
    int countByRDate(Date date);*/
    //List<Report1> findByRDateOrderByRDate(List<Report1> list);
    //List<Report2> findAllByOrderByRDateAsc();

   

}
