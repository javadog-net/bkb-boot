package net.javadog.bkb.common.enums;

import lombok.Getter;

/**
 * @Description: 业务枚举类
 * @Author: hdx
 * @Date: 2022/1/30 9:12
 * @Version: 1.0
 */
@Getter
public enum ServiceErrorEnum implements AbstractBaseExceptionEnum {

    // 业务错误
    ROAST_EXIST_ERROR(5001, "雄所见略同,此公司名称已存在!"),
    ROAST_IS_EMPTY_ERROR(5002, "吐槽信息不存在"),
    QUES_EXIST_ERROR(5011, "雄所见略同,此问题已存在!"),
    LIKES_MORE_THEN_FIVE_ERROR(5012, "已点赞5次，给别人留点机会吧!"),

    // 文件错误
    FILE_IS_NULL(3001, "文件不能为空!"),
    FILE_MAX_POST_SIZE(3002, "文件大小超出最大限制!"),
    FILE_UPLOAD_ERROR(3003, "文件上传失败!"),
    ;

    /**
     * 错误码
     */
    private final Integer resultCode;

    /**
     * 错误描述
     */
    private final String resultMsg;

    ServiceErrorEnum(Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}
