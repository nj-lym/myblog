package com.lym.myblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @Description
 * @Auther lym
 * @Date 2020-07-31 17:25
 * @Version 1.0
 */
@Component
public class DataStatisticsComponent
{
    @Autowired
    ArticleService articleService;

    //每天执行一次，统计PV
    // 秒 分 时 日 月 星期 年份
    @Scheduled(cron = "1 0 0 * * ?")
    public void pvStatisticsPerDay()
    {
        articleService.pvStatisticsPerDay();
    }
}
