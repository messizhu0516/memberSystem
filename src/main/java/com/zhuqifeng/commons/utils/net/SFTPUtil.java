package com.zhuqifeng.commons.utils.net;

import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.zhuqifeng.commons.utils.base.StringUtils;

public class SFTPUtil {

	/**
	 * @author ZhuQiFeng
	 * @addDate 2017年8月17日上午11:39:44
	 * @description 使用sftp上传文件
	 * @param src
	 *            ：源文件路径，dst：目标文件路径
	 */
	public static boolean uploadFile(String src, String dst, String ftpHost, String ftpPort,
			String ftpUserName, String ftpPassword, int timeout) {
		Session session = null;
		Channel channel = null;
		if (StringUtils.isBlank(ftpHost) || StringUtils.isBlank(ftpPort)
				|| StringUtils.isBlank(ftpUserName) || StringUtils.isBlank(ftpPassword)) {
			return false;
		} else {
			try {
				JSch jsch = new JSch(); // 创建JSch对象
				session = jsch.getSession(ftpUserName, ftpHost, Integer.valueOf(ftpPort)); // 根据用户名，主机ip，端口获取一个Session对象
				session.setPassword(ftpPassword); // 设置密码
				Properties config = new Properties();
				config.put("StrictHostKeyChecking", "no");
				session.setConfig(config); // 为Session对象设置properties
				session.setTimeout(timeout); // 设置timeout时间
				session.connect(); // 通过Session建立链接
				channel = session.openChannel("sftp"); // 打开SFTP通道
				channel.connect(); // 建立SFTP通道的连接
				ChannelSftp sftpChannel = (ChannelSftp) channel;
				/**
				 * 代码段1 OutputStream out = chSftp.put(dst,
				 * ChannelSftp.OVERWRITE); // 使用OVERWRITE模式 byte[] buff = new
				 * byte[1024 * 256]; // 设定每次传输的数据块大小为256KB int read; if (out !=
				 * null) { System.out.println("Start to read input stream");
				 * InputStream is = new FileInputStream(src); do { read =
				 * is.read(buff, 0, buff.length); if (read > 0) {
				 * out.write(buff, 0, read); } out.flush(); } while (read >= 0);
				 * System.out.println("input stream read done."); }
				 **/
				sftpChannel.put(src, dst, ChannelSftp.OVERWRITE);
				sftpChannel.quit();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				if (channel != null) {
					channel.disconnect();
				}
				if (session != null) {
					session.disconnect();
				}
			}
			return true;
		}

	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String src = "D:\\1.png"; // 本地文件名
		String dst = "/bankfile/2.png"; // 目标文件名

		String SFTP_HOST = "192.168.1.13";
		String SFTP_PORT = "22";
		String SFTP_USERNAME = "FTPTest2";
		String SFTP_PASSWORD = "hunme@1101";
		SFTPUtil.uploadFile(src, dst, SFTP_HOST, SFTP_PORT, SFTP_USERNAME, SFTP_PASSWORD, 6000);
	}
}