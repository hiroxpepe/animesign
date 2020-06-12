
package com.studio.meowtoon.animesign.repository;

import java.lang.Long;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studio.meowtoon.animesign.entity.EasyTimeline;

///////////////////////////////////////////////////////////////////////////////
/**
 * @author h.adachi
 */
@Repository
public interface EasyTimelineRepository extends JpaRepository<EasyTimeline, Long> {
    
}