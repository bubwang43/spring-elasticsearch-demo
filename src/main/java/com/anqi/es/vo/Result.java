package com.anqi.es.vo;

import lombok.Data;

/**
 * 统一返回结果集实体类
 * @param <T> 返回数据对象
 */
@Data
public class Result<T> {

    //错误码
    private Integer code;

    //错误信息，一般为前端提示信息
    private String msg;

    //返回值，一般为成功后返回的数据
    private T data;

    //错误详情，一般为失败后的详细原因，如空指针之类的
    private String errorDetail;

    public Result() {}

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 配合静态对象直接设置 data 参数
     * @param data
     * @return
     */
    public Result setNewData(T data) {
        Result error = new Result();
        error.setCode(this.code);
        error.setMsg(this.msg);
        error.setErrorDetail(this.errorDetail);
        error.setData(data);
        return error;
    }

    /**
     * 配合静态对象直接设置 msg 参数
     * @param msg
     * @return
     */
    public Result setNewMsg(String msg) {
        Result error = new Result();
        error.setCode(this.code);
        error.setMsg(msg);
        error.setErrorDetail(this.errorDetail);
        error.setData(this.data);
        return error;
    }

    public static final Result SUCCESS = new Result(200, "成功");

    public static final Result INSERT_SUCCESS = new Result(200, "新增成功");

    public static final Result UPDATE_SUCCESS = new Result(200, "更新成功");

    public static final Result DELETE_SUCCESS = new Result(200, "删除成功");

    public static final Result UPLOAD_SUCCESS = new Result(200, "上传成功");

    public static final Result DOWNLOAD_SUCCESS = new Result(200, "下载成功");

    public static final Result LOGIN_SUCCESS = new Result(200, "登陆成功");

    public static final Result LOGOUT_SUCCESS = new Result(200, "登出成功");

    public static final Result LOGIN_ERROR = new Result(201, "登陆错误");

    public static final Result LOGIN_EXPIRE = new Result(202, "登陆过期");

    public static final Result ACCESS_LIMITED = new Result(301, "访问受限");

    public static final Result ARGS_ERROR = new Result(501, "参数错误");

    public static final Result UNKOWN_ERROR = new Result(502, "系统异常");

    public static final Result INSERT_ERROR = new Result(503, "新增错误");

    public static final Result UPDATE_ERROR = new Result(504, "更新错误");

    public static final Result DELETE_ERROR = new Result(506, "删除错误");

    public static final Result UPLOAD_ERROR = new Result(507, "上传错误");

    public static final Result DOWNLOAD_ERROR = new Result(508, "下载错误");

    public static final Result OTHER_SYSTEM_ERROR = new Result(509, "调用系统异常");
}
