package com.anqi.es.controller.ind;

import com.anqi.es.entity.ind.IndQueryIn;
import com.anqi.es.service.ind.IIndService;
import com.anqi.es.vo.EsResultVo;
import com.anqi.es.vo.Result;
import com.anqi.es.vo.ind.IndItemFiltersVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ind")
public class IndController {
    @Autowired
    private IIndService indService;

    @GetMapping("/search")
    public Result<EsResultVo> search(IndQueryIn in) {
        try {
            EsResultVo esResultVo = indService.search(in);
            return new Result<>(200, "查询成功", esResultVo);
        } catch (Exception e) {
            return new Result<>(501, "查询异常");
        }
    }

    @GetMapping("/itemFilters")
    public Result<IndItemFiltersVo> getIndItemFilters(@RequestParam("indCName") String indCName) {
        try {
            IndItemFiltersVo indItemFiltersVo = indService.getIndItemFiltersVo(indCName);
            return new Result<>(200, "查询成功", indItemFiltersVo);
        } catch (Exception e) {
            return new Result<>(501, "查询异常");
        }
    }
}
