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

package com.studio.meowtoon.animesign.repository;

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

    List<Resource> findByAttrIdNotAndUseOrderByIdAsc(String attrId, boolean use);

    List<Resource> findByUseOrderByIdAsc(boolean use);

    List<Resource> findByOptions(String options); // FIXME: Like is unable to use why?

    Resource findByAttrId(String attrId); // FIXME: 1つではない場合

}