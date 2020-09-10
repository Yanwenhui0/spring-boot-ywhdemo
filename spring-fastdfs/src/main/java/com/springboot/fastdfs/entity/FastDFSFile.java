package com.springboot.fastdfs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/9/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FastDFSFile {

    private String name;

    private String ext;

    private byte[] content;
}
