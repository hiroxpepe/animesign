
package com.studio.meowtoon.animesign.service;

import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studio.meowtoon.animesign.entity.Resource;
import com.studio.meowtoon.animesign.repository.ResourceRepository;

///////////////////////////////////////////////////////////////////////////////
/**
 * @author h.adachi
 */
@Slf4j
@Service
public class ResourceService {
    
    ///////////////////////////////////////////////////////////////////////////
    // Field
    
    @Inject
    private ApplicationContext context;
    
    @Inject
    private ResourceRepository repository;
    
    ///////////////////////////////////////////////////////////////////////////
    // public Method
    
    @Transactional
    public List<Resource> getResourceList() {
        try {
            // "default-background" は除外
            List<Resource> list = repository.findByAttrIdNotAndIsUsedOrderByIdAsc("default-background", true);
            if (list != null) {
                return list;
            } else {
                log.warn("resource list is empty.");
                return new LinkedList<Resource>();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    @Transactional
    public String getDefaultBackground() {
        try {
            // デフォルト背景のURLを返す
            Resource resource = repository.findByOptionsLike("default_background=true;");
            if (resource != null) {
                return resource.getAttrSrc();
            } else {
                log.warn("default-background is empty.");
                return "";
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
}