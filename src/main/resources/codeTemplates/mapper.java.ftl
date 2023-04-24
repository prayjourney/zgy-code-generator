package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * ${table.name!}Mapper接口
 *
 * @author ${author}
 * @since ${date}
 */
@Mapper
@Repository
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

}
