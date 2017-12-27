package org.ibase4j.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import top.ibase4j.core.base.BaseModel;

@TableName("sys_dept")
@SuppressWarnings("serial")
public class SysDept extends BaseModel {
	private String deptName;
	private Long parentId;
	private Integer sortNo;
	private Integer unitId;
	@TableField("leaf_")
	private Integer leaf;
	@TableField(exist = false)
	private String parentName;

	/**
	 * @return the value of sys_dept.dept_name
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName
	 *            the value for sys_dept.dept_name
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName == null ? null : deptName.trim();
	}

	/**
	 * @return the value of sys_dept.parent_id
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the value for sys_dept.parent_id
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	/**
	 * @return the value of sys_dept.sort_no
	 */
	public Integer getSortNo() {
		return sortNo;
	}

	/**
	 * @param sortNo
	 *            the value for sys_dept.sort_no
	 */
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	/**
	 * @return the value of sys_dept.leaf_
	 */
	public Integer getLeaf() {
		return leaf;
	}

	/**
	 * @param leaf
	 *            the value for sys_dept.leaf_
	 */
	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", deptName=").append(deptName);
		sb.append(", parentId=").append(parentId);
		sb.append(", sortNo=").append(sortNo);
		sb.append(", leaf=").append(leaf);
		sb.append("]");
		return sb.toString();
	}

	/**
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		SysDept other = (SysDept) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getDeptName() == null ? other.getDeptName() == null
						: this.getDeptName().equals(other.getDeptName()))
				&& (this.getParentId() == null ? other.getParentId() == null
						: this.getParentId().equals(other.getParentId()))
				&& (this.getSortNo() == null ? other.getSortNo() == null : this.getSortNo().equals(other.getSortNo()))
				&& (this.getLeaf() == null ? other.getLeaf() == null : this.getLeaf().equals(other.getLeaf()))
				&& (this.getEnable() == null ? other.getEnable() == null : this.getEnable().equals(other.getEnable()))
				&& (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
				&& (this.getCreateBy() == null ? other.getCreateBy() == null
						: this.getCreateBy().equals(other.getCreateBy()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getUpdateBy() == null ? other.getUpdateBy() == null
						: this.getUpdateBy().equals(other.getUpdateBy()))
				&& (this.getUpdateTime() == null ? other.getUpdateTime() == null
						: this.getUpdateTime().equals(other.getUpdateTime()));
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getDeptName() == null) ? 0 : getDeptName().hashCode());
		result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
		result = prime * result + ((getSortNo() == null) ? 0 : getSortNo().hashCode());
		result = prime * result + ((getLeaf() == null) ? 0 : getLeaf().hashCode());
		result = prime * result + ((getEnable() == null) ? 0 : getEnable().hashCode());
		result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
		result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
		result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
		return result;
	}
}
