package tw.Final.FinalS1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.Final.FinalS1.model.ContentManagementModel;

public interface ContentManagementRepository extends JpaRepository<ContentManagementModel, Long> {

}
