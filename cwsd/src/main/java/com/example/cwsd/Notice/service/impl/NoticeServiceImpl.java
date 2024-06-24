package com.example.cwsd.Notice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.third.zhgd.Notice.entity.Notice;
import com.third.zhgd.Notice.mapper.NoticeMapper;
import com.third.zhgd.Notice.service.NoticeService;
import com.third.zhgd.other.utils.ConstantsUtils;
import com.third.zhgd.other.utils.DateTool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
* <p>
    *  服务层实现类
    * </p>
*
* @author Csk
* @since 2024-05-21
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper,Notice> implements NoticeService {

    @Override
    public Boolean addOrUpdate(Notice notice) {
        if(StringUtils.isBlank(notice.getId())){
            //add
            notice.setCreateTime(DateTool.getCurrTime());
            if(StringUtils.isBlank(notice.getTime())){
                notice.setTime(notice.getCreateTime());
            }
            if(StringUtils.isNotBlank(notice.getEquipmentId())){
                QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(Notice::getDelFlag, ConstantsUtils.GL_NORMAL);
                queryWrapper.lambda().eq(Notice::getEquipmentId, notice.getEquipmentId());
                queryWrapper.lambda().eq(Notice::getState, 0);
                List<Notice> list =this.list(queryWrapper);
                System.out.println(list.size());
                if(list.size()>0){
                    return true;
                }
            }
            this.save(notice);
        }else{
            //update
            this.updateById(notice);
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
            listIds.add(id);
        }
        this.removeByIds(listIds);
    }


    @Override
    public List<Notice> list( String state) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Notice::getDelFlag, ConstantsUtils.GL_NORMAL);
        if(StringUtils.isNotBlank(state)){
            queryWrapper.lambda().eq(Notice::getState, Integer.parseInt(state));
        }
        queryWrapper.lambda().orderByDesc(Notice::getTime);
        List<Notice> list =this.list(queryWrapper);
        return list;
    }


    @Override
    public Page<Notice> page(Integer pageNum,Integer pageSize,String state) {
        Page<Notice> page = new Page(pageNum,pageSize);
        LambdaQueryWrapper<Notice> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        if(StringUtils.isNotBlank(state)){
            lambdaQueryWrapper.like(Notice::getState, Integer.parseInt(state));
        }
        lambdaQueryWrapper.eq(Notice::getDelFlag,ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(Notice::getTime);
        return this.page(page,lambdaQueryWrapper);
    }

}