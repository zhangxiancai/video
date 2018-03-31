package com.example.video.controller;

import com.example.video.dao.VideoMapper;
import com.example.video.global.GlobalMy;
import com.example.video.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    VideoMapper videoMapper;
    @RequestMapping("")
    public String manage(){

        return "manage/login";
    }

    @RequestMapping("/login")
    public String manageLogin(String password,HttpServletRequest request){
        String key = (String) request.getSession().getAttribute("key");

        if ("wit".equals(password) || "wit".equals(key)){
            request.getSession().setAttribute("key","wit");
            return "manage/push";
        }
        return "manage/login";
    }

    @RequestMapping("/pushVideo")
    @Transactional
    public String pushVideo(Model model, HttpServletRequest request, @RequestParam("file") MultipartFile file, String name) throws IOException {
        String key = (String) request.getSession().getAttribute("key");
        if (key == null) {
            return "redirect:/";
        }

        String fileName= System.currentTimeMillis() + "-"+file.getOriginalFilename();
        String location = GlobalMy.LOCATION+System.currentTimeMillis() + "-"+file.getOriginalFilename();
        videoMapper.insert(name,fileName);
        FileUtil.writeToLocal(file,location);

        return "redirect:/manage/login";

    }

}
