package com.example.demo.wechat.menu;

import com.example.demo.wechat.menu.vo.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单管理器类
 * 
 * @author songxiuchao
 * @data 2019/8/16 11:39
 */
@Data
public class MenuManager {

	/**
	 * 组装菜单数据
	 * 
	 * @param menuListVo
	 * @return Menu
	 */
	public Menu getMenu(MenuListVo menuListVo) {
		List<MenuVo> button = menuListVo.getButton();
		List<ComplexButton> complexList = new ArrayList<>();
		for (MenuVo list : button) {
			List<CommonButton> buttonList = new ArrayList<>();
			ComplexButton mainBtn = new ComplexButton();
			List<Subbutton> sub_button = list.getSub_button();
			if (sub_button != null) {
				for (Subbutton listResult : sub_button) {
					CommonButton btn1 = new CommonButton();
					btn1.setName(listResult.getName());
					btn1.setType(listResult.getType());
					btn1.setKey(listResult.getKey());
					btn1.setUrl(listResult.getUrl());
					buttonList.add(btn1);
				}
			}
			mainBtn.setKey(list.getKey());
			mainBtn.setType(list.getType());
			mainBtn.setName(list.getName());
			mainBtn.setUrl(list.getUrl());
			if (sub_button != null) {
				mainBtn.setSub_button(buttonList);
			}
			complexList.add(mainBtn);

		}

		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 *
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(complexList);

		return menu;
	}

}