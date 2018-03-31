package com.example.video.controller;


import com.example.video.dao.VideoMapper;
import com.example.video.global.GlobalMy;
import com.example.video.pojo.Video;
import com.example.video.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    VideoMapper videoMapper;

    @RequestMapping("/")
    public String getMain(Model model){

        List<Video> videos = videoMapper.selectAll();
        model.addAttribute("videos",videos);
        return "main";
    }



}
