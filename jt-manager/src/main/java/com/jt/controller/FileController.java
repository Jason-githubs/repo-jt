package com.jt.controller;

import com.jt.service.FileService;
import com.jt.vo.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 完成文件上传的入门案例
     * url地址：http://localhost:8091/file
     * 请求参数:fileImage
     * 返回值：文件上传成功
     *
     * 利用SpringMvc中提供的工具API,实现文件上传的简化
     * MultipartFile
     * 实现步骤：
     *      1.接收资源文件
     *      2.准备文件上传目录
     *      3.准备文件上传的全路径    目录/文件名称
     */
    @RequestMapping("/file")
    public String file(MultipartFile fileImage){

        //2.准备文件上传的目录   D:/Tedu/TCGBIV/images
        String fileDirPath = "D:/Tedu/TCGBIV/images";
        File dirFile= new File(fileDirPath);
        //判断文件目录是否存在
        if(!dirFile.exists()){
            //如果文件目录没有，则应该新建目录
            dirFile.mkdirs();   //创建多级目录
        }
        //3.准备文件上传的全路径  路径+文件名称
        String fileName = fileImage.getOriginalFilename();  //文件名称.后缀
        File realFile = new File(fileDirPath+"/"+fileName);
        //将字节信息输出到文件中
        try {
            fileImage.transferTo(realFile); //实现文件上传
            return "文件上传成功!";
        } catch (IOException e) {
            e.printStackTrace();
            return "文件上传失败!";
        }
    }

    /**
     * 实现图片上传操作.
     * url地址:http://localhost:8091/pic/upload?dir=image
     * 参数信息:uploadFile
     * 返回值:ImageVO对象
     */
    @RequestMapping("/pic/upload")
    public ImageVO upload(MultipartFile uploadFile){

        return fileService.upload(uploadFile);
    }








}

