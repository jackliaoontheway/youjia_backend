package com.ryit.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ryit.entity.Rent;
import com.ryit.entity.vo.RentQueryVo;
import com.ryit.entity.vo.RentVo;
@Service
public interface RentService {
		/**该方法用来增加租金的信息
		 * @param rent
		 * @return
		 */
		public String addRent(Rent rent);

		/**该方法用来查询所有的租金信息
		 * @param rentQueryVo
		 * @return
		 */
		public List<Rent> queryAll(RentQueryVo rentQueryVo);

		/**该方法用来查询一定范围内时间的数据
		 * @param rentQueryVo
		 * @return
		 */
		public Map<String, Object> queryDate(RentQueryVo rentQueryVo);

		/**改方法用来删除数据库中的租金信息
		 * @param rent
		 * @return
		 */
		public String deleteRent(Rent rent);

		/**该方法用来对数据库中的租金信息进行修改
		 * @param rent
		 * @return
		 */
		public String updateRent(Rent rent);

		/**该方法根据传入的参数对id进行查询
		 * @param id
		 * @return
		 */
		public Rent findId(Integer id);

		/**该方法用来查询所有的租金信息以及费用管理的单价信息,并已List返回	
		 * @param flag
		 * @param rentQueryVo
		 * @return
		 */
		public Map<String, Object> getRenVoAll(boolean flag,RentQueryVo rentQueryVo);
		/**该方法用来查询离这个月最近的月份
		 * @param rent
		 * @return
		 */
		public Rent selectLatelyDate(Rent rent);
}
