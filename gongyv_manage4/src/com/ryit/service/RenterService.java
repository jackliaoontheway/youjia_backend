package com.ryit.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ryit.entity.Rent;
import com.ryit.entity.Renter;
import com.ryit.entity.vo.RenterVo;

@Service
public interface RenterService {
	
	/**该方法用来根据传入的参数查询对应的id并返回
	 * @param id
	 * @return
	 */
	public Renter findByLoginName(String loginName);
	
	/**该方法用来根据传入的参数查询对应的id并返回
	 * @param id
	 * @return
	 */
	public Renter findId(Integer id);

	/**该方法用来查询所有的在数据库的租户信息,将数据添加至List集合并返回
	 * @return
	 */
	public List<Renter> selectAll();
	/**该方法用来根据传入的Renter对象的rid(栋数ID)、bid(房间ID)、state(状态)、phone(电话)、identity_card(身份证)属性进行查询,将数据添加至List集合并返回
	 * @param renterVo
	 * @return
	 */

	public List<RenterVo> queryAssigned(RenterVo renterVo);
	/**该方法用向数据库添加租户的数据
	 * @param renter
	 * @return
	 */
	public String addRenter(Renter renter,MultipartFile[] files,boolean isAdmin);
	/** 该方法用来对用户的信息进行修改
	 * @param renter
	 * @return
	 */
	public String updateRenter(RenterVo renter);
	/**该方法用来删除数据库对应的数据
	 * @param renter
	 * @return
	 */
	public String deleteRenter(Renter renter);
	/** 该方法用来根据栋号以及房间号查询状态是在租的用户
	 * @param rent
	 * @return
	 */
	public Renter selectUser(Rent rent);

	/**该方法用来做查询全部的分页的
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<RenterVo> getAllRenter(int currentPage, int pageSize);
	/**该用来查询数据库所有的行
	 * @return
	 */
	public Integer getAllRecord();
	
	/**该用来按条件查询数据库所有的行
	 * @param renter
	 * @return
	 */
	public Integer getAllRecords(RenterVo renter);
	
	public String showPicZ(int id);
	public String showPicF(int id);
	public String showPicB(int id);
	
	Boolean register(Renter renter,MultipartFile[] files);
}
