package com.weavernorth.taide.getProjectCost;

import com.weavernorth.taide.getProjectCost.vo.TiDiProjectInfo;

import java.util.List;

/**
 * 获取项目花费信息
 */
public interface GetProjectCost {

    List<TiDiProjectInfo> getProjectCostInfo(String startDate, String endDate);
}
