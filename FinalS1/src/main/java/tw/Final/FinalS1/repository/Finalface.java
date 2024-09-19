package tw.Final.FinalS1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tw.Final.FinalS1.model.FinalMember;

@Repository
public interface Finalface extends JpaRepository<FinalMember, Long> {

    @Query("SELECT fm FROM FinalMember fm WHERE " +
           "fm.account LIKE %:key% OR fm.passwd LIKE %:key% OR " +
           "fm.phone LIKE %:key% OR fm.addr LIKE %:key%")
    Page<FinalMember> search(@Param("key") String key, Pageable pageable);

    void deleteById(Long id); // 删除方法 
}