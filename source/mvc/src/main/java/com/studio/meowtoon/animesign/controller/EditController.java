package com.studio.meowtoon.animesign.controller;

import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest; 

import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studio.meowtoon.animesign.entity.Resource;
import com.studio.meowtoon.animesign.form.ResourceForm;
import com.studio.meowtoon.animesign.service.ResourceService;
import com.studio.meowtoon.animesign.service.ResourceImageService;
import com.studio.meowtoon.animesign.service.WriteTimelineService;
import com.studio.meowtoon.animesign.service.EasyToRawTimelineService;
import com.studio.meowtoon.animesign.response.EasyTimelineResponse;

///////////////////////////////////////////////////////////////////////////////
/**
 * @author h.adachi
 */
@Slf4j
@Controller
@Scope(value="session")
public class EditController {
    
    ///////////////////////////////////////////////////////////////////////////
    // Field
    
    private static final String TIMELINE_RESPONSE_BEAN_ID = "easyTimelineResponse";
    
    @Inject
    private HttpServletRequest request = null;
    
    @Inject
    private ApplicationContext context = null;
    
    @Inject
    private Mapper mapper = null;
    
    @Inject
    private ResourceService resourceService = null;
    
    @Inject
    private WriteTimelineService writeTimelineService = null;
    
    @Inject
    private EasyToRawTimelineService easyToRawTimelineService = null;
    
    @Inject
    private ResourceImageService resourceImageService = null;
    
    ///////////////////////////////////////////////////////////////////////////
    // public methods
    
    @RequestMapping(
        value = "/edit/chunk",
        method = RequestMethod.GET
    )
    public void getEditChunk(
        Model model
    ) {
        log.info("GET request to '/edit/chunk' from " + request.getRemoteHost());
    }
    
    // chunk を呼び出すボタン
    //  => chunk_id のプルダウンで検索(選択)出来るUI　※名前を付けられる
    
    // chunk id ごとの easytimeline リストを表示する
    //  => リストへの行の追加・更新・削除、上下移動
    //  => chunk で紐付けられた easytimeline を名前をつけてパターンかエピソードに
    //     保存出来る
    
    // ある chunk の一部分をデリートORカットする機能
    // ある chunk と別の chunk をマージする機能
    // ある chunk の一部分に別の chunk をインサートORペーストする機能
    // ある chunk の選択部分のオフセットを一括してシフトする機能
    // ある chunk の選択部分のパラメータの値を一括して増減する機能
    // ある chunk の選択部分のパラメータの一部を置き換える機能
    
    ///////////////////////////////////////////////////////////////////////////
    // private methods
    
}