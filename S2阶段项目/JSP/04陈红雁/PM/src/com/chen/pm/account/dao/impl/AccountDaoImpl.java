package com.chen.pm.account.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.chen.pm.account.bean.AccountDetail;
import com.chen.pm.common.bean.PageBean;
import com.chen.pm.goods.bean.Goods;
import com.chen.pm.utils.DBManager;


public class AccountDaoImpl {
	private Connection con = null;
	private PreparedStatement ps = null;

	public PageBean getAccount(PageBean pageBean) {
		AccountDetail accountDetail = null;
		con = DBManager.getConnection();
		String sql = "select top "
				+ pageBean.getPagesize()
				+ " a.accountId,g.goodsName,g.goodsNum,a.businessNum,a.totalPrice,"
				+ " a.isPayed,p.providerName,g.goodsIntro,a.accountDate "
				+ " from tb_account a left join tb_goods g on a.goodsId = g.goodsId "
				+ " left join tb_provider p on p.providerId = a.providerId "
				+ " where a.accountId not in "
				+ " (select top "
				+ pageBean.getPagesize()*(pageBean.getP() - 1)
				+ " a.accountId from tb_account a left join tb_goods g on a.goodsId = g.goodsId "
				+ "left join tb_provider p on p.providerId = a.providerId );";
		System.out.println(sql);
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				accountDetail = new AccountDetail(
						rs.getInt("accountId"), 
						rs.getString("goodsName"), 
						rs.getInt("goodsNum"), 
						rs.getInt("businessNum"),
						rs.getFloat("totalPrice"), 
						rs.getInt("isPayed"), 
						rs.getString("providerName"), 
						rs.getString("goodsIntro"), 
						rs.getDate("accountDate"));
				pageBean.addData(accountDetail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageBean;
	}
	public int getCount(List<String> wheres,
			List<String> values) {
		con = DBManager.getConnection();
		String sql = "select count(*) as count from tb_account a left join tb_goods g on a.goodsId = g.goodsId ";
		if(wheres != null && wheres.size() > 0){
			sql += " where ";
			for(int i=0; i<wheres.size();i++){
				if(wheres.get(i).equals("goodsName")){
					sql += "g."+wheres.get(i)+" = "+values.get(i)+" and ";
				}else{
					sql += "a."+wheres.get(i)+" = "+values.get(i)+" and ";
				}
			}
			sql += "1 = 1";
		}
		System.out.println(sql);
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBManager.close(ps, con);
		}
		return 0;
	}

	public PageBean getAccount(PageBean pageBean, List<String> wheres,
			List<String> values) {
		AccountDetail accountDetail = null;
		con = DBManager.getConnection();
		String conditions = "";
		if(wheres != null && wheres.size() > 0){
			for(int i=0; i<wheres.size();i++){
				if(wheres.get(i).equals("goodsName")){
					conditions += "g."+wheres.get(i)+" = "+values.get(i)+" and ";
				}else{
					conditions += "a."+wheres.get(i)+" = "+values.get(i)+" and ";
				}
			}
		}
		String sql = "select top "
				+ pageBean.getPagesize()
				+ " a.accountId,g.goodsName,g.goodsNum,a.businessNum,a.totalPrice,"
				+ " a.isPayed,p.providerName,g.goodsIntro,a.accountDate "
				+ " from tb_account a left join tb_goods g on a.goodsId = g.goodsId "
				+ " left join tb_provider p on p.providerId = a.providerId "
				+ " where "
				+ conditions
				+ " a.accountId not in "
				+ " (select top "
				+ pageBean.getPagesize()*(pageBean.getP() - 1)
				+ " a.accountId from tb_account a left join tb_goods g on a.goodsId = g.goodsId "
				+ "left join tb_provider p on p.providerId = a.providerId where "
				+ conditions
				+ " 1 = 1 );";
		
		System.out.println(sql);
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				accountDetail = new AccountDetail(
						rs.getInt("accountId"), 
						rs.getString("goodsName"), 
						rs.getInt("goodsNum"),
						rs.getInt("businessNum"),
						rs.getFloat("totalPrice"), 
						rs.getInt("isPayed"), 
						rs.getString("providerName"), 
						rs.getString("goodsIntro"), 
						rs.getDate("accountDate"));
				pageBean.addData(accountDetail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pageBean;
	}

	public int addAccount(Goods goods, int providerId, int isPayed,
			int businessNum) {
		con = DBManager.getConnection();
		String sql = "insert into tb_account values (?,?,?,getDate(),?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, providerId);
			ps.setFloat(2, businessNum*goods.getGoodsPrice());
			ps.setInt(3, isPayed);
			ps.setInt(4, goods.getGoodsId());
			ps.setInt(5, businessNum);
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int updateAccount(int accountId, int isPayed) {
		con = DBManager.getConnection();
		String sql = "update tb_account set isPayed = ? where accountId = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, isPayed);
			ps.setInt(2, accountId);
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int deleteAccountById(int accountId) {
		con = DBManager.getConnection();
		String sql = "delete from tb_account where accountId = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, accountId);
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
