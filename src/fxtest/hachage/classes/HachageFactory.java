package fxtest.hachage.classes;

public class HachageFactory {

	private static final HachageFactory instance = new HachageFactory();

	private HachageFactory(){
		super();
	}

	public IHachage getHachage(TypeHachage type){
		switch(type){
		case MD5 :
			return MD5.getInstance();
		case SHA1 :
			return SHA1.getInstance();
		default :
			return SHA1.getInstance();
		}
	}

	public static HachageFactory getInstance() {
		return instance;
	}
}
