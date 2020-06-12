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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studio.meowtoon.animesign.entity.Resource;
import com.studio.meowtoon.animesign.form.ResourceForm;
import com.studio.meowtoon.animesign.service.ResourceService;
import com.studio.meowtoon.animesign.service.ResourceImageService;
import com.studio.meowtoon.animesign.service.WriteTimelineService;
import com.studio.meowtoon.animesign.service.EasyToRawTimelineService;
import com.studio.meowtoon.animesign.response.Response;

///////////////////////////////////////////////////////////////////////////////
/**
 * @author h.adachi
 */
@Slf4j
@Controller
@Scope(value="session")
public class TimelineController {
    
    ///////////////////////////////////////////////////////////////////////////
    // Field
    
    private static final String RESPONSE_BEAN_ID = "response";
    
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
    
    // 初回リクエスト /index.html ページを返す
    // ※現状では画像ファイルを HTML に埋め込む
    @RequestMapping(
        value = "/index",
        method = RequestMethod.GET
    )
    public void getIndex(
        Model model
    ) {
        log.info("GET request to '/index' from " + request.getRemoteHost());
        
        // resource のリスト取得
        List<ResourceForm> resourceFormList = new LinkedList<ResourceForm>();
        for (Resource resource : resourceService.getResourceList()) {
            resourceFormList.add(mapper.map(resource, ResourceForm.class));
        }
        
        // デフォルト背景URL取得
        String defaultBackground = resourceService.getDefaultBackground();
        
        // model に対してHTMLで表示するデータをセット
        model.addAttribute("resourceList", resourceFormList);
        model.addAttribute("defaultBackground", defaultBackground);
    }
    
    // Ajax リクエストを受け、タイムライン再生用の JavaScript ファイルを動的生成する
    @RequestMapping(
        value="/build",
        method=RequestMethod.GET,
        headers="Accept=application/json"
    )
    public @ResponseBody Response getBuild() {
        log.info("Ajax GET request to '/build' from " + request.getRemoteHost());
        
        // timeline データ作成
        //buildTimeline(); // 一時的に停止
        
        // レスポンス用のオブジェクトを返す
        return (Response) context.getBean(
            RESPONSE_BEAN_ID,
            false,
            "write timeline list complete."
        );
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // private methods
    
    private void buildTimeline() {
        // resource 画像の情報取得
        resourceImageService.getResourceInfo();
        
        // timeline のデータを再構築
        easyToRawTimelineService.convertEasyToRaw();
        
        // timeline 再生用の JavaSctipt ファイルを出力
        writeTimelineService.createJavaScript();
    }
    
}