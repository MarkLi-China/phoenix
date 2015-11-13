package codegen.gen;

import codegen.configxml.GenConfiguration;
import codegen.configxml.TableConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;

/**
 * Controller生成类
 * @author xiongfang
 */
public class ControllerGen extends BaseGen {

    public ControllerGen(GenConfiguration genConfiguration, TableConfig tableConfig, Configuration ftlCfg) {

        super(genConfiguration, tableConfig, ftlCfg);
    }

    @Override
    public Template getTemplate() throws IOException {

        Template template = ftlCfg.getTemplate("controller.ftl");
        template.setEncoding("utf-8");
        return template;
    }

    @Override
    public void preGen() throws Exception {

    }

    @Override
    public String getOutPath() throws Exception {

        return genCfg.getBase().getController().replace('.', '/') + "/" + tableConfig.getTableMeta().getEntityName() + "Controller.java";
    }

}
