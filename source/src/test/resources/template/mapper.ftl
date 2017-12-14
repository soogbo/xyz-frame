package ${parameters.orgName}.${parameters.projectName}.<#if parameters.moduleName?has_content>${parameters.moduleName}.</#if>mapper;

import ${parameters.orgName}.${parameters.projectName}.<#if parameters.moduleName?has_content>${parameters.moduleName}.</#if>entity.${parameters.entity};
import xyz.frame.utils.FrameMapper;

public interface ${parameters.entity}Mapper extends FrameMapper<${parameters.entity}>{
    
}