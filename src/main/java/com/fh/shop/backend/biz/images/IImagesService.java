package com.fh.shop.backend.biz.images;

import com.fh.shop.backend.po.images.Images;

import java.util.List;

public interface IImagesService {

    void addImages(Images images);

    List<Images> findProductImages(Integer id);

    void deleteImages(String s);

    void addBatchImages(List<Images> imagesList);
}
