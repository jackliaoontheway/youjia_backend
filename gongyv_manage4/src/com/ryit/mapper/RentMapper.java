package com.ryit.mapper;

import java.util.List;

import com.ryit.entity.Rent;
import com.ryit.entity.Renter;
import com.ryit.entity.vo.RentQueryVo;
import com.ryit.entity.vo.RentVo;

public interface RentMapper {

	/**该方法用来增加租金的信息
	 * @param rent
	 */
	public void addRent(Rent rent);
	/**该方法用来查询所有的租金信息
	 * @param rentQueryVo
	 * @return
	 */
	public List<Rent> queryAll(RentQueryVo rentQueryVo);

	/**该方法用来查询一定范围内时间的数据
	 * @param rentQueryVo
	 * @return List<Rent>
	 */
	public List<Rent> queryDate(RentQueryVo rentQueryVo);

	/**改方法用来删除数据库中的租金信息
	 * @param rent
	 */
	public void deleteRent(Rent rent);

	/**该方法用来对数据库中的租金信息进行修改
	 * @param rent
	 */
	public void updateRent(Rent rent);

	/**该方法根据传入的参数对id进行查询
	 * @param id
	 * @return
	 */
	public Rent findId(Integer id);

	/**该方法用来查询上个月方法的信息
	 * @param rent
	 * @return Rent
	 */
	public Rent selectDate(Rent rent);

	/**该方法用来查询离这个月最近的月份
	 * @param rent
	 * @return Rent
	 */
	public Rent selectLatelyDate(Rent rent);
	
	/**获取数据库所有个行
	 * @return Integer
	 */
	public List<Rent> getCount();
	/**按条件获取数据库所有个行
	 * @param rentQueryVo
	 * @return Integer
	 */
	public List<Rent> getCounts(RentQueryVo rentQueryVo);
}
