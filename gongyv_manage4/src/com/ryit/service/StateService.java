package com.ryit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryit.entity.Building;
import com.ryit.entity.Plot;
import com.ryit.entity.Room;

@Service
public interface StateService {
		/**该方法用来获取小区的
		 * @return List<Plot>
		 */
		public List<Plot> selectPlotState();
		/**该方法用来获取栋数的栋号
		 * @param id
		 * @return List<Building>
		 */
		public List<Building> selectBuildingState(Integer id);

		/**该方法用来获取房间号
		 * @param pid
		 * @return List<Room>
		 */
		public List<Room> selectRoomstate(Integer pid);
		
		/**该方法用来获取已租的房间号
		 * @param pid
		 * @return List<Room>
		 */
		public List<Room> selectRoomstates(Integer pid);
		
		/**该方法用来获取为租房间号
		 * @param pid
		 * @return List<Room> 
		 */
		public List<Room> selectRoomstatex(Integer pid);
}
