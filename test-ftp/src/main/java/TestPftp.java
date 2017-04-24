import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;

import com.enterprisedt.net.ftp.FTPClient;
import com.enterprisedt.net.ftp.FTPConnectMode;
import com.enterprisedt.net.ftp.FTPException;
import com.enterprisedt.net.ftp.FTPTransferType;

public class TestPftp {

    public static void main(String[] args) throws IOException, FTPException {

        if(args == null || args.length < 5){
            System.out.println("请输入参数：0远程IP 1远程Port 2用户名 3密码 4远程ftp目录 5本地IP(可选)");
            System.exit(-1);
        }

        String remoteHost = args[0];
        int remotePort = Integer.valueOf(args[1]);
        String ftpname = args[2];
        String ftppwd = args[3];
        String ftpdir = args[4];

        String localHost = null;
        if(args.length >= 6){
            localHost = args[5];

            InetAddress localAddr = InetAddress.getByName(localHost);
            System.out.println("localHost：" + localAddr.toString());
        }

        FTPClient ftpClient = new FTPClient();

        ftpClient.setRemoteHost(remoteHost);
        ftpClient.setRemotePort(remotePort);

        if(localHost != null)
            ftpClient.setLocalHost(localHost);

        ftpClient.connect();
        ftpClient.setTimeout(20 * 1000);// 120秒超时


        ftpClient.login(ftpname, ftppwd);

        //ftpClient.setType(FTPTransferType.ASCII);
        ftpClient.setType(FTPTransferType.BINARY);
        ftpClient.setConnectMode(FTPConnectMode.PASV);


        ftpClient.chdir(ftpdir);

        String[] folders = ftpClient.dir(".", false);

        System.out.println("目录清单: " + Arrays.toString(folders));


    }

}
