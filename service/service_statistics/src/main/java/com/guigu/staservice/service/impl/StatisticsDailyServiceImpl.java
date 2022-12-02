package com.guigu.staservice.service.impl;

import com.atguigu.commonutils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guigu.staservice.UcenterClient;
import com.guigu.staservice.entity.StatisticsDaily;
import com.guigu.staservice.mapper.StatisticsDailyMapper;
import com.guigu.staservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-10-10
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
  @Autowired
  private UcenterClient ucenterClient;
    @Override
    public void registerCount(String day) {
      //先删除前一天数据库
      QueryWrapper<StatisticsDaily> queryWrapper=new QueryWrapper<>();
      queryWrapper.eq("date_calculated",day);
      baseMapper.delete(queryWrapper);


      //添加到数据库
      int i = ucenterClient.countRegister(day);

      StatisticsDaily statisticsDaily = new StatisticsDaily();
      statisticsDaily.setRegisterNum(i);       // 注册人数
      statisticsDaily.setDateCalculated(day);  //统计日期

      statisticsDaily.setVideoViewNum(RandomUtils.nextInt(100,200));
      statisticsDaily.setLoginNum(RandomUtils.nextInt(100,200));
      statisticsDaily.setCourseNum(RandomUtils.nextInt(100,200));

      baseMapper.insert(statisticsDaily);
    }

  @Override
  public Map<String, Object> getShowData(String type, String begin, String end) {
      QueryWrapper<StatisticsDaily> queryWrapper=new QueryWrapper<>();
      queryWrapper.between("date_calculated",begin,end);
      queryWrapper.select("date_calculated",type);

    List<StatisticsDaily> staList = baseMapper.selectList(queryWrapper);

    //封装map
    List<String> date_calculatedList=new ArrayList<>();
    List<Integer> numDataList=new ArrayList<>();

    for (StatisticsDaily sta:staList){
      date_calculatedList.add(sta.getDateCalculated());

      switch (type){
        case "login_num":
          numDataList.add(sta.getLoginNum());
          break;
        case "register_num":
          numDataList.add(sta.getRegisterNum());
          break;
        case "video_view_num":
          numDataList.add(sta.getVideoViewNum());
          break;
        case "course_num":
          numDataList.add(sta.getCourseNum());
          break;
      }
    }

    HashMap<String, Object> map = new HashMap<>();
    map.put("date_calculatedList",date_calculatedList);
    map.put("numDataList",numDataList);
    return map;
  }
}
