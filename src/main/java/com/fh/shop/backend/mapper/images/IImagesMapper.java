package com.fh.shop.backend.mapper.images;

import com.fh.shop.backend.po.images.Images;

import java.util.List;

public interface IImagesMapper {
    void addImages(Images images);

    List<Images> findProductImages(Integer id);

    void deleteImages(String s);

    List<Images> findImagesPath(List<Integer> idList);

    void deleteImagesById(List<Integer> idList);

    void addBatchImages(List<Images> imagesList);
}
