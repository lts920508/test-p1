package cn.easybuy.dao.product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.easybuy.dao.BaseDaoImpl;
import cn.easybuy.entity.Product;
import cn.easybuy.params.ProductParams;
import cn.easybuy.utils.EmptyUtils;

public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {

	public ProductDaoImpl(Connection connection) {
		super(connection);
	}

	public Product tableToClass(ResultSet rs) throws Exception {
		Product product = new Product();
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setDescription(rs.getString("description"));
		product.setPrice(rs.getFloat("price"));
		product.setStock(rs.getInt("stock"));
		product.setCategoryLevel1Id(rs.getInt("categoryLevel1Id"));
		product.setCategoryLevel2Id(rs.getInt("categoryLevel2Id"));
		product.setCategoryLevel3Id(rs.getInt("categoryLevel3Id"));
		product.setFileName(rs.getString("fileName"));
		return product;
	}

	@Override
	public List<Product> queryProductList(ProductParams params)
			throws SQLException {
		List<Product> pList = new ArrayList<Product>();
		StringBuffer sql = new StringBuffer(
				"select * from easybuy_product where isDelete=0 and 1=1 ");
		List<Object> paramsList = new ArrayList<Object>();
		ResultSet rs = null;
		try {
			if (EmptyUtils.isNotEmpty(params.getKeyWords())) {
				sql.append(" and name like ? ");
				paramsList.add("%" + params.getKeyWords() + "%");
			}
			if (EmptyUtils.isNotEmpty(params.getCategorId())) {
				sql.append(" and(categoryLevel1Id=? or categoryLevel2Id=? or categoryLevel3Id=?)");
				paramsList.add(params.getCategorId());
				paramsList.add(params.getCategorId());
				paramsList.add(params.getCategorId());
			}
			if (EmptyUtils.isNotEmpty(params.getSort())) {
				sql.append(" order by " + params.getSort());
			}
			if (EmptyUtils.isNotEmpty(params.isPage())) {
				sql.append(" limit " + params.getStartIndex() + ","
						+ params.getPageSize());
			}
			rs = this.executeQuery(sql.toString(), paramsList.toArray());
			while (rs.next()) {
				Product product = tableToClass(rs);
				pList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			this.closeResource(rs);
			this.closeResource();
		}
		return pList;
	}

	@Override
	public int queryProductCount(ProductParams params) throws SQLException {
		Integer num = 0;
		StringBuffer sql = new StringBuffer(
				"select count(*) count from easybuy_product where isDelete=0 and 1=1 ");
		List<Object> paramsList = new ArrayList<Object>();
		ResultSet rs = null;
		try {
			if (EmptyUtils.isNotEmpty(params.getKeyWords())) {
				sql.append(" and name like ? ");
				paramsList.add("%" + params.getKeyWords() + "%");
			}
			if (EmptyUtils.isNotEmpty(params.getCategorId())) {
				sql.append(" and(categoryLevel1Id=? or categoryLevel2Id=? or categoryLevel3Id=?)");
				paramsList.add(params.getCategorId());
				paramsList.add(params.getCategorId());
				paramsList.add(params.getCategorId());
			}
			rs = this.executeQuery(sql.toString(), paramsList.toArray());
			while (rs.next()) {
				num = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			this.closeResource(rs);
			this.closeResource();
		}
		return num;
	}

	@Override
	public Product getProductById(Integer id) throws SQLException {
		String sql = "select id,name,description,price,stock,categoryLevel1Id,categoryLevel2Id,categoryLevel3Id,fileName,isDelete from easybuy_product where isDelete=0 and id=?";
		ResultSet resultSet = null;
		Product product = null;
		try {
			Object params[] = new Object[] { id };
			resultSet = this.executeQuery(sql, params);
			while (resultSet.next()) {
				product = tableToClass(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResource(resultSet);
			this.closeResource();
		}
		return product;
	}

	@Override
	public void updateStock(Integer id, Integer quantity) throws SQLException{
		try {
			Object[] params = new Object[] { quantity, id };
			String sql = "update easybuy_product set stock=? where isDelete=0 and id=? ";
			this.executeUpdate(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResource();
		}
	}

	@Override
	public void deleteById(Integer id) throws SQLException {
		String sql="UPDATE easybuy_product SET isDelete=1 WHERE id=?";
		Object[] params=new Object[]{ id };
		try {
			this.executeUpdate(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
		}
	}

	@Override
	public void addProduct(Product product) throws SQLException {
		try {
			String sql = "insert into `easybuy_product`(`name`,`description`,`price`,`stock`,`categoryLevel1Id`,`categoryLevel2Id`,`categoryLevel3Id`,`fileName`,`isDelete`) values(?,?,?,?,?,?,?,?,?)";
			Object[] params=new Object[]{ product.getName(),product.getDescription(),product.getPrice(),product.getStock(),product.getCategoryLevel1Id(),product.getCategoryLevel2Id(),product.getCategoryLevel3Id(),product.getFileName(),product.getIsDelete()};
			this.executeInsert(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
		}
		
	}

	@Override
	public void modifyProduct(Product product) throws SQLException {
		try {
			String sql = "UPDATE easybuy_product SET `name`=?,`description`=?,`price`=?,`stock`=?,`categoryLevel1Id`=?,`categoryLevel2Id`=?,`categoryLevel3Id`=?,`fileName`=?,`isDelete`=?  WHERE `id`=? ";
			Object[] params=new Object[]{product.getName(),product.getDescription(),product.getPrice(),product.getStock(),product.getCategoryLevel1Id(),product.getCategoryLevel2Id(),product.getCategoryLevel3Id(),product.getFileName(),product.getIsDelete(),product.getId()};
			this.executeUpdate(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeResource();
		}
	}
}
