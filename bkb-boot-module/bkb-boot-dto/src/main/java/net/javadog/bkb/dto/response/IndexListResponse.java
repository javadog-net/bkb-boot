package net.javadog.bkb.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 问题回复返回实体
 *
 * @author: hdx
 * @Date: 2023-01-16 09:59
 * @version: 1.0
 **/
@Data
public class IndexListResponse {

    /**
     * 红榜list
     */
    private List<RoastResponse> redList;

    /**
     * 黑榜list
     */
    private List<RoastResponse> blackList;

    /**
     * 最新list
     */
    private List<RoastResponse> lastList;

    /**
     * 最新list
     */
    private List<QuesResponse> quesList;

}
