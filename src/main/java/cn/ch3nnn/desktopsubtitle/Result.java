package cn.ch3nnn.desktopsubtitle;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 有道输出结果
 *
 * @Author ChenTong
 * @Date 2021/12/18 20:21
 */
@lombok.NoArgsConstructor
@lombok.Data
public class Result {

    /**
     * result
     */
    @JSONField(name = "result")
    public ResultDTO result;
    /**
     * requestId
     */
    @JSONField(name = "RequestId")
    public String requestId;
    /**
     * errorCode
     */
    @JSONField(name = "errorCode")
    public String errorCode;
    /**
     * action
     */
    @JSONField(name = "action")
    public String action;

    /**
     * ResultDTO
     */
    @lombok.NoArgsConstructor
    @lombok.Data
    public static class ResultDTO {
        /**
         * transPattern
         */
        @JSONField(name = "transPattern")
        public String transPattern;
        /**
         * segId
         */
        @JSONField(name = "segId")
        public Integer segId;
        /**
         * bg
         */
        @JSONField(name = "bg")
        public Integer bg;
        /**
         * context
         */
        @JSONField(name = "context")
        public String context;
        /**
         * from
         */
        @JSONField(name = "from")
        public String from;
        /**
         * tranContent
         */
        @JSONField(name = "tranContent")
        public String tranContent;
        /**
         * to
         */
        @JSONField(name = "to")
        public String to;
        /**
         * partial
         */
        @JSONField(name = "partial")
        public Boolean partial;
        /**
         * ed
         */
        @JSONField(name = "ed")
        public Integer ed;
    }
}
