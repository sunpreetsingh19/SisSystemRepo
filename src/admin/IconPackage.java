package admin;

import javax.swing.ImageIcon;

public class IconPackage {
	public ImageIcon IconsDash() {
		return new ImageIcon(this.getClass().getResource("/image/Dash.png"));
	
}
public ImageIcon IconsCourseList() {
	
	return new ImageIcon(
            this.getClass().getResource("/image/CList.png"));
	
}
public ImageIcon IconsEnrollment() {
	return new ImageIcon(
            this.getClass().getResource("/image/student.png"));
	
}
public ImageIcon IconsDepartment() {
	return new ImageIcon(
            this.getClass().getResource("/image/department.png"));
	
}

public ImageIcon IconsLogout() {
	return new ImageIcon(
            this.getClass().getResource("/image/logout.png"));
	
}
public ImageIcon IconsAdd() {
	return new ImageIcon(
            this.getClass().getResource("/image/add.png"));
	
}
public ImageIcon IconsSearch() {
	return new ImageIcon(
            this.getClass().getResource("/image/search.png"));
	
}
public ImageIcon IconsReload() {
	return new ImageIcon(
            this.getClass().getResource("/image/reload.png"));
	
}

}
