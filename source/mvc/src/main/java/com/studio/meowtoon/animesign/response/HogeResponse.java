package com.studio.meowtoon.animesign.response;

import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.studio.meowtoon.animesign.model.HogeModel;

///////////////////////////////////////////////////////////////////////////////
/**
 * @author h.adachi
 */
@Getter
@Component(value="hogeResponse")
@Scope(value="prototype")
public class HogeResponse extends Response {
    
    ///////////////////////////////////////////////////////////////////////////
    // Field
    
    @NonNull
    private List<HogeModel> hogeModelList;
    
    ///////////////////////////////////////////////////////////////////////////
    // Constructor
    
    public HogeResponse(List<HogeModel> hogeModelList, boolean isError, String message) {
        super(isError, message);
        this.hogeModelList = hogeModelList;
    }
    
}