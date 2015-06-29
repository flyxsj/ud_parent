package com.ud.util;

public class PageInfo {

	private int count = 0;

	private int pageSize = 10;

	private int pageCount = 0;

	private int pageIndex = 1;

	public PageInfo(int pageSize, int count, int pageIndex) {

		this.pageSize = pageSize;
		this.count = count;
		setPageIndex(pageIndex);
	}

	public PageInfo(int pageSize, int count) {

		this(pageSize, count, 1);
	}

	public int getCount() {

		return count;
	}

	public void setCount(int count) {

		this.count = count;
	}

	public int getPageSize() {

		return pageSize;
	}

	public void setPageSize(int pageSize) {

		this.pageSize = pageSize;
	}

	public int getPageCount() {

		int size = count / pageSize;
		int mod = count % pageSize;
		if (mod != 0)
			size++;
		this.pageCount = count == 0 ? 1 : size;
		return pageCount;
	}

	public void setPageCount(int pageCount) {

		this.pageCount = pageCount;
	}

	public int getPageIndex() {

		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {

		int validPage = pageIndex <= 0 ? 1 : pageIndex;
		validPage = validPage > getPageCount() ? getPageCount() : validPage;
		this.pageIndex = validPage;
		// this.pageIndex = pageIndex;
	}

	public int getFromIndex() {

		return (pageIndex - 1) * pageSize;
	}

	public int getToIndex() {

		return Math.min(count, pageIndex * pageSize);
	}

	public int getPrevious() {

		if (pageIndex > 1) {
			return pageIndex - 1;
		} else {
			return pageIndex;
		}
	}

	public int getNext() {

		if (pageIndex < pageCount) {
			return pageIndex + 1;
		} else {
			return pageIndex;
		}
	}

	public String toString() {

		final String TAB = "    ";

		StringBuffer retValue = new StringBuffer();

		retValue.append("PageInfo ( ").append(super.toString()).append(TAB).append("count = ").append(this.count)
				.append(TAB).append("pageSize = ").append(this.pageSize).append(TAB).append("pageCount = ")
				.append(this.pageCount).append(TAB).append("pageIndex = ").append(this.pageIndex).append(TAB)
				.append(" )");

		return retValue.toString();
	}
}
