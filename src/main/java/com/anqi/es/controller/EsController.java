package com.anqi.es.controller;

import com.anqi.es.highclient.RestHighLevelClientService;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author anqi
 */
@RestController
public class EsController {
    @Autowired
    RestHighLevelClientService restHighLevelClientService;

    @GetMapping("/es")
    public String testHigh(HttpServletResponse httpServletResponse) throws IOException{
        String source = "{\n" +
                "  \"name\" : \"耐苦无领运动半袖\",\n" +
                "  \"price\" : 300,\n" +
                "  \"num\" : 800,\n" +
                "  \"date\" : \"2019-07-28\"\n" +
                "}";

        IndexResponse response = restHighLevelClientService.addDoc("idx_clouthing", source);

        httpServletResponse.getWriter().println();

        return response.toString();
    }

    @GetMapping("/es/update")
    public UpdateResponse update(@RequestParam("indexNames") String indexNames, @RequestParam("id") String id) throws IOException {
        String content = "图表24:ESG市场动态及相关政策\n" +
                "\n" +
                "\n" +
                "\n" +
                "日期\n" +
                "\n" +
                "中证指数有限\n" +
                "\n" +
                "2月1日公司\n" +
                "\n" +
                "2月10日河钢集团\n" +
                "\n" +
                "2月17日\"成都农商银行\n" +
                "\n" +
                "英朱颅万资讯中仁史家郭\n" +
                "\n" +
                "事件\n" +
                "\n" +
                "中证指数有限公司将发布碟中和指数。为促进实现国家碟中和目标,为\n" +
                "市场提供绿色业续基准和投资标的,中证指数有限公司将于2022年3月7日\n" +
                "正式发布首条以降低殊排放强度为目标的指数一沪深300碧中和指数。沪深\n" +
                "300碳中和指数从沫深300指数样本中初除中证E56评价8及以下的上市公\n" +
                "司,并根据行业及企业的碟中和贼献,将指数权重分布向绿色领逼以及碟\n" +
                "中和贡献较大的上市公司适当倾斜,促使挂数碳排放强度逐年降低,日较\n" +
                "沪深300指数碳排放强度净少208以上\n" +
                "\n" +
                "\n" +
                "\n" +
                "河钢集团发行河北首单碳中和公司债券。由财达证券股份有限公司担任\n" +
                "狩立主承铺唐,河钢集团2022年非公开发行碟中和绿色公司保券(第一\n" +
                "期)在上海证券交易所成加发行,发行规模15亿元,期限2年期,票面利率\n" +
                "3.688。该绿色俨券是河北省首单成功发行的碳中和公司保刺。\n" +
                "\n" +
                "四川崇州市落地全国首单\"乡村振兴+碟中和\"双贴标应收账款债权融\n" +
                "资计划。成都农商银行崇州支行近日承销的全国首单\"乡村振兴\\碧中和\n" +
                "\"双贴标应收账款债权蜀资计划成功在北京金融资产交易所挂牌发行,蔡\n" +
                "集的资金将全部用于支持祺州市城镇公共交通的绿色何碣升级,推动植州\n" +
                "优化交通能源结构,促进城市绿色何碧发展。这是祺州市金融机构在人行\n" +
                "查州市支行指导下,运用绿色金融服务乡村振兴的创新举探";
        Map<String,Object> updateMap = new HashMap<>();
        updateMap.put("content", content);
        return restHighLevelClientService.updateDoc(indexNames,id, updateMap);
    }

    @GetMapping("/es/search")
    public List<String> search(@RequestParam("field") String field, @RequestParam("key") String key,
                                 @RequestParam("page") int page, @RequestParam("size") int size,
                                 @RequestParam("indexNames") String indexNames) throws IOException {
        SearchResponse response = restHighLevelClientService.search(field, key, page, size, indexNames);
//        return response.toString();
        List<String> sources = new ArrayList<>();
        for (SearchHit hit : response.getHits()) {
            sources.add(hit.getSourceAsString());
//            System.out.println(hit.getSourceAsString());
        }
       return sources;
    }
}
