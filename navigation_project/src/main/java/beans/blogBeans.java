package beans;

public class blogBeans
{

	private int userId;
	private String title;
	private String content;
	private String image;
	private String searchKeyword;
	private int page;
	private int count;

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	public String getSearchKeyword()
	{
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword)
	{
		this.searchKeyword = searchKeyword;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

}
