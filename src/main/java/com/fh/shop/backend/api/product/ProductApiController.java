package com.fh.shop.backend.api.product;

import com.fh.shop.backend.biz.product.IProductService;
import com.fh.shop.backend.common.ResponseEnum;
import com.fh.shop.backend.common.ServerResponse;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductApiController {

    @Resource(name = "productService")
    private IProductService productService;

    @RequestMapping("findProductApiList")
    public Object findProductApiList(String callback){
        //查询数据
        List<ProductApiVo> productApiVoList = productService.findProductApiList();
        //将要响应的数据，用构造参数进行传递
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(ServerResponse.successData(ResponseEnum.SUCCESS, productApiVoList));
        //设置参数callback对应的值
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }
}
