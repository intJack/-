package edu.just.resource_management_system.mapper;

import edu.just.resource_management_system.pojo.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 按照语言查询前十条资源
 * 按照标签查询前十条资源
 * 按照语言和标签共同查找资源
 */
public interface ResourceMapper {
    List<Resource>selectTop10Resources(List<Long>ids);

    List<Resource>selectResourcesByIdsAndKeyword(@Param("ids") List<Long>ids,
                                                 @Param("keyword") String keyword);
}
