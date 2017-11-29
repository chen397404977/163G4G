/**
 * 
 */
package com.sea.sbms.goods.service;

import com.sea.sbms.goods.bean.Goods;

/**
 * @author admin 2017年11月15日
 */
public interface GoodsService {

	/**
	 * @param goodsNum
	 * @param goodsId
	 * @return
	 */
	public int updateGoods(int goodsNum, int goodsId);

	/**
	 * @param goodsName
	 * @return
	 * 
	 */
	public Goods getGoodsInfoByName(String goodsName);
}
