package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * @author by KingOfTetris
 * @date 2023/6/1
 */

@Controller
public class FileUpAndDownLoadController {

    @RequestMapping("/test/up")
    //下面的MultipartFile是SpringMVC对文件上传进行了封装以后的类型
    //所以你得提前在springmvc.xml里配置，另外photo就是前端的name，要保存一致才能绑定
    //这个配置又得加依赖
    public String testUP(MultipartFile photo, HttpSession session) throws IOException {
        //photo已经被封装了，不需要你再用流去读写了，它就是一个具体的文件。
        //获取上传的文件的文件名
        String fileName = photo.getOriginalFilename(); //带后缀的文件名 后缀很重要
        //为了解决重名的问题，你就得改个名字，在后缀名前面加上UUID
        int lastIndexOf = fileName.lastIndexOf(".");//找到最后一个.的出现位置
//        String realFileName = fileName.substring(0,lastIndexOf);//真实文件名
        String suffixName = fileName.substring(lastIndexOf);//后缀名
        //现在用UUID拼接真实文件名
        String UUID = java.util.UUID.randomUUID().toString();
        fileName = UUID + suffixName;
        System.out.println(fileName);
        //现在我们要获取 上传准备存放的地址finalPath。
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //因为我们没有专门的文件服务器，就先放在tomcat服务器上
        //所以最后上传的是在tomcat服务器中，不是在你的本地文件。
        //getRealPath()等于你maven工程里war包的路径 里面加上"XXX" 就相当于在war包路径后加上/XXX
        String photoPath = servletContext.getRealPath("photo");
        //现在的问题是这个路径可能不存在
        //你就得提前创个目录
        File file = new File(photoPath);
        if (!file.exists()){
            file.mkdir();
        }
        //目录创建好以后
        //File.separator 系统不一样有的时候路径分隔符就不一样 有的是/ 有的是\
        //所以你最好用File.separator 自动匹配
        String finalPath = photoPath + File.separator + fileName;
        //有了最终存储地址，就可以上传了
        //SpringMVC也帮你搞定上传了，transferTo加上你最后的保存地址就行了，也不用再去手动读写复制了。
        //但是还有问题，这样上传，同名文件会被覆盖。
        photo.transferTo(new File(finalPath));
        return "success";
    }
}
