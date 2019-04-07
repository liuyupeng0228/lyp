package com.fh.shop.backend.biz.images;

import com.fh.shop.backend.mapper.images.IImagesMapper;
import com.fh.shop.backend.po.images.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IImagesServiceImpl implements IImagesService {

    @Autowired
    private IImagesMapper imagesMapper;

    @Override
    public void addImages(Images images) {
        imagesMapper.addImages(images);
    }

    @Override
    public List<Images> findProductImages(Integer id) {
        return imagesMapper.findProductImages(id);
    }

    @Override
    public void deleteImages(String s) {
        imagesMapper.deleteImages(s);
    }

    @Override
    public void addBatchImages(List<Images> imagesList) {
        imagesMapper.addBatchImages(imagesList);
    }

}
