/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.studio.meowtoon.animesign.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studio.meowtoon.animesign.entity.Resource;
import com.studio.meowtoon.animesign.repository.ResourceRepository;

/**
 * @author h.adachi
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ResourceImageService {

    ///////////////////////////////////////////////////////////////////////////
    // Field

    @NonNull
    private ResourceRepository repository;

    ///////////////////////////////////////////////////////////////////////////
    // public Method

    @Transactional
    public void getResourceInfo() {
        try {
            List<Resource> _resourceList = repository.findByUseOrderByIdAsc(true);
            for (Resource _resource : _resourceList) {
                String _url = _resource.getAttrSrc();
                if (_url == null) {
                    continue;
                }
                if (_resource.getAttrSrcWidth() != null && _resource.getAttrSrcHeight() != null) {
                    continue;
                }
                log.debug("attrSrc: " + _url);
                request(_resource);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // private Method

    private void request(Resource resource) {
        BufferedImage _image = null;
        try {
            URL _url = new URL(resource.getAttrSrc());
            _image = ImageIO.read(_url);
            if (_image == null) {
                // TODO: log処理
            }
            // リソース画像の縦横のpixel値を取得
            Integer _width = _image.getWidth();
            Integer _height = _image.getHeight();
            resource.setAttrSrcWidth(_width.longValue());
            resource.setAttrSrcHeight(_height.longValue());
            repository.save(resource);
        } catch (IOException ioe) {
            log.error(ioe.getMessage());
            throw new RuntimeException(ioe);
        }
    }
}
