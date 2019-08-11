package com.ryit.mapper;

import java.util.List;

import com.ryit.entity.Building;
import com.ryit.entity.Plot;
import com.ryit.entity.Rent;
import com.ryit.entity.Renter;
import com.ryit.entity.Room;
import com.ryit.entity.vo.RentQueryVo;
import com.ryit.entity.vo.RentVo;

public interface StateMapper {

	/**该方法用来获取小区的
	 * @return
	 */
	public List<Plot> selectPlotState();
	/**该方法用来获取栋数的栋号
	 * @param id
	 * @return
	 */
	public List<Building> selectBuildingState(Integer id);

	/**该方法用来获取房间号
	 * @param pid
	 * @return
	 */
	public List<Room> selectRoomstate(Integer pid);
	
	/**该方法用来获取小区的
	 * @return
	 */
	public List<Plot> selectPlotStates();

	/**该方法用来获取栋数的栋号
	 * @param id
	 * @return
	 */
	public List<Building> selectBuildingStates(Integer id);
	/**该方法用来获取房间号
	 * @param pid
	 * @return
	 */
	public List<Room> selectRoomstates(Integer pid);
	
	/**该方法用来获取已租的房间号
	 * @return
	 */
	public List<Building> selectBuildingStatex();
	/**该方法用来获取未租房间号
	 * @param pid
	 * @return
	 */
	public List<Room> selectRoomstatex(Integer pid);
}
