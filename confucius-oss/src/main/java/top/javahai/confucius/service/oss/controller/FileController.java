package top.javahai.confucius.service.oss.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.javahai.confucius.frame.base.service.exception.BaseException;
import top.javahai.confucius.frame.common.result.ResultCodeEnum;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.oss.service.FileService;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Hai
 * @program: zhkuschool-frame
 * @description:
 * @create 2021/1/11 - 0:28
 **/
@Api(value = "阿里云OSS文件管理",tags = "File Controller")
@RestController
@RequestMapping("admin/oss/file")
public class FileController {

    @Autowired
    private FileService fileService;


    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public ResultVO<String> upload(
            @ApiParam(value = "文件",required = true)
            @RequestParam(value = "file") MultipartFile file,
            @ApiParam(value = "模块(文件目录)",required = true)
            @RequestParam(value = "module") String module)  {
        String url;

        try {

            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();

            url= fileService.upload(inputStream, module, originalFilename);
        }catch (Exception e){
            throw new BaseException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }

        return new ResultVO<String>().success().data(url).message("文件上传成功！");
    }
}
