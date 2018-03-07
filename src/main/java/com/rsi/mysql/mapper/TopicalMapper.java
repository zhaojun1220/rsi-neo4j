package com.rsi.mysql.mapper;

import java.util.List;
import java.util.Map;

public interface TopicalMapper {
	
//	/** 根据标准分类类型取得标准分类序号 */
//	public Integer findNodnoByNodtype(Map<String, Object> params);
//
//	/** 取得我的批注的题录序号 */
//	public List<Integer> findMypostilStdnoByPsnno(Map<String, Object> params);
//
	public List<Map<String, Object>> findQuotes(Map<String, Object> params);

//
//	/**
//	 * 取得个人收藏的标准序号
//	 * @param params
//	 * @return
//	 */
//	public List<Integer> findFavoriteStdidByPsnno(Map<String, Object> params);
}
