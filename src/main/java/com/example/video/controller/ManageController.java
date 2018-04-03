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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    VideoMapper videoMapper;
    @RequestMapping("")
    public String manage(){

        return "manage/main";
    }
    @RequestMapping("/pushVideo")
    public String pushVideo(){

        return "manage/push";
    }
//    @RequestMapping("/login")
//    public String manageLogin(String password,HttpServletRequest request){
//        String key = (String) request.getSession().getAttribute("key");
//
//        if ("wit".equals(password) || "wit".equals(key)){
//            request.getSession().setAttribute("key","wit");
//            return "manage/main";
//        }
//        return "manage/login";
//    }

    @RequestMapping("/doPush")
    @Transactional
    public String doPush(Model model, HttpServletRequest request, @RequestParam("file") MultipartFile file, String name) throws IOException {

        String fileName= System.currentTimeMillis() + "-"+file.getOriginalFilename();
        String location = GlobalMy.LOCATION+fileName;
        videoMapper.insert(name,fileName);
        FileUtil.writeToLocal(file,location);

        return "manage/push";

    }

    @RequestMapping("/deleteVideo")
    public String deleteVideo(Model model){

        List<Video> videos = videoMapper.selectAll();
        model.addAttribute("videos",videos);
        return "manage/delete";
    }
    @RequestMapping("/doDelete")
    public String doDelete(int id,Model model){

        Video video = videoMapper.selectById(id);
        videoMapper.deleteById(id);
        String fileName = video.getLocation();
        String location = GlobalMy.LOCATION+fileName;
        FileUtil.deleteFile(location);
        List<Video> videos = videoMapper.selectAll();
        model.addAttribute("videos",videos);
        return "manage/delete";
    }
}
