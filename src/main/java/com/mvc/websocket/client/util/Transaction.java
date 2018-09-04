package com.mvc.websocket.client.util;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * common transaction
 *
 * @author qiyichen
 * @author Ye_WD
 * @create 2018/5/8 16:16
 * <p>
 * Add an innerWithdraw signal.
 * @create 2018/6/25 21:21
 */
@Data
public class Transaction {

    private BigInteger id;
    /**
     * 操作类型,包含[fee:发送手续费,recharge:充值,withdraw:提现,wallet:钱包操作(钱包需要有少量余额用于发送gas费用)]
     */
    private String oprType;
    /**
     * 是否内部转账
     */
    private Integer innerWithdraw;
    /**
     * 交易数量
     */
    private BigDecimal value;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;
    /**
     * 令牌类型
     */
    private String tokenType;
    /**
     * 来源地址
     */
    private String fromAddress;
    /**
     * 交易hash
     */
    private String hash;
    /**
     * 确认数
     */
    private Integer depth;
    /**
     * 目标地址
     */
    private String toAddress;
    /**
     * 手续费
     */
    private BigDecimal fee;
    /**
     * 生成区块
     */
    private BigInteger block;
    /**
     * 交易状态， 0x0失败， 0x1确认中， 0x2最终确认
     */
    private String status;
    /**
     * 币种大类, 如erc20代币则该字段为ETH
     */
    private String blockType;
    /**
     * 所属用户id
     */
    private BigInteger createdUserId;
    /**
     * 交易id
     */
    private String transactionId;
    /**
     * 交易状态
     */
    private Integer transactionStatus;
}
