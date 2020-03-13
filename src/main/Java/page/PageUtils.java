package page;

import cn.seczone.ssdlc.portal.common.tool.Utils;
import cn.seczone.toolkits.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页工具
 * @Author : JieWang
 * @Date : Created in 2019年04月04日15:56
 * @Email : wangjie_hf@seczone.cn
 */
public class PageUtils {

    private static final Logger logger = LoggerFactory.getLogger(PageUtils.class);

    /**
     * pageVo 预处理
     * @param pageVo
     * @return
     */
    public static PageVo getPrePageVo(PageVo pageVo){
        try{
            pageVo.setStart(pageVo.getPageIndex(),pageVo.getPageSize());
            String filter = pageVo.getFilter();
            if(filter != null && !StringUtils.isEmpty(filter)){
                pageVo.setFilter("%"+pageVo.getFilter()+"%");
            }
            return pageVo ;
        }catch (Exception e){
            logger.error(e.getMessage(),e.getStackTrace());
        }

        return new PageVo() ;
    }

    /**
     * pageVo 封装list到data ，并且使用
     * @param pageVo
     * @param dataList
     * @return
     */
    public static PageVo getPageVo(PageVo pageVo ,List dataList){
        try{
            if(Utils.isNotEmpty(dataList)) {
                /**
                 * 返回分页list和total.
                 * 返回的数据刚好是两条
                 */
                if(dataList instanceof ArrayList && dataList.size() == 2 ){
                    pageVo.setData((List) dataList.get(0));
                    Object o = ((List)dataList.get(1)).get(0) ;
                    if( o instanceof Integer){
                        pageVo.setTotal((Integer) o);
                    }else if(o instanceof Long){
                        pageVo.setTotal(((Long)o).intValue());
                    }else{
                        //第二条数据类型不是int或long
                        pageVo.setData(dataList);
                        return pageVo ;
                    }
                    if(((List)dataList.get(1)).get(0) instanceof Long){
                        Long l = (Long) ((List)dataList.get(1)).get(0);
                        pageVo.setTotal(l.intValue());
                    }else {
                        pageVo.setTotal((Integer) ((List)dataList.get(1)).get(0));
                    }
                }else {
                    pageVo.setData(dataList);
                }
            }
            return pageVo ;
        }catch (Exception e){
            logger.error(e.getMessage(),e.getStackTrace());
        }
        return pageVo ;
    }
}
