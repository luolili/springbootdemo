package com.luo.web.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luo.domain.Video;
import com.luo.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("")
public class VideoAdminWeb {
    @Resource
    VideoService videoService;

    @RequestMapping("save")
    public Object save(String title) {
        Video video = new Video();
        video.setTitle(title);
        return videoService.save(video);
    }

    @RequestMapping("page")
    public Object page(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Video> list = videoService.findAll();
        PageInfo<Video> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
