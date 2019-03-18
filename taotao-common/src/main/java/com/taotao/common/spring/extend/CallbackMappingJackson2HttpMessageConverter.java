package com.taotao.common.spring.extend;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * 统一扩展Json的转换类，使它自动适配jsonp
 */
public class CallbackMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    private String callbackName;

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String callbackParam = request.getParameter(callbackName);

        if(StringUtils.isEmpty(callbackParam)){
            //没有找到callback参数，直接返回json数据
            super.writeInternal(object,outputMessage);
        }
        else{
            JsonEncoding encoding = getJsonEncoding(outputMessage.getHeaders().getContentType());
            try{
                String result = callbackParam + "(" +super.getObjectMapper().writeValueAsString(object)+");";
                IOUtils.write(result,outputMessage.getBody(),encoding.getJavaName());
            }catch(JsonProcessingException ex){
                throw new HttpMessageNotWritableException("Could not write JSON" + ex.getMessage(),ex);
            }
        }
    }
    public String getCallbackName(){
        return callbackName;
    }

    public void setCallbackName(String callbackName){
        this.callbackName = callbackName;
    }
}
