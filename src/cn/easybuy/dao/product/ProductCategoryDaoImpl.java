 package cn.easybuy.dao.product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import cn.easybuy.dao.BaseDaoImpl;
import cn.easybuy.entity.ProductCategory;
import cn.easybuy.params.ProductCategoryParam;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.EmptyUtils;

public class ProductCategoryDaoImpl extends BaseDaoImpl implements
		ProductCategoryDao {

	public ProductCategoryDaoImpl(Connection connection) {
		super(connection);
	}

	private ProductCategory tableToClass(ResultSet rs) throws Exception {
		ProductCategory pc = new ProductCategory();
		pc.setId(rs.getInt("id"));
		pc.setName(rs.getString("name"));
		pc.setParentId(rs.getInt("parentId"));
		pc.setType(rs.getInt("type"));
		pc.setIconClass(rs.getString("iconClass"));
		return pc;
	}

	@Override
	public List<ProductCategory> queryAllProductCategory(String parentId) {
		List<ProductCategory> pcList = new ArrayList<ProductCategory>();
		StringBuffer sql = new StringBuffer(
				"select id,name,parentId,type,iconClass from easybuy_product_category where 1=1");
		List<Object> params = new ArrayList<Object>();
		ResultSet rs = null;
		if (null != parentId && !"".equals(parentId)) {
			sql.append(" and parentId=?");
			params.add(parentId);
		}
		try {
			rs = this.executeQuery(sql.toString(), params.toArray());
			while (rs.next()) {
				ProductCategory pc = tableToClass(rs);
				pcList.add(pc);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResource(rs);
			this.closeResource();
		}
		return pcList;
	}

	@Override
	public List<ProductCategory> queryProductCategoryList(
			ProductCategoryParam param) {
		List<Object> params = new ArrayList<Object>();
		List<ProductCategory> productList = new ArrayList<ProductCategory>();
		StringBuffer sql = new StringBuffer(
				"select epc1.*,epc2.name as parentName from easybuy_product_category epc1 left join easybuy_product_category epc2 on epc1.parentId=epc2.id where 1=1 ");
		ResultSet rs = null;
		try {
			if (EmptyUtils.isNotEmpty(param.getName())) {
				sql.append(" and epc1.name like ? ");
				params.add("%" + param.getName() + "%");
			}
			if (EmptyUtils.isNotEmpty(param.getParentId())) {
				sql.append(" and epc1.parentId like ? ");
				params.add("%" + param.getParentId() + "%");
			}
			if (EmptyUtils.isNotEmpty(param.getType())) {
				sql.append(" and epc1.type like ? ");
				params.add("%" + param.getType() + "%");
			}
			if (param.isPage()) {
				sql.append(" limit " + param.getStartIndex() + ","
						+ param.getPageSize());
			}
			rs = this.executeQuery(sql.toString(), params.toArray());
			while (rs.next()) {
				ProductCategory productCategory = this.tableToClass(rs, 1);
				productList.add(productCategory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResource(rs);
			this.closeResource();
		}
		return productList;
	}

	private ProductCategory tableToClass(ResultSet rs, int i) throws Exception {
		ProductCategory pc = new ProductCategory();
		pc.setId(rs.getInt("id"));
		pc.setName(rs.getString("name"));
		pc.setParentId(rs.getInt("parentId"));
		pc.setType(rs.getInt("type"));
		pc.setIconClass(rs.getString("iconClass"));
		pc.setParentName(rs.getString("parentName"));
		return pc;
	}

	@Override
	public int queryproductCategoryCount(ProductCategoryParam params) {
		int count = 0;
		List<Object> param = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(
				"select count(*) count from easybuy_product_category epc1 left join easybuy_product_category epc2 on epc1.parentId=epc2.id where 1=1 ");
		ResultSet rs = null;
		try {
			if (EmptyUtils.isNotEmpty(params.getName())) {
				sql.append(" and epc1.name like ? ");
				param.add("%" + params.getName() + "%");
			}
			if (EmptyUtils.isNotEmpty(params.getParentId())) {
				sql.append(" and epc1.parentId like ? ");
				param.add("%" + params.getParentId() + "%");
			}
			if (EmptyUtils.isNotEmpty(params.getType())) {
				sql.append(" and epc1.type like ? ");
				param.add("%" + params.getType() + "%");
			}
			rs = this.executeQuery(sql.toString(), param.toArray());
			while (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (Exception e) {
		}finally{
			this.closeResource(rs);
			this.closeResource();
		}
		return count;
	}

	@Override
	public void deleteById(Integer id) throws SQLException{
		String sql="delete from easybuy_product_category where id=? ";
		Object params[]=new Object[]{ id };
		this.executeUpdate(sql, params);
		this.closeResource();
	}

	@Override
	public Integer save(ProductCategory productCategory) {
		Integer id=0;
		try {
			String sql="insert into easybuy_product_category(name,parentId,type,iconClass) values(?,?,?,?) ";
			Object[] params=new Object[]{ productCategory.getName(),productCategory.getParentId(),productCategory.getType(),productCategory.getIconClass()};
			id=this.executeInsert(sql, params);
			productCategory.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
		}
		return id;
	}

	@Override
	public ProductCategory getById(Integer id) {
		String sql="select * from easybuy_product_category where id=? ";
		ResultSet rs=null;
		ProductCategory productCategory=null;
		try {
			productCategory = null;
			Object[] params=new Object[]{id};
			rs=this.executeQuery(sql, params);
			while (rs.next()) {
				productCategory=this.tableToClass(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource(rs);
			this.closeResource();
		}
		return productCategory;
	}

	@Override
	public void modifyProductCategory(ProductCategory productCategory) {
		String sql = "UPDATE easybuy_product_category SET `name`=?,parentId=? WHERE id=? ";
		try {
			Object[] params =new Object[]{ productCategory.getName(),productCategory.getParentId(),productCategory.getId() };
			this.executeUpdate(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
		}
	}

}
