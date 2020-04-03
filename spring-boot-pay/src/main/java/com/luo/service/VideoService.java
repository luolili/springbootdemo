package com.luo.service;

import com.luo.domain.Video;
import com.luo.mapper.VideoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VideoService {

    @Resource
    VideoMapper videoMapper;

    public List<Video> findAll() {
        return videoMapper.findAll();
    }

    public Video findById(Integer id) {
        return videoMapper.findById(id);
    }

    public int update(Video video) {
        return videoMapper.update(video);
    }

    public int delete(Integer id) {
        return videoMapper.delete(id);
    }

    public int save(Video video) {
        return videoMapper.save(video);
    }
}
