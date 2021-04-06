package com.userzrq.designPatterns.dutyChain;


import org.junit.internal.requests.FilterRequest;

import javax.servlet.http.HttpServletResponse;

public abstract class AbstractFilter {

    /**
     * 类似单向链表，指针指向下一个节点（过滤器）
     */
    private AbstractFilter nextFilter;

    /**
     * 责任链的下一个元素
     */
    public void setNextFilter(AbstractFilter nextFilter) {
        this.nextFilter = nextFilter;
    }

    public AbstractFilter getLastFilter() {
        if (this.nextFilter != null) {
            return this.nextFilter.getLastFilter();
        } else {
            return this;
        }
    }


}
