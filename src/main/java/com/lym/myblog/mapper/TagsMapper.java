package com.lym.myblog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description
 * @Auther lym
 * @Date 2020-07-28 13:41
 * @Version 1.0
 */
@Mapper
public interface TagsMapper
{
    /**
     * 删除活动id对应的标签
     * @param aid 活动id
     * @return
     */
    int deleteTagsByAid(Long aid);

    /**
     * 保存标签
     * @param tags
     * @return
     */
    int saveTags(@Param("tags") String[] tags);

    /**
     * 根据标签名查询标签
     * @param tagNames 标签名
     * @return
     */
    List<Long> getTagsIdByTagName(@Param("tagNames") String[] tagNames);

    /**
     * 设置标签给活动
     * @param tagIds 标签id
     * @param aid 活动id
     * @return
     */
    int saveTags2ArticleTags(@Param("tagIds") List<Long> tagIds, @Param("aid") Long aid);
}
