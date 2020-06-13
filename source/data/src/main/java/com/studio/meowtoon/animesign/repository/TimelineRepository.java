
package com.studio.meowtoon.animesign.repository;

import java.lang.Long;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studio.meowtoon.animesign.entity.Timeline;

///////////////////////////////////////////////////////////////////////////////
/**
 * @author h.adachi
 */
@Repository
public interface TimelineRepository extends JpaRepository<Timeline, Long> {
    
    List<Timeline> findByIsUsedOrderByDelayAsc(boolean isUsed);
    
}