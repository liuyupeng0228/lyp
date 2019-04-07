package com.fh.shop.backend.controller.images;

import com.fh.shop.backend.biz.images.IImagesService;
import com.fh.shop.backend.po.images.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("images")
public class ImagesController {
    @Autowired
    private IImagesService imagesService;

   @RequestMapping("findProductImages")
    public String findProductImages(Integer id,ModelMap map){
        List<Images> imagesList = imagesService.findProductImages(id);
        map.put("imagesList",imagesList);
        return "images/images";
    }
}
