package ${cfg.base.mapperExt};

<#if table.page == true || table.list == true>
import java.util.List;
import java.util.Map;
</#if>
import ${cfg.base.mapper}.${table.tableMeta.entityName}Mapper;
import ${cfg.base.mapperModel}.${table.tableMeta.entityName};

/**
* ${table.tableMeta.entityName}Dao.java
* @autor xiongfang
* @team kuaidigroup.zhuanche.boss
* @version 1.0.0
*/
public interface ${table.tableMeta.entityName}Dao extends ${table.tableMeta.entityName}Mapper {

<#if table.page == true>

public int count(Map
<String, Object> params);

public List<${table.tableMeta.entityName}> getListByPage(Map
<String, Object> params);

</#if>
<#if table.list == true>
public List<${table.tableMeta.entityName}> getList(Map
<String, Object> params);

</#if>

}