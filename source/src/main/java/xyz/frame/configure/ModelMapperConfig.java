package xyz.frame.configure;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import xyz.frame.mrshi.pojo.po.Category;
import xyz.frame.mrshi.pojo.po.CategoryDTO;

/**
 * 一个属性拷贝工具，线程安全，选用此工具的理由：
 * 1、Spring的BeanUtils无法完成BigDecimal > String或相反的属性拷贝，不知道还有没有别的坑。
 * 2、拷贝效率比Spring的BeanUtils更高。
 *
 * @author chenks
 */
@Configuration
public class ModelMapperConfig {
    private static final Logger logger = LoggerFactory.getLogger(ModelMapperConfig.class);

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // 忽略带有歧义的字段匹配
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
        initDemoForUsers();
        return modelMapper;
    }
    
    private void initDemoForUsers() {
        // 使用，@Autowire private ModelMapper modelMapper; modelMapper.map(
        ModelMapper modelMapper = new ModelMapper();
        Category category = new Category();
        category.setCreateAt(new Date());
        category.setId(123L);
        category.setName("test");
        category.setUpdateAt(new Date());
        
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        logger.info("测试使用modemapper转换工具，测试转换对象，category-》categoryDTO：categoryDTO = {}", categoryDTO);
        
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        categoryList.add(category);
        
        List<CategoryDTO> categoryDtoList = modelMapper.map(categoryList, new TypeToken<List<CategoryDTO>>() {}.getType());
        logger.info("测试使用modemapper转换工具，测试转换对象列表，categoryList-》categoryDtoList：categoryDtoList = {}", categoryDtoList);
        
    }
}
