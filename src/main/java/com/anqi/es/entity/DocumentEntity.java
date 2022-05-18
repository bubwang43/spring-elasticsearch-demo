package com.anqi.es.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * document
 * @author 
 */
@Data
public class DocumentEntity implements Serializable {
    private Long id;

    /**
     * 中金研报id
     */
    private Long dId;

    private String title;

    private String authorIds;

    private String authorNames;

    private Date publishTime;

    private Long categoryId;

    private Integer status;

    private Boolean isImportant;

    private Integer level;

    private Integer levelChange;

    /**
     * 研报行业id,有多个，用逗号分隔
     */
    private String industryIds;

    private String stockCode;

    /**
     *  1 非美版 2 美版
     */
    private Boolean isAmerican;

    private String relatedIds;

    private String highlight;

    private Long price;

    private String fileUrl;

    private Integer fileSize;

    private Integer filePageSize;

    private Date protectedTime;

    private String audioUrl;

    private String videoUrl;

    /**
     * 研报同步的时间
     */
    private Date createTime;

    private Date updateTime;

    private Integer languageType;

    private String languageRelated;

    /**
     * 0: 还未推送到abc; 1: 已经推送到abc;2：执行过推送，但是执行失败
     */
    private Integer indexPushed;

    /**
     * 0.暂未准备就绪，不应执行索引推送。1.推送就绪
     */
    private Integer parsePrepared;

    /**
     * 音频名称
     */
    private String audioName;

    private String syncRelatedIds;

    /**
     * 从中间数据库同步而来的研报标签，用逗号分隔的字符串
     */
    private String syncLabel;

    private String subStockCode;

    /**
     * 评论状态， 0待定（默认），1开启，2 暂停 3 关闭
     */
    private Boolean commentState;

    /**
     * 是否取消全局研报自动暂停条数限制，默认为1（否），0（取消限制）
     */
    private Boolean commentLimitState;

    /**
     * 取消暂停时间
     */
    private Date commentCancelStopTime;

    /**
     * pdf版本号
     */
    private Integer pdfVersion;

    /**
     * portal研报分类Id，对应portal_category表的id,多个用逗号分隔,包含一级、二级分类、三级分类
     */
    private String portalCategoryIds;

    /**
     * 分享图片-微信小程序
     */
    private String wxSharePicture;

    private static final long serialVersionUID = 1L;
}