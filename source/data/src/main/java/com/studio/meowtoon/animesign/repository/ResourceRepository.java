
package com.studio.meowtoon.animesign.repository;

import java.lang.Long;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studio.meowtoon.animesign.entity.Resource;

///////////////////////////////////////////////////////////////////////////////
/**
 * @author h.adachi
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    
    List<Resource> findByAttrIdNotAndIsUsedOrderByIdAsc(String attrId, boolean isUsed);
    
    List<Resource> findByIsUsedOrderByIdAsc(boolean isUsed);
    
    Resource findByOptionsLike(String options);
    
    // TODO: 1つではない場合 
    Resource findByAttrId(String attrId);
    
}