/*
 * Copyright (c) 2016,2018, easytnt All Rights Reserved. 深圳市易考试乐学测评有限公司 版权所有.
 */

package com.easytnt.aim.domain.model.shop.form.checkin;

import com.easytnt.aim.domain.model.share.Scoredable;

import java.util.List;

/**
 * 主观题表单内容
 *
 * @author Liguiqing
 * @since V3.0
 */

public class ZgFormBody extends CheckInFormBody implements Scoredable {

    private List<ZgFormBodyEntry> entrys;

    private List<Appendix> appendices;
}