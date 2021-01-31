package top.javahai.confucius.service.oss.remote;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.oss.service.FileService;

/**
 * @author Hai
 * @program: zhkuschool-frame
 * @description:
 * @create 2021/1/12 - 15:08
 **/
@Api(value = "阿里云OSS文件管理",tags = "File RPC Controller")
@RestController
@RequestMapping("/admin/rpc/oss/file")
public class FileRpcController {

    @Autowired
    private FileService fileService;

    @ApiOperation("远程调用删除文件")
    @DeleteMapping("/delete")
    public ResultVO removeFile(
            @ApiParam(value = "文件的url")
            @RequestParam(value = "url") String url){
        fileService.removeFile(url);
        return ResultVO.ok();
    }


}
