package top.yueshushu.init.util;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
@Log4j2
public class HardwareUtil {

    private static final String[] getCpuIdWindows = {"wmic", "cpu", "get", "ProcessorId"};
    private static String cpuId;

    public static String getCpuId() {
        try{
            String osName = getOsName();
            String cpuId = null;

            if (osName.toLowerCase().contains("linux")) {
                cpuId = getSerialNumber("dmidecode -t processor | grep 'ID'", "ID", ":");
            } else if (osName.toLowerCase().contains("windows")) {
                cpuId = getWindowsCpuId();
            }
            return !StringUtils.hasText(cpuId)?"1020304050607080":cpuId;
        }catch (Exception e){
            log.error("生成CpuId错误",e);
            return "1020304050607080";
        }
    }

    private static String getWindowsCpuId() {
        try {
            Process process = Runtime.getRuntime().exec(getCpuIdWindows);
            process.getOutputStream().close();
            Scanner sc = new Scanner(process.getInputStream());
            String property = sc.next();
            String serial = sc.next();
            log.info(property + ": " + serial);
            return serial;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            log.error("发生异常", e);
        }

        return null;
    }

    public static String getMac() {
        NetworkInterface networkInterface = getRealNetworkInterface();
        try {
            if (networkInterface == null) {
                return null;
            }
            byte[] mac = networkInterface.getHardwareAddress();
            return buildMac(mac);
        } catch (SocketException e) {
            log.error("发生异常{}", e);
        }
        return null;
    }

    public static String getServerId() {
        return buildServerId(getIpAddress());
    }

    private static String getIpAddress() {
        NetworkInterface networkInterface = HardwareUtil.getRealNetworkInterface();
        return networkInterface.getInterfaceAddresses().get(0).getAddress().getHostAddress();
    }

    private static NetworkInterface getRealNetworkInterface() {
        try {
            //枚举本机所有的网络接口
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();

                // 去除回环接口，子接口，未运行和接口
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                }
                return netInterface;
            }
        } catch (SocketException e) {
            System.err.println("Error when getting host ip address" + e.getMessage());
        }
        return null;
    }

    private static String buildServerId(String address) {
        List<String> ips = Arrays.asList(address.split("\\."));
        return ips.stream().mapToInt(Integer::parseInt).boxed().map(Object::toString).reduce("", String::concat);
    }

    private static String buildMac(byte[] mac) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length() == 1 ? 0 + s : s);
        }
        return sb.toString().toUpperCase();
    }

    private static String getOsName() {
        return System.getProperty("os.name");
    }


    private static String getSerialNumber(String cmd, String record, String symbol) {
        String execResult = executeLinuxCmd(cmd);
        String[] infos = execResult.split("\n");

        for (String info : infos) {
            info = info.trim();
            if (info.contains(record)) {
                info.replace(" ", "");
                String[] sn = info.split(symbol);
                return sn[1];
            }
        }

        return null;
    }

    private static String executeLinuxCmd(String cmd) {
        try {
            log.debug("got cmd job : " + cmd);
            Runtime run = Runtime.getRuntime();
            Process process;
            process = run.exec(cmd);
            InputStream in = process.getInputStream();
//            BufferedReader bs = new BufferedReader(new InputStreamReader(in));
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[8192];
            for (int n; (n = in.read(b)) != -1; ) {
                out.append(new String(b, 0, n));
            }

            in.close();
            process.destroy();
            return out.toString();
        } catch (Exception e) {
            log.error("发生异常", e);
        }
        return null;
    }
}
