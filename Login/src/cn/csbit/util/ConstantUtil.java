package cn.csbit.util;



public interface ConstantUtil {
	final static Boolean isLinux = System.getProperties().getProperty("os.name").toLowerCase().contains("linux");
	/**部署的 /WEB-INF/classes/目录
	 * 
	 */
	public final static String CLASSPATH = isLinux ? ConstantUtil.class.getResource("/").toString().replaceFirst("file:", "") 
			: ConstantUtil.class.getResource("/").toString().replaceFirst("file:/", "");
	public final static String WEBROO = CLASSPATH.replaceAll("WEB-INF/classes/", "");

	//角色
	public final static byte SysAdmin = 1;
	public final static byte SecAdmin = 2;
	public final static byte AuditAdmin = 3;
	public final static byte system = 4;
	public final static byte security = 5;
	public final static byte audit = 6;
	public final static byte MoreAdmin = 7;
	
	public final static short TAP = 0;// 单网口 0 旁路
	public final static short FIRE = 1;// 单网口 0 旁路	
	
	public final static long dayMillis = 24*60*60*1000l;
	
	public final static byte EngineRuning = 0;
	public final static byte EngineStop = 1;
	public final static byte EngineNotAvailable = 2;
	public final static byte EngineError = 3;
	
	public final static String SUCCESS = "@success";
	
	//验证 正则
	public final static String IP = "(^25[0-5]|^2[0-4]\\d|^1\\d{2}|^[1-9]\\d|^\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)){3}$";
}
