package tw.rc.rcs1.repository;

import org.springframework.data.repository.CrudRepository;

import tw.rc.rcs1.model.UserV2;
/*
 * 基本款: CrudRepository
 * 分頁款: PaginAndSortRepository
 * 超強款: JpaRepository
 * -------------------------------------------
 * 
 * save()       => 增&修
 * saveAll(List, Set, Map, [])
 * 
 * deleteById() => 刪除
 * delete(物件)
 * 
 * 
 * findById()   => 查詢
 * existsById() => true/false
 * 
 * count() => 筆數
 * 
 * 
 */


public interface UserRepository extends CrudRepository<UserV2, Long> {

}
