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
        String content = "图表16:国企央企\"双碴\"行动迭踪\n" +
                "\n" +
                "发布日期\"国企库全似\n" +
                "\n" +
                "命阳林纲子公司简眉遂室气体自厥决排顺目林业理汇资源开发合同。2月13日,和阳林\n" +
                "2月9日居阮林纸\"钟发布公呈公告,子公司筠濑《瀑宣气体自厩凑揣顽目林东钌流源开发合同》,第一期\n" +
                "开虹面积50万申。\n" +
                "\n" +
                "河锦集国发行河北首单磁中和公司借其,申江法刹服伦有限公司担任立主视铺面,\n" +
                "河铭雇团022年非公开发行瑛中和蝉色公司保弈(符一期)在上海语交易所成功发行\n" +
                "发忏规筐衢亿元〉期颊2年期,栗面利率3488、试维色保弈昼河北省首卖成功发行的理中\n" +
                "和公司信梁。\n" +
                "\n" +
                "中国矶油宇林石化转型升级项目建设正式告动,打遂我国然化企业\"准油墟化\"\"绿\n" +
                "色例瑕\"示范工程,本次启动的吉林石化转型升纵顽目包挂新建年120万晓等1悟炳\n" +
                "\n" +
                "2月3中国石湖\"油化工装眩,改通9娟装置,借运7唐装眩。诗项目是中国石油第一个全部俭用绮电的化工\n" +
                "顽目,采用先选环保节能技术实现绿色生产。项目实施有利于推选中国石油姬化业务高质\n" +
                "垂发展、廷伸托方石化产业锤、助力交北老工业菊地摄兴战赊定施。\n" +
                "\n" +
                "兴业银行南京分行首筠分布式光伏整县推迹业务踹地。兴业钟行南京分行在维色能源刊\n" +
                "2月14日兴业锭行南京用领基震度实玑穷磁,成加蒂地诗行首符分市文光任境县推进业务,向卯交县棣公司发放\n" +
                "分行顽目费数,专项用于\"加交县振及周汪乡镇MW分布式光侨发电项目\",为当地探选排色\n" +
                "\n" +
                "能源利用提供伟融支持。\n" +
                "\n" +
                "中国石化长城湖湘油重硫推出绿色何礁渊压油。中国石化油涛油有限公司重确推\"\n" +
                "NES000贵瀑长效渡压泉\"\"HE长效液压油\"两歇焕新产口,以任碑科技助排户运菊限札\n" +
                "增效。在国家瑛达崔、锴中和的大腐景下,此次E长政系列渭压油刊焊新上市,以产品的\n" +
                "E\n" +
                "\n" +
                "海康婷林与牺斯联岗隐签红,将在佑碳A1国区等领堤吾作。双方将通过共创解沥方类、\n" +
                "建立联合实驿室铭方式,持缝排动双镐技术、产品及决方案,在佑跟A国区、佑确Al社\n" +
                "区、伸琼W途等领振有效葛地,间时关注埕市绍双颖大脑、能浩大脑算关锭新兴市场霸\n" +
                "求,加通推选继市双璞目标的高质和河成。\n" +
                "\n" +
                "中韩石化棣焦绿色佑碳战路推动高质量发展。中韩石化诗绪合集团公司的相关要求,制\n" +
                "订契合全业实陈的节能陶瑞目标与行动方案,力争2023年达刺单图待转佑于.8年家林油/\n" +
                "《跳,国放)的水平,绮绮推动刹度蒂实,开展工艺算理名业常态化查查,重炳国绕技高\n" +
                "\n" +
                "中韩石化\"全厂炎直供家、提高加烨炉效家、优化分铁基固浑比、推动调节闵数\n" +
                "专顽管理算多方面葬定用能伟工作,缙接大力散河跋真海湖,仁年\n" +
                "管庾潼点40处,目前狒改率为80化工部分发现并驹改借度渊点0会处,目前驿改率为\n" +
                "988.\n" +
                "\n" +
                "2808河铁集团\n" +
                "\n" +
                "28158中国目化\n" +
                "\n" +
                "\n" +
                "\n" +
                "216B滋康历视";
        Map<String,Object> updateMap = new HashMap<>();
        updateMap.put("content", content);
        return restHighLevelClientService.updateDoc(indexNames,id, updateMap);
    }

    @GetMapping("/es/update2")
    public UpdateResponse update2(@RequestParam("indexNames") String indexNames, @RequestParam("id") String id) throws IOException {
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
