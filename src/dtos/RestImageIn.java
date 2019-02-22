package dtos;

public class RestImageIn {

	private String mpadre;
	private int pags;
	private String destpath;
	private int count;

	public String getMpadre() {
		return mpadre;
	}

	public void setMpadre(String mpadre) {
		this.mpadre = mpadre;
	}

	public int getPags() {
		return pags;
	}

	public void setPags(int pags) {
		this.pags = pags;
	}

	public String getDestpath() {
		return destpath;
	}

	public void setDestpath(String destpath) {
		this.destpath = destpath;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "RestImageIn [mpadre=" + mpadre + ", pags=" + pags + ", destpath=" + destpath + ", count=" + count + "]";
	}

	
	
	
}
