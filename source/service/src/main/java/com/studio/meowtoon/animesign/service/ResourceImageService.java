
package com.studio.meowtoon.animesign.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javax.inject.Inject;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Service
public class ResourceImageService {
    
    ///////////////////////////////////////////////////////////////////////////
    // Field
    
    @Inject
    private final ApplicationContext context = null;
    
    @Inject
    private ResourceRepository repository;
    
    ///////////////////////////////////////////////////////////////////////////
    // public Method
    
    @Transactional
    public void getResourceInfo() {
        try {
            List<Resource> resourceList = repository.findByIsUsedOrderByIdAsc(true);
            for (Resource resource : resourceList) {
                String url = resource.getAttrSrc();
                if (url == null) {
                    continue;
                }
                if (resource.getAttrSrcWidth() != null && resource.getAttrSrcHeight() != null) {
                    continue;
                }
                log.debug("attrSrc: " + url);
                request(resource);
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // private Method
    
    private void request(Resource resource) {
        BufferedImage image = null;
        try {
            URL url = new URL(resource.getAttrSrc());
            image = ImageIO.read(url);
            if (image == null) {
                // TODO: log処理
            }
            // リソース画像の縦横のpixel値を取得
            Integer width = image.getWidth();
            Integer height = image.getHeight();
            resource.setAttrSrcWidth(width.longValue());
            resource.setAttrSrcHeight(height.longValue());
            repository.save(resource);
        } catch (IOException ioe) {
            log.error(ioe.getMessage());
            throw new RuntimeException(ioe);
        }
    }
    
}