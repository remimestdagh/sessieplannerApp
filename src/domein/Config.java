package domein;



public interface Config {
	public boolean USE_DUMMY = false;
	public boolean USE_JPA = !USE_DUMMY;
}
