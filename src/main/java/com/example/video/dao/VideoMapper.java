package com.example.video.dao;


import com.example.video.pojo.Video;

import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface VideoMapper {

    @Select("SELECT * FROM video WHERE id=#{id}")
    Video selectById(@Param("id") int id);

    @Select("SELECT * FROM video ")
    List<Video> selectAll();

    @Insert("INSERT INTO video(name,location) VALUES(#{name},#{location})")
    Boolean insert(@Param("name") String name, @Param("location") String location);

    @Select("DELETE FROM video WHERE id=#{id}")
    Boolean deleteById(@Param("id") int id);
}
